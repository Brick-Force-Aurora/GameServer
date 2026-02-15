package de.brickforceaurora.maven.server;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.jboss.forge.roaster.model.source.JavaClassSource;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.lauriichan.maven.sourcemod.api.ISourceGenerator;
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
public class PacketRegistryGenerator implements ISourceGenerator {

    public PacketRegistryGenerator(Properties properties) {
        System.out.println(properties.toString());
    }

    @Override
    public void generateSources(final SourcePackage root) {
        final SourcePackage protoRoot = root.getOrCreatePackage("de.brickforceaurora.server.net.protocol");
        JavaClassSource registry = protoRoot.createClass("PacketRegistry");
        registry.addImport(Object2IntMap.class);
        registry.addImport(Object2IntMaps.class);
        registry.addImport(Object2IntArrayMap.class);
        registry.addImport(Int2ObjectMap.class);
        registry.addImport(Int2ObjectMaps.class);
        registry.addImport(Int2ObjectArrayMap.class);
        registry.addImport(Supplier.class);

        registry.setFinal(true);
        registry.addMethod().setConstructor(true).setPrivate().setBody("throw new UnsupportedOperationException();");

        registry.addField().setPrivate().setStatic(true).setFinal(true).setName("PACKET_ID_MAP")
            .setType("Object2IntMap<Class<? extends IPacket>>");
        registry.addField().setPrivate().setStatic(true).setFinal(true).setName("CLIENTBOUND_PACKETS")
            .setType("Int2ObjectMap<Supplier<? extends IClientboundPacket>>");
        registry.addField().setPrivate().setStatic(true).setFinal(true).setName("SERVERBOUND_PACKETS")
            .setType("Int2ObjectMap<Supplier<? extends IServerboundPacket>>");

        registry.addMethod("""
            public static IClientboundPacket newClientPacket(int id) {
                Supplier<? extends IClientboundPacket> supplier = CLIENTBOUND_PACKETS.get(id);
                if (supplier == null) {
                    return null;
                }
                return supplier.get();
            }
            """);
        registry.addMethod("""
            public static IServerboundPacket newServerPacket(int id) {
                Supplier<? extends IServerboundPacket> supplier = SERVERBOUND_PACKETS.get(id);
                if (supplier == null) {
                    return null;
                }
                return supplier.get();
            }
            """);

        registry.addMethod("""
            public static int packetIdByType(Class<? extends IPacket> type) {
                int id = PACKET_ID_MAP.getInt(type);
                if (id == Integer.MIN_VALUE) {
                    throw new IllegalArgumentException("Unknown packet type '%s'".formatted(type.getName()));
                }
                return id;
            }
            """);
        registry.addMethod("""
            private static <P extends IPacket> void register(Object2IntArrayMap<Class<? extends IPacket>> packetIdMap, Int2ObjectArrayMap<Supplier<? extends P>> boundPackets, Supplier<? extends P> pktSupplier, Class<? extends IPacket> pktType) {
                if (packetIdMap.containsKey(pktType)) {
                    throw new IllegalArgumentException("Packet type '%s' is already registered".formatted(pktType.getName()));
                }
                var packet = pktSupplier.get();
                if (packetIdMap.containsValue(packet.packetId())) {
                    throw new IllegalArgumentException("There is already a packet registered with id '%s'!".formatted(packet.packetId()));
                }
                packetIdMap.put(pktType, packet.packetId());
                boundPackets.put(packet.packetId(), pktSupplier);
            }
            """);

        registerPackets(registry, "Clientbound", protoRoot);
        registerPackets(registry, "Serverbound", protoRoot);

        registry.addInitializer("""
            static {
                var packetIdMap = new Object2IntArrayMap<Class<? extends IPacket>>();
                packetIdMap.defaultReturnValue(Integer.MIN_VALUE);
                var clientbound = new Int2ObjectArrayMap<Supplier<? extends IClientboundPacket>>();
                var serverbound = new Int2ObjectArrayMap<Supplier<? extends IServerboundPacket>>();
                registerClientbound(packetIdMap, clientbound);
                registerServerbound(packetIdMap, serverbound);
                CLIENTBOUND_PACKETS = Int2ObjectMaps.unmodifiable(clientbound);
                SERVERBOUND_PACKETS = Int2ObjectMaps.unmodifiable(serverbound);
                PACKET_ID_MAP = Object2IntMaps.unmodifiable(packetIdMap);
            }
            """);
    }

    private void registerPackets(JavaClassSource registry, String name, SourcePackage protoRoot) {
        StringBuilder builder = new StringBuilder("""
            private static void register%1$s(Object2IntArrayMap<Class<? extends IPacket>> packetIdMap, Int2ObjectArrayMap<Supplier<? extends I%1$sPacket>> boundPackets) {
            """
            .formatted(name));
        forEachPacket(protoRoot.getDirectPackage(name.toLowerCase()), cls -> {
            String clsName = cls.getQualifiedName();
            builder.append("register").append("(packetIdMap, boundPackets, ").append(clsName).append("::new, ").append(clsName).append(".class);");
        });
        System.out.println(builder.append('}').toString());
        registry.addMethod(builder.toString());
    }

    private void forEachPacket(SourcePackage rootPkg, Consumer<JavaClassSource> pktConsumer) {
        ObjectArrayList<SourcePackage> packageQueue = new ObjectArrayList<>();
        packageQueue.push(rootPkg);
        SourcePackage pkg;
        while (!packageQueue.isEmpty()) {
            pkg = packageQueue.pop();
            pkg.getPackages().forEach(packageQueue::push);
            pkg.classStream().forEach(pktConsumer);
        }
    }

}
