package de.brickforceaurora.gameserver.legacy.handler;

import java.util.List;

import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.legacy.maps.RegMapManager;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.Room;

public class MapHandlers {

    public static void register(final MessageDispatcher d) {
        d.register(MessageId.CS_DESTROY_BRICK_REQ.id(), MapHandlers::destroyBrick);
        d.register(MessageId.CS_REG_MAP_INFO_REQ.id(), MapHandlers::HandleRegMapInfoRequest);
        d.register(MessageId.CS_MY_REGISTER_MAP_REQ.id(), MapHandlers::HandleRequestRegisteredMaps);
        d.register(MessageId.CS_USER_MAP_REQ.id(), MapHandlers::HandleRequestUserMaps);
        d.register(MessageId.CS_MY_DOWNLOAD_MAP_REQ.id(), MapHandlers::HandleRequestDownloadedMaps);
        d.register(MessageId.CS_ALL_MAP_REQ.id(), MapHandlers::HandleRequestAllMaps);
    }

    private static void HandleRequestDownloadedMaps(final GameServerLogic server, final MsgReference msgRef) {
        final int prevPage = msgRef.msg.msg().readInt();
        final int nextPage = msgRef.msg.msg().readInt();
        final int indexer = msgRef.msg.msg().readInt();
        final int modeMask = msgRef.msg.msg().readUShort();

        server.logger().debug("HandleRequestDownloadedMaps from: " + msgRef.client.GetIdentifier());

        SendDownloadedMaps(server, msgRef.client, nextPage);
    }

    private static void HandleRequestRegisteredMaps(final GameServerLogic server, final MsgReference msgRef) {
        final int prevPage = msgRef.msg.msg().readInt();
        final int nextPage = msgRef.msg.msg().readInt();
        final int indexer = msgRef.msg.msg().readInt();
        final int modeMask = msgRef.msg.msg().readUShort();

        server.logger().debug("HandleRequestRegisteredMaps from: " + msgRef.client.GetIdentifier());

        SendRegisteredMaps(server, msgRef.client, nextPage);
    }

