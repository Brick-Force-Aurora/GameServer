package de.brickforceaurora.gameserver.room;

public class Room {
    public static String[] sceneByMode = {"MapEditor", "TeamMatch", "IndividualMatch", "CaptureTheFlagMatch", "ExplosionMatch", "Defense", "BndMatch", "Bungee", "Escape", "Zombie"};

    public static String[] typeStringKey = {"ROOM_TYPE_MAP_EDITOR", "ROOM_TYPE_TEAM_MATCH", "ROOM_TYPE_INDIVIDUAL_MATCH", "ROOM_TYPE_CAPTURE_THE_FLAG", "ROOM_TYPE_EXPLOSION", "ROOM_TYPE_MISSION", "ROOM_TYPE_BND", "ROOM_TYPE_BUNGEE", "ROOM_TYPE_ESCAPE", "ROOM_TYPE_ZOMBIE"};

    public static int[] modeMasks = {0, 1, 2, 4, 8, 16, 32, 64, 128, 256};

    public static int[] modeSelector = {32767, 1, 2, 4, 8, 16, 32, 64, 128, 256};

    public static byte official = 1;

    public static byte clanMatch = 2;

    public static byte excludeRanking = 4;

    public static byte blocked = 8;

    private static String[] statusStringKey = {"ROOM_STATUS_WAITING", "ROOM_STATUS_PENDING", "ROOM_STATUS_PLAYING"};

    public boolean locked;

    public int no;

    public int squad;

    public int squadCounter;

    public String title;

    public RoomType type;

    public RoomStatus status;

    public int curPlayer;

    public int maxPlayer;

    public String curMapAlias;

    public int map;

    public int goal;

    public int timelimit;

    public int weaponOption;

    public int ping;

    public int score1;

    public int score2;

    public int CountryFilter;

    public boolean isBreakInto;

    public boolean isDropItem;

    public boolean isWanted;

    public Room(Room src)
    {
        locked = src.locked;
        no = src.no;
        title = src.title;
        type = src.type;
        status = src.status;
        curPlayer = src.curPlayer;
        maxPlayer = src.maxPlayer;
        map = src.map;
        curMapAlias = src.curMapAlias;
        goal = src.goal;
        timelimit = src.timelimit;
        weaponOption = src.weaponOption;
        ping = src.ping;
        score1 = src.score1;
        score2 = src.score2;
        CountryFilter = src.CountryFilter;
        isBreakInto = src.isBreakInto;
        isDropItem = src.isDropItem;
        isWanted = src.isWanted;
        squad = src.squad;
        squadCounter = src.squadCounter;
    }

    public Room(boolean isLocked, int roomNo, String roomTitle, RoomType roomType, RoomStatus roomStatus, int cur, int max, int mapId, String alias, int roomGoal, int roomTimelimit, int RoomWeaponOption, int RoomPing, int RoomScore1, int RoomScore2, int countryFilter, boolean breakInto, boolean wanted, boolean dropItem, int _squad, int _squadCounter)
    {
        locked = isLocked;
        no = roomNo;
        title = roomTitle;
        type = roomType;
        status = roomStatus;
        curPlayer = cur;
        maxPlayer = max;
        map = mapId;
        curMapAlias = alias;
        goal = roomGoal;
        timelimit = roomTimelimit;
        weaponOption = RoomWeaponOption;
        ping = RoomPing;
        score1 = RoomScore1;
        score2 = RoomScore2;
        CountryFilter = countryFilter;
        isBreakInto = breakInto;
        isWanted = wanted;
        isDropItem = dropItem;
        squad = _squad;
        squadCounter = _squadCounter;
    }

    public int Compare(Room arg, Column sortedBy, boolean ascending)
    {
        int num = 0;
        switch (sortedBy) {
            case NO -> num = Integer.compare(this.no, arg.no);
            case TYPE -> num = Integer.compare(this.type.getId(), arg.type.getId());
            case TITLE -> num = this.title.compareTo(arg.title);
            case STATUS -> num = Integer.compare(this.status.getId(), arg.status.getId());
            case NUM_PLAYER -> num = Integer.compare(this.curPlayer, arg.curPlayer);
            case MAP_ALIAS -> num = this.curMapAlias.compareTo(arg.curMapAlias);
            case PING -> num = Integer.compare(this.ping, arg.ping);
            case COUNTRY -> num = Integer.compare(CountryFilter, arg.CountryFilter);
            case WPN_OPT -> num = Integer.compare(weaponOption, arg.weaponOption);
        }
        if (!ascending)
        {
            num = -num;
        }
        return num;
    }
}
