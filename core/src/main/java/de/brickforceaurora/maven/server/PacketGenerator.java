package de.brickforceaurora.maven.server;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import de.brickforceaurora.server.net.protocol.PacketBuf;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;
import me.lauriichan.maven.sourcemod.api.ISourceGenerator;
import me.lauriichan.maven.sourcemod.api.SourceTransformerUtils;
import me.lauriichan.maven.sourcemod.api.source.SourcePackage;

/**
 * The packet generator is used to transform the "raw_packets.json", which has
 * been generated from the original CSharp code, to java classes.
 * 
 * This class generates each packet (REQ packets => Serverbound / ACK packets =>
 * Clientbound) and a PacketRegistry class.
 * 
 * The packet generator won't do anything if the PacketRegistry class exists in
 * the main source code.
 */
public class PacketGenerator implements ISourceGenerator {

    private static final ObjectOpenHashSet<String> CLIENTBOUND_UNSUPPORTED = new ObjectOpenHashSet<>(),
        SERVERBOUND_UNSUPPORTED = new ObjectOpenHashSet<>();
    private static final ObjectOpenHashSet<String> CLIENTBOUND_UNKNOWN = new ObjectOpenHashSet<>(),
        SERVERBOUND_UNKNOWN = new ObjectOpenHashSet<>();

