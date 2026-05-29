package de.brickforceaurora.maven.server.match;

import static me.lauriichan.maven.sourcemod.api.SourceTransformerUtils.importClass;
import static me.lauriichan.maven.sourcemod.api.SourceTransformerUtils.removeMethod;

import java.util.List;

import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import de.brickforceaurora.server.net.PacketHandler;
import me.lauriichan.maven.sourcemod.api.ISourceTransformer;

public final class GameModeNetListenerTransformer implements ISourceTransformer {

    private static final String NET_HANDLER_CONTAINER_TYPE = "de.brickforceaurora.server.match.gamemode.GameNetHandlerContainer";
    private static final String INET_LISTENER_TYPE = "de.brickforceaurora.server.match.gamemode.IGameNetListener";
    private static final String NET_HANDLER_TYPE = "de.brickforceaurora.server.match.gamemode.GameNetHandler";
    private static final String ROOM_TYPE = "de.brickforceaurora.server.match.room.Room";
    private static final String NET_CONTEXT_TYPE = "de.brickforceaurora.server.net.NetContext";

    @Override
    public boolean canTransform(final JavaSource<?> source) {
        if (!(source instanceof final JavaClassSource classSource)) {
            return false;
        }
        return !classSource.isAbstract() && !classSource.isRecord() && classSource.hasInterface(INET_LISTENER_TYPE);
    }

    @Override
    public void transform(final JavaSource<?> source) {
        final JavaClassSource clazz = (JavaClassSource) source;
        
        String gameDataType = clazz.getInterfaces().stream().filter(intf -> intf.startsWith(INET_LISTENER_TYPE)).findFirst().orElseThrow();
        gameDataType = gameDataType.substring(INET_LISTENER_TYPE.length() + 1);
        gameDataType = gameDataType.substring(0, gameDataType.length() - 1);
        
        StringBuilder containerBuilder = new StringBuilder("""
            @SuppressWarnings("unchecked")
            @Override
            public GameNetHandlerContainer<%1$s> newContainer() {
                return new GameNetHandlerContainer<%1$s>(this, new GameNetHandler[] {
            """.formatted(gameDataType));
        int amount = 0;
        for (final MethodSource<JavaClassSource> method : clazz.getMethods()) {
            if (!method.hasAnnotation(PacketHandler.class)
                || (!method.getReturnType().isType(void.class) && !method.getReturnType().isType(Void.class))) {
                continue;
            }
            final List<ParameterSource<JavaClassSource>> params = method.getParameters();
            if (params.size() != 3) {
                continue;
            }
            Type<JavaClassSource> paramType = params.get(0).getType();
            if (!paramType.isType(ROOM_TYPE)) {
                continue;
            }
            paramType = params.get(1).getType();
            if (!paramType.isType(gameDataType)) {
                System.out.println(paramType.getName());
                System.out.println(gameDataType);
                continue;
            }
            paramType = params.get(2).getType();
            if (!paramType.isType(NET_CONTEXT_TYPE) || !paramType.isParameterized()) {
                continue;
            }
            final Type<JavaClassSource> packetType = paramType.getTypeArguments().get(0);
            if (amount++ != 0) {
                containerBuilder.append(",");
            }
            containerBuilder.append("\n\t\tnew GameNetHandler<").append(gameDataType).append(", ").append(packetType.getQualifiedName()).append(">(").append(packetType.getQualifiedName()).append(".class, this::")
                .append(method.getName()).append(')');
        }
        if (amount == 0) {
            return;
        }

        removeMethod(clazz, "newContainer");

        importClass(clazz, NET_HANDLER_CONTAINER_TYPE);
        importClass(clazz, NET_HANDLER_TYPE);

        containerBuilder.append('\n').append("""
                });
            }
            """);
        clazz.addMethod(containerBuilder.toString());
        containerBuilder = null;
    }

}