    public static void SendDownloadedMaps(final GameServerLogic server, final ClientReference client, final int page) {
        final MsgBody body = new MsgBody();

        final int mapsPerPage = 12;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int offset = page * mapsPerPage;
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++) {
            final RegMap entry = maps.get(i);
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte) (Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte) entry.getRegisteredDate().getMonth().getValue());
            body.write((byte) entry.getRegisteredDate().getDayOfMonth());
            body.write((byte) entry.getRegisteredDate().getHour());
            body.write((byte) entry.getRegisteredDate().getMinute());
            body.write((byte) entry.getRegisteredDate().getSecond());
            body.write(entry.getDownloadFee());
            body.write(entry.getRelease());
            body.write(entry.getLatestRelease());
            body.write(entry.getLikes());
            body.write(entry.getDisLikes());
            body.write(entry.getDownloadCount());
        }
        server.say(new MsgReference(MessageId.CS_MY_DOWNLOAD_MAP_ACK, body, client));

        server.logger().debug("SendDownloadedMaps to: " + client.GetIdentifier());
    }

    public static void SendRegisteredMaps(final GameServerLogic server, final ClientReference client, final int page) {
        final MsgBody body = new MsgBody();

        final int mapsPerPage = 12;
        final int offset = page * mapsPerPage;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++) {
            final RegMap entry = maps.get(i);
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte) (Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte) entry.getRegisteredDate().getMonth().getValue());
            body.write((byte) entry.getRegisteredDate().getDayOfMonth());
            body.write((byte) entry.getRegisteredDate().getHour());
            body.write((byte) entry.getRegisteredDate().getMinute());
            body.write((byte) entry.getRegisteredDate().getSecond());
            body.write(entry.getDownloadFee());
            body.write(entry.getRelease());
            body.write(entry.getLatestRelease());
            body.write(entry.getLikes());
            body.write(entry.getDisLikes());
            body.write(entry.getDownloadCount());
        }
        server.say(new MsgReference(MessageId.CS_MY_REGISTER_MAP_ACK, body, client));

        server.logger().debug("SendRegisteredMaps to: " + client.GetIdentifier());
    }

    private static void HandleRequestUserMaps(final GameServerLogic server, final MsgReference msgRef) {
        final int page = msgRef.msg.msg().readInt();

        server.logger().debug("HandleRequestUserMaps from: " + msgRef.client.GetIdentifier());

        SendUserMaps(server, msgRef.client, page);
    }

    public static void SendUserMaps(final GameServerLogic server, final ClientReference client, final int page) {
        final MsgBody body = new MsgBody();

        final int mapsPerPage = 12;
        final int offset = page * mapsPerPage;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++) {
            final RegMap entry = maps.get(i);
            body.write(i); //slot
            body.write(entry.getAlias());
            body.write(10000); //brick count
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte) entry.getRegisteredDate().getMonth().getValue());
            body.write((byte) entry.getRegisteredDate().getDayOfMonth());
            body.write((byte) entry.getRegisteredDate().getHour());
            body.write((byte) entry.getRegisteredDate().getMinute());
            body.write((byte) entry.getRegisteredDate().getSecond());
            body.write((byte) 0);
        }
        server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));

        server.logger().debug("SendUserMaps to: " + client.GetIdentifier());
    }

    private static void HandleRegMapInfoRequest(final GameServerLogic server, final MsgReference msgRef) {
        final int mapId = msgRef.msg.msg().readInt();

        server.logger().debug("HandleRegMapInfoRequest from: " + msgRef.client.GetIdentifier());
    }

    private static void HandleRequestAllMaps(final GameServerLogic server, final MsgReference msgRef) {
        final int prevPage = msgRef.msg.msg().readInt();
        final int nextPage = msgRef.msg.msg().readInt();
        final int indexer = msgRef.msg.msg().readInt();
        final int modeMask = msgRef.msg.msg().readUShort();
        final int flag = msgRef.msg.msg().readInt();
        final String filter = msgRef.msg.msg().readString();

        final var maps = RegMapManager.getInstance().getAllMapsPage(prevPage, nextPage, indexer, modeMask, flag, filter);

        SendMapList(server, msgRef.client, nextPage, maps, MessageId.CS_ALL_MAP_ACK);
    }

    private static void SendMapList(final GameServerLogic server, final ClientReference client, final int page, final List<RegMap> maps,
        final MessageId msgId) {
        final MsgBody body = new MsgBody();

        body.write(page);
        body.write(maps.size());

        for (final RegMap entry : maps) {
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte) (Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte) entry.getRegisteredDate().getMonthValue());
            body.write((byte) entry.getRegisteredDate().getDayOfMonth());
            body.write((byte) entry.getRegisteredDate().getHour());
            body.write((byte) entry.getRegisteredDate().getMinute());
            body.write((byte) entry.getRegisteredDate().getSecond());

            body.write(entry.getDownloadFee());
            body.write(entry.getRelease());
            body.write(entry.getLatestRelease());
            body.write(entry.getLikes());
            body.write(entry.getDisLikes());
            body.write(entry.getDownloadCount());
        }

        server.say(new MsgReference(msgId, body, client));
    }

    public static void sendAllUserMaps(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        int chunkSize = 200;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++) {
            final int remaining = maps.size() - processedCount;
            if (remaining < chunkSize) {
                chunkSize = remaining;
            }

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++) {
                final RegMap entry = maps.get(i);
                body.write(i); //slot
                body.write(entry.getAlias());
                body.write(-1); //brick count
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte) entry.getRegisteredDate().getMonth().getValue());
                body.write((byte) entry.getRegisteredDate().getDayOfMonth());
                body.write((byte) entry.getRegisteredDate().getHour());
                body.write((byte) entry.getRegisteredDate().getMinute());
                body.write((byte) entry.getRegisteredDate().getSecond());
                body.write((byte) 0); //premium
            }
            server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));
        }

        server.logger().debug("SendAllUserMaps to: " + client.GetIdentifier());
    }

    public static void sendAllDownloadedMaps(final GameServerLogic server, final ClientReference client) {
        int chunkSize = 100;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++) {
            final int remaining = maps.size() - processedCount;
            if (remaining < chunkSize) {
                chunkSize = remaining;
            }

            final MsgBody body = new MsgBody();

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++) {
                final RegMap entry = maps.get(i);
                body.write(entry.getMap());
                body.write(entry.getDeveloper());
                body.write(entry.getAlias());
                body.writeUShort(entry.getModeMask());
                body.write((byte) (Room.clanMatch | Room.official));
                body.write(entry.tagMask);
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte) entry.getRegisteredDate().getMonth().getValue());
                body.write((byte) entry.getRegisteredDate().getDayOfMonth());
                body.write((byte) entry.getRegisteredDate().getHour());
                body.write((byte) entry.getRegisteredDate().getMinute());
                body.write((byte) entry.getRegisteredDate().getSecond());
                body.write(entry.getDownloadFee());
                body.write(entry.getRelease());
                body.write(entry.getLatestRelease());
                body.write(entry.getLikes());
                body.write(entry.getDisLikes());
                body.write(entry.getDownloadCount());
            }

            server.say(new MsgReference(MessageId.CS_MY_DOWNLOAD_MAP_ACK, body, client));
        }

        server.logger().debug("SendAllDownloadedMaps to: " + client.GetIdentifier());
    }

    public static void sendEmptyUserMap(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        body.write(-1); //page
        body.write(1); //count
        body.write(0); //slot
        body.write("");
        body.write(-1); //brick count
        body.write(2000);
        body.write((byte) 0);
        body.write((byte) 0);
        body.write((byte) 0);
        body.write((byte) 0);
        body.write((byte) 0);
        body.write((byte) 0);

        server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));

        server.logger().debug("SendEmptyUserMap to: " + client.GetIdentifier());
    }

    private static void destroyBrick(final GameServerLogic logic, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final int brick = msgRef.msg.msg().readInt();

        logic.logger().debug("HandleDestroyBrickRequest from: " + msgRef.client.GetIdentifier());

        if (!matchData.destroyedBricks.contains(brick)) {
            matchData.destroyedBricks.add(brick);
            SendDestroyBrick(logic, brick, matchData);
        }
    }

    public static void SendDestroyBrick(final GameServerLogic logic, final int brick, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        body.write(brick);

        logic.say(new MsgReference(MessageId.CS_DESTROY_BRICK_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendDestroyBrick for brick " + brick + " for room no: " + matchData.room.no);
    }
}