    public PacketGenerator(Properties properties) {
        System.out.println(properties.toString());
        // Uncomment this when wanting to know which classes are most definitely broken :)
        // or which types are missing from the conversion (which there shouldn't be any if nothing was changed)
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Unknown (Clientbound): " + CLIENTBOUND_UNKNOWN.toString());
            System.out.println("Unknown (Serverbound): " + SERVERBOUND_UNKNOWN.toString());
            System.out.println("Unsupported (Clientbound): " + CLIENTBOUND_UNSUPPORTED.toString());
            System.out.println("Unsupported (Serverbound): " + SERVERBOUND_UNSUPPORTED.toString());
        }));
        CLIENTBOUND_UNSUPPORTED.iterator();
        
    }

    @Override
    public void generateSources(final SourcePackage root) {
        final Path path = Paths.get("raw_packets.json");
        if (!Files.exists(path)) {
            return;
        }
        final SourcePackage orgRoot = root.getOrCreatePackage("de.brickforceaurora.server.net.protocol");
        final SourcePackage packetRoot = root.getOrCreatePackage("de.brickforceaurora.server.net.newprotocol");
        if (packetRoot.hasDirectSource("PacketRegistry")) {
            return;
        }
        JsonArray packets;
        try (InputStream input = path.getFileSystem().provider().newInputStream(path)) {
            final IJson<?> json = JsonParser.fromStream(input);
            if (json == null || !json.isArray()) {
                return;
            }
            packets = json.asJsonArray();
        } catch (final Throwable throwable) {
            throw new IllegalStateException("Failed to read raw_packets.json", throwable);
        }

        final SourcePackage clientboundRoot = packetRoot.getOrCreatePackage("clientbound");
        final SourcePackage serverboundRoot = packetRoot.getOrCreatePackage("serverbound");

        final JavaInterfaceSource serverboundBase = orgRoot.findInterface("IServerboundPacket").orElse(null);
        final JavaInterfaceSource clientboundBase = orgRoot.findInterface("IClientboundPacket").orElse(null);

        for (final IJson<?> entry : packets) {
            final JsonObject object = entry.asJsonObject();
            String name = object.getAsString("name") + "Packet";
            if (name.contains("Usermap")) {
                name = name.replace("Usermap", "UserPrivateMap");
            }
            SourcePackage pkg;
            if (name.startsWith("Serverbound")) {
                pkg = serverboundRoot;
            } else {
                pkg = clientboundRoot;
            }
            if (pkg.hasDirectSource(name)) {
                continue;
            }
            final JavaClassSource packet = pkg.createClass(name);
            packet.setFinal(true);
            if (pkg == serverboundRoot) {
                if (serverboundBase != null) {
                    packet.addImport(serverboundBase);
                    packet.implementInterface(serverboundBase);
                }
            } else if (clientboundBase != null) {
                packet.addImport(clientboundBase);
                packet.implementInterface(clientboundBase);
            }
            if (pkg == serverboundRoot) {
                handleServerbound(packet, object);
            } else {
                handleClientbound(packet, object);
            }
        }
    }

    private void handleClientbound(final JavaClassSource source, final JsonObject data) {
        int unknownCounter = 0, unsupportedCounter = 0, unnamedCounter = 0;
        boolean hasSequence = false;
        final StringBuilder writeOutput = new StringBuilder();
        for (final IJson<?> valueEntry : data.getAsArray("values")) {
            final JsonObject value = valueEntry.asJsonObject();
            String name = value.getAsString("name");
            final String type = value.getAsString("type");
            switch (name) {
            case "MyInfoManager.Instance.Seq":
            case "seq":
                hasSequence = true;
                continue;
            case "_":
                name = "Unnamed%s".formatted(unnamedCounter++);
                break;
            case "hashCode":
                name = "hash";
                break;
            case null:
            default:
                break;
            }
            switch (type) {
            case "UNKNOWN" -> {
                final String fieldName = "UnknownValue%s".formatted(unknownCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true).setStringInitializer(name);
            }
            case "int" -> {
                if ("seq".equals(name)) {
                    name = "clientId";
                }
                addField(source, name, "int");
                writeOutput.append("""
                    buf.writeInt(this.%1$s);
                    """.formatted(name));
            }
            case "uint" -> {
                addRemappedNumberField(source, name, "long", 0, 4294967295L);
                writeOutput.append("""
                    buf.writeInt(this.%1$s);
                    """.formatted(name));
            }
            case "string" -> {
                addField(source, name, "String");
                writeOutput.append("""
                    buf.writeString(this.%1$s);
                    """.formatted(name));
            }
            case "long" -> {
                addField(source, name, "long");
                writeOutput.append("""
                    buf.writeLong(this.%1$s);
                    """.formatted(name));
            }
            case "bool" -> {
                addField(source, name, "boolean");
                writeOutput.append("""
                    buf.writeBoolean(this.%1$s);
                    """.formatted(name));
            }
            case "byte" -> {
                addRemappedNumberField(source, name, "int", 0, 255);
                writeOutput.append("""
                    buf.writeByte(this.%1$s);
                    """.formatted(name));
            }
            case "sbyte" -> {
                addField(source, name, "byte");
                writeOutput.append("""
                    buf.writeByte(this.%1$s);
                    """.formatted(name));
            }
            case "ushort" -> {
                addRemappedNumberField(source, name, "int", 0, 65535);
                writeOutput.append("""
                    buf.writeShort(this.%1$s);
                    """.formatted(name));
            }
            case "float" -> {
                addField(source, name, "float");
                writeOutput.append("""
                    buf.writeFloat(this.%1$s);
                    """.formatted(name));
            }
            default -> {
                CLIENTBOUND_UNSUPPORTED.add(type);
                final String fieldName = "UnsupportedValue%s".formatted(unsupportedCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true)
                    .setStringInitializer("name:'%s',type:'%s'".formatted(name, type));
            }
            }
        }

        if (unknownCounter != 0) {
            CLIENTBOUND_UNKNOWN.add(source.getName());
        }

        source.addMethod().setPublic().setName("packetId").setReturnType("int").setBody("return %s;".formatted(data.getAsInt("id")))
            .addAnnotation("Override");

        if (hasSequence) {
            source.addMethod().setPublic().setName("requiresClientId").setReturnType("boolean").setBody("return true;")
                .addAnnotation("Override");
        }

        SourceTransformerUtils.importClass(source, PacketBuf.class);
        source.addMethod("""
            @Override
            public final void write(PacketBuf buf) {
                %s
            }
            """.formatted(writeOutput.toString()));
        source.removeMethod(source.getMethod("write", "PacketBuf"));
    }

    private void handleServerbound(final JavaClassSource source, final JsonObject data) {
        int unknownCounter = 0, unsupportedCounter = 0, unnamedCounter = 0;
        final StringBuilder readOutput = new StringBuilder();
        for (final IJson<?> valueEntry : data.getAsArray("values")) {
            final JsonObject value = valueEntry.asJsonObject();
            String name = value.getAsString("name");
            final String type = value.getAsString("type");
            if ("_".equals(name)) {
                name = "Unnamed%s".formatted(unnamedCounter++);
            } else if ("hashCode".equals(name)) {
                name = "hash";
            }
            switch (type) {
            case "UNKNOWN" -> {
                final String fieldName = "UnknownValue%s".formatted(unknownCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true).setStringInitializer(name);
            }
            case "int" -> {
                if ("seq".equals(name)) {
                    name = "clientId";
                }
                addField(source, name, "int");
                readOutput.append("""
                    this.%1$s = buf.readInt();
                    """.formatted(name));
            }
            case "string" -> {
                addField(source, name, "String");
                readOutput.append("""
                    this.%1$s = buf.readString();
                    """.formatted(name));
            }
            case "long" -> {
                addField(source, name, "long");
                readOutput.append("""
                    this.%1$s = buf.readLong();
                    """.formatted(name));
            }
            case "bool" -> {
                addField(source, name, "boolean");
                readOutput.append("""
                    this.%1$s = buf.readBoolean();
                    """.formatted(name));
            }
            case "byte" -> {
                addRemappedNumberField(source, name, "int", 0, 255);
                readOutput.append("""
                    this.%1$s = buf.readUnsignedByte();
                    """.formatted(name));
            }
            case "sbyte" -> {
                addField(source, name, "byte");
                readOutput.append("""
                    this.%1$s = buf.readByte();
                    """.formatted(name));
            }
            case "ushort" -> {
                addRemappedNumberField(source, name, "int", 0, 65535);
                readOutput.append("""
                    this.%1$s = buf.readUnsignedShort();
                    """.formatted(name));
            }
            case "float" -> {
                addField(source, name, "float");
                readOutput.append("""
                    this.%1$s = buf.readFloat();
                    """.formatted(name));
            }
            default -> {
                SERVERBOUND_UNSUPPORTED.add(type);
                final String fieldName = "UnsupportedValue%s".formatted(unsupportedCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true)
                    .setStringInitializer("name:'%s',type:'%s'".formatted(name, type));
            }
            }
        }

        if (unknownCounter != 0) {
            SERVERBOUND_UNKNOWN.add(source.getName());
        }

        source.addMethod().setPublic().setName("packetId").setReturnType("int").setBody("return %s;".formatted(data.getAsInt("id")))
            .addAnnotation("Override");

        SourceTransformerUtils.importClass(source, PacketBuf.class);
        source.addMethod("""
            @Override
            public final void read(PacketBuf buf) {
                %s
            }
            """.formatted(readOutput.toString()));
        source.removeMethod(source.getMethod("read", "PacketBuf"));
    }

    private void addField(final JavaClassSource source, final String name, final String type) {
        source.addField().setPrivate().setType(type).setName(name);
        source.addMethod().setPublic().setFinal(true).setReturnType(source).setName(name).setBody("""
            this.%1$s = %1$s;
            return this;
            """.formatted(name)).addParameter(type, name);
        source.addMethod().setPublic().setFinal(true).setReturnType(type).setName(name).setBody("return this.%1$s;".formatted(name));
    }

    private void addRemappedNumberField(final JavaClassSource source, final String name, final String type, final long minValue,
        final long maxValue) {
        source.addField().setPrivate().setType(type).setName(name);
        source.addMethod().setPublic().setFinal(true).setReturnType(source).setName(name).setBody("""
            if (%1$s > %2$sL || %1$s < %3$sL) {
                throw new IllegalArgumentException("Value " + %1$s + " is out of bounds of allowed number range of %3$s - %2$s");
            }
            this.%1$s = %1$s;
            return this;
            """.formatted(name, maxValue, minValue)).addParameter(type, name);
        source.addMethod().setPublic().setFinal(true).setReturnType(type).setName(name).setBody("return this.%1$s;".formatted(name));
    }

}
