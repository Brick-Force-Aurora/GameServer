package de.brickforceaurora.server.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jline.reader.ParsedLine;
import org.jline.reader.impl.DefaultParser;
import org.jline.reader.impl.LineReaderImpl;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.command.message.CommandManagerMessage;
import de.brickforceaurora.server.command.util.CommandUtil;
import de.brickforceaurora.server.command.util.CommandUtil.CommandArguments;
import de.brickforceaurora.server.command.util.StringReader;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.command.ArgumentRegistry;
import me.lauriichan.laylib.command.CommandManager;
import me.lauriichan.laylib.command.CommandProcess;
import me.lauriichan.laylib.command.IArgumentType;
import me.lauriichan.laylib.command.Node;
import me.lauriichan.laylib.command.NodeAction;
import me.lauriichan.laylib.command.NodeArgument;
import me.lauriichan.laylib.command.NodeCommand;
import me.lauriichan.laylib.command.util.Triple;
import me.lauriichan.laylib.localization.Key;
import me.lauriichan.laylib.reflection.ClassUtil;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.logger.IDelegateLogger;

public final class Console implements IDelegateLogger, AutoCloseable {

    public static final Console INSTANCE = new Console();

    private static final String RESET = "\u001B[0m";

    private static final String GRAY = "\u001B[38;5;15m";
    private static final String YELLOW = "\u001B[38;5;226m";
    private static final String RED = "\u001B[38;5;197m";
    private static final String CYAN = "\u001B[38;5;87m";
    private static final String GREEN = "\u001B[38;5;84m";
    private static final String PURPLE = "\u001B[38;5;128m";

    private static final String CUSTOM = PURPLE + "%s" + RESET + "\n";
    private static final String INFO = GRAY + "%s" + RESET + "\n";
    private static final String WARNING = YELLOW + "%s" + RESET + "\n";
    private static final String ERROR = RED + "%s" + RESET + "\n";
    private static final String DEBUG = CYAN + "%s" + RESET + "\n";
    private static final String TRACE = GREEN + "%s" + RESET + "\n";

    private final Terminal terminal;

    private final PrintWriter writer;
    private final JLineReader reader;

    private final ExecutorService commandExecutor = Executors.newSingleThreadExecutor(Thread.ofVirtual().name("CommandExecutor").factory());

    private CommandManager commandManager;
    private ConsoleActor consoleActor;

    private Console() {
        if (INSTANCE != null) {
            throw new UnsupportedOperationException("Singleton");
        }
        try {
            this.terminal = TerminalBuilder.builder().system(true).build();
            this.writer = terminal.writer();
            this.reader = new JLineReader(terminal);
            reader.setParser(new DefaultParser());
            reader.setHistory(new DefaultHistory());
            reader.setKeyMap(JLineReader.MAIN);
            reader.variable(JLineReader.WORDCHARS, "*?_-.[]~&;!#$%^(){}<>");
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize terminal", e);
        }
    }

    @SuppressWarnings("unchecked")
    public void setup(SnowFrame<? extends ServerApp<?>> snowFrame) {
        if (this.commandManager != null) {
            return;
        }
        this.commandManager = new CommandManager(snowFrame.logger());
        commandManager.setPrefix("");
        this.consoleActor = snowFrame.app().consoleActor();
        ArgumentRegistry registry = commandManager.getRegistry();
        snowFrame.extension(IArgumentExtension.class, false)
            .callClasses(cls -> registry.registerArgumentType((Class<? extends IArgumentType<?>>) cls));
        snowFrame.extension(IProviderArgumentExtension.class, true).callInstances(registry::setProvider);
        snowFrame.extension(ICommandExtension.class, false).callClasses(commandManager::register);
        execute("help");
    }

    public void execute(String commandLine) {
        execute(consoleActor, commandLine);
    }

    public void execute(Actor<?> actor, String commandLine) {
        if (commandManager == null) {
            return;
        }
        commandExecutor.submit(() -> executeCommand(actor, commandLine));
    }

    private void executeCommand(Actor<?> actor, String commandLine) {
        if (commandLine.startsWith("/")) {
            commandLine = commandLine.substring(1);
        }
        CommandArguments args = CommandUtil.toArguments(commandLine);
        final Triple<NodeCommand, Node, String> triple = commandManager.findNode(args.commandName(), args.arguments());
        if (triple == null) {
            actor.sendTranslatedMessage(CommandManagerMessage.CREATE_NO$COMMAND.id(), Key.of("command", args.commandName()));
            return;
        }
        final NodeCommand command = triple.getA();
        if (command.isRestricted() && !actor.hasPermission(command.getPermission())) {
            actor.sendTranslatedMessage(CommandManagerMessage.NOT$PERMITTED.id(), Key.of("permission", command.getPermission()));
            return;
        }
        final Node node = triple.getB();
        final String commandPath = triple.getC().substring(1);
        if (node.getAction() == null) {
            actor.sendTranslatedMessage(CommandManagerMessage.CREATE_NO$ACTION.id(), Key.of("command", commandPath));
            return;
        }
        final NodeAction action = node.getAction();
        if (action.isRestricted() && !actor.hasPermission(action.getPermission())) {
            actor.sendTranslatedMessage(CommandManagerMessage.NOT$PERMITTED.id(), Key.of("permission", action.getPermission()));
            return;
        }
        final CommandProcess process = new CommandProcess(triple.getC(), action, command.getInstance());
        NodeArgument argument = process.findNext(actor);
        if (argument == null) {
            commandManager.executeProcess(actor, process);
            return;
        }
        final StringReader reader = new StringReader(CommandUtil.recombine(commandPath, args.arguments()));
        String data;
        while (argument != null) {
            if (!reader.skipWhitespace().hasNext()) {
                if (argument.isOptional()) {
                    process.skip(actor);
                    argument = process.findNext(actor);
                    continue;
                }
                actor.sendTranslatedMessage(CommandManagerMessage.INPUT_FAILED.id(),
                    Key.of("argument.type", ClassUtil.getClassName(argument.getArgumentType())), Key.of("error", "Argument not provided!"));
                return;
            }
            data = reader.read();
            try {
                process.provide(actor, data);
            } catch (final IllegalArgumentException exp) {
                actor.sendTranslatedMessage(CommandManagerMessage.INPUT_FAILED.id(),
                    Key.of("argument.type", ClassUtil.getClassName(argument.getArgumentType())), Key.of("error", exp.getMessage()));
                return;
            }
            argument = process.findNext(actor);
        }
        commandManager.executeProcess(actor, process);
    }

    @Override
    public void custom(String message) {
        writer.printf(CUSTOM, message);
        writer.flush();
    }

    @Override
    public void info(String message) {
        writer.printf(INFO, message);
        writer.flush();
    }

    @Override
    public void warning(String message) {
        writer.printf(WARNING, message);
        writer.flush();
    }

    @Override
    public void error(String message) {
        writer.printf(ERROR, message);
        writer.flush();
    }

    @Override
    public void track(String message) {
        writer.printf(TRACE, message);
        writer.flush();
    }

    @Override
    public void debug(String message) {
        writer.printf(DEBUG, message);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        terminal.close();
        commandExecutor.close();
    }

    public String readLine() {
        String line = reader.readLine();
        ParsedLine parsed = reader.getParsedLine();
        if (parsed != null) {
            return parsed.line();
        }
        return line;
    }

    /*
     * Reader
     */

    private static class JLineReader extends LineReaderImpl {

        public JLineReader(Terminal terminal) throws IOException {
            super(terminal, terminal.getName(), new Object2ObjectOpenHashMap<>());
        }

    }

}
