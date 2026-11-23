package de.brickforceaurora.server.net.udp;

import java.util.function.Supplier;

import de.brickforceaurora.server.net.tcp.protocol.IPacket;
import de.brickforceaurora.server.net.udp.protocol.clientbound.ClientboundP2PPongPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PPingPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PReliableAckPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PStatusPacket;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class P2PPacketRegistry {

    private static final Int2ObjectMap<Supplier<? extends IPacket>> PACKETS;

    static {
        final Int2ObjectOpenHashMap<Supplier<? extends IPacket>> packets = new Int2ObjectOpenHashMap<>();
        register(packets, ServerboundP2PPingPacket::new);
        register(packets, ClientboundP2PPongPacket::new);
        register(packets, ServerboundP2PReliableAckPacket::new);
        register(packets, ServerboundP2PStatusPacket::new);
        PACKETS = Int2ObjectMaps.unmodifiable(packets);
    }

    private P2PPacketRegistry() {
        throw new UnsupportedOperationException();
    }

    private static void register(final Int2ObjectMap<Supplier<? extends IPacket>> packets, final Supplier<? extends IPacket> supplier) {
        final int id = supplier.get().packetId();
        if (packets.containsKey(id)) {
            throw new IllegalArgumentException("There is already a P2P packet registered with id '%s'!".formatted(id));
        }
        packets.put(id, supplier);
    }

    public static IPacket create(final int id) {
        final Supplier<? extends IPacket> supplier = PACKETS.get(id);
        return supplier == null ? null : supplier.get();
    }

}
