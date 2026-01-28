package de.brickforceaurora.gameserver.legacy.handler;

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

import java.util.List;

public class MapHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_DESTROY_BRICK_REQ.id(), MapHandlers::destroyBrick);
        d.register(MessageId.CS_REG_MAP_INFO_REQ.id(), MapHandlers::HandleRegMapInfoRequest);
        d.register(MessageId.CS_MY_REGISTER_MAP_REQ.id(), MapHandlers::HandleRequestRegisteredMaps);
        d.register(MessageId.CS_USER_MAP_REQ.id(), MapHandlers::HandleRequestUserMaps);
        d.register(MessageId.CS_MY_DOWNLOAD_MAP_REQ.id(), MapHandlers::HandleRequestDownloadedMaps);
        d.register(MessageId.CS_ALL_MAP_REQ.id(),  MapHandlers::HandleRequestAllMaps);
    }

    private static void HandleRequestDownloadedMaps(GameServerLogic server, MsgReference msgRef)
    {
        int prevPage = msgRef.msg.msg().readInt();
        int nextPage = msgRef.msg.msg().readInt();
        int indexer = msgRef.msg.msg().readInt();
        int modeMask = msgRef.msg.msg().readUShort();

        server.logger().debug("HandleRequestDownloadedMaps from: " + msgRef.client.GetIdentifier());

        SendDownloadedMaps(server, msgRef.client, nextPage);
    }

    private static void HandleRequestRegisteredMaps(GameServerLogic server, MsgReference msgRef)
    {
        int prevPage = msgRef.msg.msg().readInt();
        int nextPage = msgRef.msg.msg().readInt();
        int indexer = msgRef.msg.msg().readInt();
        int modeMask = msgRef.msg.msg().readUShort();

        server.logger().debug("HandleRequestRegisteredMaps from: " + msgRef.client.GetIdentifier());

        SendRegisteredMaps(server, msgRef.client, nextPage);
    }

    public static void SendDownloadedMaps(GameServerLogic server, ClientReference client, int page)
    {
        MsgBody body = new MsgBody();

        int mapsPerPage = 12;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int offset = page * mapsPerPage;
        int remaining = maps.size() - offset;
        int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++)
        {
            RegMap entry = maps.get(i);
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte)(Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte)entry.getRegisteredDate().getMonth().getValue());
            body.write((byte)entry.getRegisteredDate().getDayOfMonth());
            body.write((byte)entry.getRegisteredDate().getHour());
            body.write((byte)entry.getRegisteredDate().getMinute());
            body.write((byte)entry.getRegisteredDate().getSecond());
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

    public static void SendRegisteredMaps(GameServerLogic server, ClientReference client, int page)
    {
        MsgBody body = new MsgBody();

        int mapsPerPage = 12;
        int offset = page * mapsPerPage;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int remaining = maps.size() - offset;
        int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++)
        {
            RegMap entry = maps.get(i);
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte)(Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte)entry.getRegisteredDate().getMonth().getValue());
            body.write((byte)entry.getRegisteredDate().getDayOfMonth());
            body.write((byte)entry.getRegisteredDate().getHour());
            body.write((byte)entry.getRegisteredDate().getMinute());
            body.write((byte)entry.getRegisteredDate().getSecond());
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

    private static void HandleRequestUserMaps(GameServerLogic server, MsgReference msgRef)
    {
        int page = msgRef.msg.msg().readInt();

        server.logger().debug("HandleRequestUserMaps from: " + msgRef.client.GetIdentifier());

        SendUserMaps(server, msgRef.client, page);
    }

    public static void SendUserMaps(GameServerLogic server, ClientReference client, int page)
    {
        MsgBody body = new MsgBody();

        int mapsPerPage = 12;
        int offset = page * mapsPerPage;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int remaining = maps.size() - offset;
        int count = Math.min(remaining, mapsPerPage);

        body.write(page); //page
        body.write(count); //count
        for (int i = offset; i < offset + count; i++)
        {
            RegMap entry = maps.get(i);
            body.write(i); //slot
            body.write(entry.getAlias());
            body.write(10000); //brick count
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte)entry.getRegisteredDate().getMonth().getValue());
            body.write((byte)entry.getRegisteredDate().getDayOfMonth());
            body.write((byte)entry.getRegisteredDate().getHour());
            body.write((byte)entry.getRegisteredDate().getMinute());
            body.write((byte)entry.getRegisteredDate().getSecond());
            body.write((byte)0);
        }
        server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));

        server.logger().debug("SendUserMaps to: " + client.GetIdentifier());
    }

    private static void HandleRegMapInfoRequest(GameServerLogic server, MsgReference msgRef)
    {
        int mapId = msgRef.msg.msg().readInt();

        server.logger().debug("HandleRegMapInfoRequest from: " + msgRef.client.GetIdentifier());
    }

    private static void HandleRequestAllMaps(GameServerLogic server, MsgReference msgRef)
    {
        int prevPage = msgRef.msg.msg().readInt();
        int nextPage = msgRef.msg.msg().readInt();
        int indexer = msgRef.msg.msg().readInt();
        int modeMask = msgRef.msg.msg().readUShort();
        int flag = msgRef.msg.msg().readInt();
        String filter = msgRef.msg.msg().readString();

        var maps = RegMapManager.getInstance().getAllMapsPage(
                prevPage,
                nextPage,
                indexer,
                modeMask,
                flag,
                filter
        );

        SendMapList(server, msgRef.client, nextPage, maps, MessageId.CS_ALL_MAP_ACK);
    }

    private static void SendMapList(GameServerLogic server, ClientReference client, int page, List<RegMap> maps, MessageId msgId)
    {
        MsgBody body = new MsgBody();

        body.write(page);
        body.write(maps.size());

        for (RegMap entry : maps)
        {
            body.write(entry.getMap());
            body.write(entry.getDeveloper());
            body.write(entry.getAlias());
            body.writeUShort(entry.getModeMask());
            body.write((byte)(Room.clanMatch | Room.official));
            body.write(entry.tagMask);
            body.write(entry.getRegisteredDate().getYear());
            body.write((byte)entry.getRegisteredDate().getMonthValue());
            body.write((byte)entry.getRegisteredDate().getDayOfMonth());
            body.write((byte)entry.getRegisteredDate().getHour());
            body.write((byte)entry.getRegisteredDate().getMinute());
            body.write((byte)entry.getRegisteredDate().getSecond());

            body.write(entry.getDownloadFee());
            body.write(entry.getRelease());
            body.write(entry.getLatestRelease());
            body.write(entry.getLikes());
            body.write(entry.getDisLikes());
            body.write(entry.getDownloadCount());
        }

        server.say(new MsgReference(msgId, body, client));
    }


    public static void sendAllUserMaps(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        int chunkSize = 200;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++)
        {
            int remaining = maps.size() - processedCount;
            if (remaining < chunkSize)
                chunkSize = remaining;

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++)
            {
                RegMap entry = maps.get(i);
                body.write(i); //slot
                body.write(entry.getAlias());
                body.write(-1); //brick count
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte)entry.getRegisteredDate().getMonth().getValue());
                body.write((byte)entry.getRegisteredDate().getDayOfMonth());
                body.write((byte)entry.getRegisteredDate().getHour());
                body.write((byte)entry.getRegisteredDate().getMinute());
                body.write((byte)entry.getRegisteredDate().getSecond());
                body.write((byte)0); //premium
            }
            server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));
        }

        server.logger().debug("SendAllUserMaps to: " + client.GetIdentifier());
    }

    public static void sendAllDownloadedMaps(GameServerLogic server, ClientReference client)
    {
        int chunkSize = 100;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++)
        {
            int remaining = maps.size() - processedCount;
            if (remaining < chunkSize)
                chunkSize = remaining;

            MsgBody body = new MsgBody();

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++)
            {
                RegMap entry = maps.get(i);
                body.write(entry.getMap());
                body.write(entry.getDeveloper());
                body.write(entry.getAlias());
                body.writeUShort(entry.getModeMask());
                body.write((byte)(Room.clanMatch | Room.official));
                body.write(entry.tagMask);
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte)entry.getRegisteredDate().getMonth().getValue());
                body.write((byte)entry.getRegisteredDate().getDayOfMonth());
                body.write((byte)entry.getRegisteredDate().getHour());
                body.write((byte)entry.getRegisteredDate().getMinute());
                body.write((byte)entry.getRegisteredDate().getSecond());
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

    public static void sendEmptyUserMap(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(-1); //page
        body.write(1); //count
        body.write(0); //slot
        body.write("");
        body.write(-1); //brick count
        body.write(2000);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);

        server.say(new MsgReference(MessageId.CS_USER_MAP_ACK, body, client));

        server.logger().debug("SendEmptyUserMap to: " + client.GetIdentifier());
    }

    private static void destroyBrick(GameServerLogic logic, MsgReference msgRef)
    {
        MatchData matchData = msgRef.matchData;

        int brick = msgRef.msg.msg().readInt();

        logic.logger().debug("HandleDestroyBrickRequest from: " + msgRef.client.GetIdentifier());

        if (!matchData.destroyedBricks.contains(brick)) {
            matchData.destroyedBricks.add(brick);
            SendDestroyBrick(logic, brick, matchData);
        }
    }

    public static void SendDestroyBrick(GameServerLogic logic, int brick, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(brick);

        logic.say(new MsgReference(MessageId.CS_DESTROY_BRICK_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendDestroyBrick for brick " + brick + " for room no: " + matchData.room.no);
    }
}
