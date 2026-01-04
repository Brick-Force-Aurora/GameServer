package de.brickforceaurora.maven;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
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

    public PacketGenerator() {
        // Uncomment this when wanting to know which classes are most definitely broken :)
        // or which types are missing from the conversion (which there shouldn't be any if nothing was changed)
        /*
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Unknown (Clientbound): " + CLIENTBOUND_UNKNOWN.toString());
            System.out.println("Unknown (Serverbound): " + SERVERBOUND_UNKNOWN.toString());
            System.out.println("Unsupported (Clientbound): " + CLIENTBOUND_UNSUPPORTED.toString());
            System.out.println("Unsupported (Serverbound): " + SERVERBOUND_UNSUPPORTED.toString());
        }));
        CLIENTBOUND_UNSUPPORTED.iterator();
        */
    }

    @Override
    public void generateSources(SourcePackage root) {
        Path path = Paths.get("raw_packets.json");
        if (!Files.exists(path)) {
            return;
        }
        SourcePackage packetRoot = root.getOrCreatePackage("de.brickforceaurora.gameserver.net.protocol");
        if (packetRoot.hasDirectSource("PacketRegistry")) {
            return;
        }
        JsonArray packets;
        try (InputStream input = path.getFileSystem().provider().newInputStream(path)) {
            IJson<?> json = JsonParser.fromStream(input);
            if (json == null || !json.isArray()) {
                return;
            }
            packets = json.asJsonArray();
        } catch (Throwable throwable) {
            throw new IllegalStateException("Failed to read raw_packets.json", throwable);
        }

        SourcePackage clientboundRoot = packetRoot.getOrCreatePackage("clientbound");
        SourcePackage serverboundRoot = packetRoot.getOrCreatePackage("serverbound");

        JavaInterfaceSource serverboundBase = packetRoot.findInterface("IServerboundPacket").orElse(null);
        JavaInterfaceSource clientboundBase = packetRoot.findInterface("IClientboundPacket").orElse(null);

        JavaClassSource packetRegistry = packetRoot.createClass("PacketRegistry");
        packetRegistry.setFinal(true);
        packetRegistry.addMethod().setConstructor(true).setPrivate().setBody("throw new UnsupportedOperationException();");

        packetRegistry.addField().setPrivate().setStatic(true).setFinal(true).setName("CLIENTBOUND_PACKETS")
            .setType("Int2ObjectMap<Supplier<? extends IClientboundPacket>>");
        packetRegistry.addField().setPrivate().setStatic(true).setFinal(true).setName("SERVERBOUND_PACKETS")
            .setType("Int2ObjectMap<Supplier<? extends IServerboundPacket>>");

        StringBuilder clientBuilder = new StringBuilder();
        StringBuilder serverBuilder = new StringBuilder();

        for (IJson<?> entry : packets) {
            JsonObject object = entry.asJsonObject();
            String name = object.getAsString("name") + "Packet";
            if (name.contains("Usermap")) {
                name = name.replace("Usermap", "UserPrivateMap");
            }
            SourcePackage pkg;
            if (name.startsWith("Serverbound")) {
                pkg = serverboundRoot;
                serverBuilder.append("serverbound.put(").append(object.getAsInt("id")).append(", ").append(name).append("::new);");
            } else {
                pkg = clientboundRoot;
                clientBuilder.append("clientbound.put(").append(object.getAsInt("id")).append(", ").append(name).append("::new);");
            }
            if (pkg.hasDirectSource(name)) {
                packetRegistry.addImport(pkg.getDirectSource(name));
                continue;
            }
            JavaClassSource packet = pkg.createClass(name);
            packetRegistry.addImport(packet);
            packet.setFinal(true);
            if (pkg == serverboundRoot) {
                if (serverboundBase != null) {
                    packet.implementInterface(serverboundBase);
                }
            } else if (clientboundBase != null) {
                packet.implementInterface(clientboundBase);
            }
            if (pkg == serverboundRoot) {
                handleServerbound(packet, object);
            } else {
                handleClientbound(packet, object);
            }
        }

        packetRegistry.addImport(Int2ObjectMap.class);
        packetRegistry.addImport(Int2ObjectMaps.class);
        packetRegistry.addImport(Int2ObjectArrayMap.class);
        packetRegistry.addImport(Supplier.class);
        if (clientboundBase != null) {
            packetRegistry.addImport(clientboundBase);
        }
        if (serverboundBase != null) {
            packetRegistry.addImport(serverboundBase);
        }
        packetRegistry.addInitializer("""
            static {
                var clientbound = new Int2ObjectArrayMap<Supplier<? extends IClientboundPacket>>();
                %s
                CLIENTBOUND_PACKETS = Int2ObjectMaps.unmodifiable(clientbound);
                var serverbound = new Int2ObjectArrayMap<Supplier<? extends IServerboundPacket>>();
                %s
                SERVERBOUND_PACKETS = Int2ObjectMaps.unmodifiable(serverbound);
            }
            """.formatted(clientBuilder.toString(), serverBuilder.toString()));

        packetRegistry.addMethod("""
            public static IClientboundPacket newClientPacket(int id) {
                Supplier<? extends IClientboundPacket> supplier = CLIENTBOUND_PACKETS.get(id);
                if (supplier == null) {
                    return null;
                }
                return supplier.get();
            }
            """);
        packetRegistry.addMethod("""
            public static IServerboundPacket newServerPacket(int id) {
                Supplier<? extends IServerboundPacket> supplier = SERVERBOUND_PACKETS.get(id);
                if (supplier == null) {
                    return null;
                }
                return supplier.get();
            }
            """);
    }

    private void handleClientbound(JavaClassSource source, JsonObject data) {
        int unknownCounter = 0, unsupportedCounter = 0, unnamedCounter = 0;
        boolean hasSequence = false;
        StringBuilder writeOutput = new StringBuilder();
        for (IJson<?> valueEntry : data.getAsArray("values")) {
            JsonObject value = valueEntry.asJsonObject();
            String name = value.getAsString("name"), type = value.getAsString("type");
            if (name.equals("MyInfoManager.Instance.Seq") || name.equals("seq")) {
                hasSequence = true;
                continue;
            }
            if (name.equals("_")) {
                name = "Unnamed%s".formatted(unnamedCounter++);
            } else if (name.equals("hashCode")) {
                name = "hash";
            }
            switch (type) {
            case "UNKNOWN" -> {
                String fieldName = "UnknownValue%s".formatted(unknownCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true).setStringInitializer(name);
            }
            case "int" -> {
                if (name.equals("seq")) {
                    name = "clientId";
                }
                addField(source, name, "int");
                writeOutput.append("""
                    buffer.writeIntLE(this.%1$s);
                    """.formatted(name));
            }
            case "uint" -> {
                addRemappedNumberField(source, name, "long", 0, 4294967295L);
                writeOutput.append("""
                    buffer.writeIntLE((int) this.%1$s);
                    """.formatted(name));
            }
            case "string" -> {
                SourceTransformerUtils.importClass(source, StandardCharsets.class);
                addField(source, name, "String");
                writeOutput.append("""
                    if (this.%1$s.isEmpty()) {
                        buffer.writeIntLE(0);
                    } else {
                        byte[] bytes = this.%1$s.getBytes(StandardCharsets.UTF_16LE);
                        buffer.writeIntLE(bytes.length);
                        buffer.writeBytes(bytes);
                    }
                    """.formatted(name));
            }
            case "long" -> {
                addField(source, name, "long");
                writeOutput.append("""
                    buffer.writeLongLE(this.%1$s);
                    """.formatted(name));
            }
            case "bool" -> {
                addField(source, name, "boolean");
                writeOutput.append("""
                    buffer.writeBoolean(this.%1$s);
                    """.formatted(name));
            }
            case "byte" -> {
                addRemappedNumberField(source, name, "int", 0, 255);
                writeOutput.append("""
                    buffer.writeByte(this.%1$s & 0xFF);
                    """.formatted(name));
            }
            case "sbyte" -> {
                addField(source, name, "byte");
                writeOutput.append("""
                    buffer.writeByte(this.%1$s);
                    """.formatted(name));
            }
            case "ushort" -> {
                addRemappedNumberField(source, name, "int", 0, 32767);
                writeOutput.append("""
                    buffer.writeShortLE(this.%1$s & 0xFFFF);
                    """.formatted(name));
            }
            case "float" -> {
                addField(source, name, "float");
                writeOutput.append("""
                    buffer.writeFloatLE(this.%1$s);
                    """.formatted(name));
            }
            default -> {
                CLIENTBOUND_UNSUPPORTED.add(type);
                String fieldName = "UnsupportedValue%s".formatted(unsupportedCounter++);
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

        SourceTransformerUtils.importClass(source, ByteBuf.class);
        source.addMethod("""
            @Override
            public final void write(ByteBuf buffer) {
                %s
            }
            """.formatted(writeOutput.toString()));
        source.removeMethod(source.getMethod("write", "ByteBuf"));
    }

    private void handleServerbound(JavaClassSource source, JsonObject data) {
        int unknownCounter = 0, unsupportedCounter = 0, unnamedCounter = 0;
        StringBuilder readOutput = new StringBuilder();
        for (IJson<?> valueEntry : data.getAsArray("values")) {
            JsonObject value = valueEntry.asJsonObject();
            String name = value.getAsString("name"), type = value.getAsString("type");
            if (name.equals("_")) {
                name = "Unnamed%s".formatted(unnamedCounter++);
            } else if (name.equals("hashCode")) {
                name = "hash";
            }
            switch (type) {
            case "UNKNOWN" -> {
                String fieldName = "UnknownValue%s".formatted(unknownCounter++);
                source.addField().setType("String").setName(fieldName).setFinal(true).setStringInitializer(name);
            }
            case "int" -> {
                if (name.equals("seq")) {
                    name = "clientId";
                }
                addField(source, name, "int");
                readOutput.append("""
                    this.%1$s = buffer.readIntLE();
                    """.formatted(name));
            }
            case "string" -> {
                SourceTransformerUtils.importClass(source, StandardCharsets.class);
                addField(source, name, "String");
                readOutput.append("""
                    {
                        int length = buffer.readIntLE();
                        if (length == 0) {
                            this.%1$s = "";
                        } else {
                            byte[] bytes = new byte[length];
                            buffer.readBytes(bytes);
                            this.%1$s = new String(bytes, StandardCharsets.UTF_16LE);
                        }
                    }
                    """.formatted(name));
            }
            case "long" -> {
                addField(source, name, "long");
                readOutput.append("""
                    this.%1$s = buffer.readLongLE();
                    """.formatted(name));
            }
            case "bool" -> {
                addField(source, name, "boolean");
                readOutput.append("""
                    this.%1$s = buffer.readBoolean();
                    """.formatted(name));
            }
            case "byte" -> {
                addRemappedNumberField(source, name, "int", 0, 255);
                readOutput.append("""
                    this.%1$s = buffer.readUnsignedByte();
                    """.formatted(name));
            }
            case "sbyte" -> {
                addField(source, name, "byte");
                readOutput.append("""
                    this.%1$s = buffer.readByte();
                    """.formatted(name));
            }
            case "ushort" -> {
                addRemappedNumberField(source, name, "int", 0, 32767);
                readOutput.append("""
                    this.%1$s = buffer.readUnsignedShortLE();
                    """.formatted(name));
            }
            case "float" -> {
                addField(source, name, "float");
                readOutput.append("""
                    this.%1$s = buffer.readFloatLE();
                    """.formatted(name));
            }
            default -> {
                SERVERBOUND_UNSUPPORTED.add(type);
                String fieldName = "UnsupportedValue%s".formatted(unsupportedCounter++);
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

        SourceTransformerUtils.importClass(source, ByteBuf.class);
        source.addMethod("""
            @Override
            public final void read(ByteBuf buffer) {
                %s
            }
            """.formatted(readOutput.toString()));
        source.removeMethod(source.getMethod("read", "ByteBuf"));
    }

    private void addField(JavaClassSource source, String name, String type) {
        source.addField().setPrivate().setType(type).setName(name);
        source.addMethod().setPublic().setFinal(true).setReturnType(source).setName(name).setBody("""
            this.%1$s = %1$s;
            return this;
            """.formatted(name)).addParameter(type, name);
        source.addMethod().setPublic().setFinal(true).setReturnType(type).setName(name).setBody("return this.%1$s;".formatted(name));
    }

    private void addRemappedNumberField(JavaClassSource source, String name, String type, long minValue, long maxValue) {
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
