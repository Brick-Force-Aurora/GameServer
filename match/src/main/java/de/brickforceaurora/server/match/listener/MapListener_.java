package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundAllMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMyDownloadMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMyRegisterMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUserMapPacket;
import de.brickforceaurora.server.net.protocol.data.GameMode;
import de.brickforceaurora.server.net.protocol.data.MapMeta;
import de.brickforceaurora.server.net.protocol.data.MapTag;
import de.brickforceaurora.server.net.protocol.data.RegisteredMap;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundAllMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMyDownloadMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMyRegisterMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundUserMapPacket;
import me.lauriichan.snowframe.extension.Extension;

import java.time.LocalDateTime;

@Extension
public class MapListener_ implements INetListener {

    private static final RegisteredMap[] MAPS = new RegisteredMap[] {
            new RegisteredMap(71594, "Brick-Force", "Aftermath",
                    GameMode.MANAGER.newImmutable(32),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 8, 13, 13, 2, 21),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(13, "Brick-Force", "Assault",
                    GameMode.MANAGER.newImmutable(9),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2012, 2, 9, 17, 28, 48),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(48184, "Brick-Force", "Aztecy",
                    GameMode.MANAGER.newImmutable(15),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 9, 34, 39),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(11, "Brick-Force", "Barracks",
                    GameMode.MANAGER.newImmutable(259),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2012, 1, 31, 15, 53, 36),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(12, "Brick-Force", "Base Camp",
                    GameMode.MANAGER.newImmutable(1),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 31, 15, 54, 9),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(7, "Brick-Force", "Bus Depot",
                    GameMode.MANAGER.newImmutable(5),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2012, 1, 30, 15, 50, 53),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(10, "Brick-Force", "Cargo Quay",
                    GameMode.MANAGER.newImmutable(5),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 16, 43, 56),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(71595, "Brick-Force", "Caved In",
                    GameMode.MANAGER.newImmutable(32),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 8, 13, 13, 6, 35),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(2, "Brick-Force", "Compound",
                    GameMode.MANAGER.newImmutable(1),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 14, 10, 41),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(9, "Brick-Force", "Deep Tower",
                    GameMode.MANAGER.newImmutable(263),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 16, 36, 34),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(6, "Brick-Force", "Dusty",
                    GameMode.MANAGER.newImmutable(267),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 15, 48, 4),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(274312, "Brick-Force", "Kill Hill",
                    GameMode.MANAGER.newImmutable(271),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 11, 34, 35),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(5, "Brick-Force", "Metro 2012",
                    GameMode.MANAGER.newImmutable(259),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 15, 44, 58),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(39601, "Brick-Force", "Playground",
                    GameMode.MANAGER.newImmutable(32),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 7, 8, 14, 51, 27),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(274317, "Brick-Force", "Saraios",
                    GameMode.MANAGER.newImmutable(271),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 11, 46, 51),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(1, "Brick-Force", "Sky Bridge",
                    GameMode.MANAGER.newImmutable(1),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 13, 49, 51),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(3, "Brick-Force", "Sky Garden",
                    GameMode.MANAGER.newImmutable(1),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 14, 18, 1),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(71596, "Brick-Force", "Skylands",
                    GameMode.MANAGER.newImmutable(32),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 8, 13, 16, 7, 7),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(274314, "Brick-Force", "SniperHill",
                    GameMode.MANAGER.newImmutable(13),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 11, 41, 34),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(24115, "Brick-Force", "Subway",
                    GameMode.MANAGER.newImmutable(17),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(0),
                    LocalDateTime.of(2012, 8, 13, 14, 56, 35),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(71597, "Brick-Force", "Subway",
                    GameMode.MANAGER.newImmutable(17),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 8, 13, 14, 56, 35),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(274316, "Brick-Force", "Terminal",
                    GameMode.MANAGER.newImmutable(271),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 11, 44, 5),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(274313, "Brick-Force", "The Bridge",
                    GameMode.MANAGER.newImmutable(287),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2014, 4, 11, 11, 39, 20),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(8, "Brick-Force", "The Cube",
                    GameMode.MANAGER.newImmutable(2),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 1, 30, 15, 52, 27),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(250344, "Brick-Force", "Valley",
                    GameMode.MANAGER.newImmutable(64),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2013, 12, 13, 13, 47, 38),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(14, "Brick-Force", "Warehouse",
                    GameMode.MANAGER.newImmutable(8),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2012, 2, 13, 17, 18, 24),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(4, "Brick-Force", "Warzone",
                    GameMode.MANAGER.newImmutable(259),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(3),
                    LocalDateTime.of(2012, 1, 30, 15, 26, 29),
                    0, 0, 0, 0, 0, 0),

            new RegisteredMap(281095, "Brick-Force", "WildWest",
                    GameMode.MANAGER.newImmutable(271),
                    MapTag.MANAGER.newImmutable(),
                    MapMeta.MANAGER.newImmutable(2),
                    LocalDateTime.of(2014, 6, 5, 11, 6, 56),
                    0, 0, 0, 0, 0, 0)
    };

    @PacketHandler
    public void onUserMap(final NetContext<ServerboundUserMapPacket> context) {
        int page = context.packet().page();
        context.client().send(new ClientboundUserMapPacket().page(0).maps(null));
    }

    @PacketHandler
    public void onMyRegisterMap(final NetContext<ServerboundMyRegisterMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        context.client().send(new ClientboundMyRegisterMapPacket().page(0).maps(MAPS));
    }

    @PacketHandler
    public void onMyDownloadMap(final NetContext<ServerboundMyDownloadMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        context.client().send(new ClientboundMyDownloadMapPacket().page(0).maps(MAPS));
    }

    @PacketHandler
    public void onAllMap(final NetContext<ServerboundAllMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        int flag = context.packet().flag();
        String filter = context.packet().filter();
        context.client().send(new ClientboundAllMapPacket().page(0).maps(MAPS));
    }
}
