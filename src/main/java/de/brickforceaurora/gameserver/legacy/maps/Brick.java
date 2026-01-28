package de.brickforceaurora.gameserver.legacy.maps;

import de.brickforceaurora.gameserver.legacy.room.RoomType;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class Brick {
    public String brickName;
    public int seq;
    public String brickAlias;
    public String brickComment;

    public boolean destructible;
    public boolean directionable;
    public BrickFunction function;

    public boolean meshOptimize = true;
    public boolean chunkOptimize = true;
    public boolean chunkOptimizeOnMatch = true;

    public boolean onlyTutor;

    public int horz = 1;
    public int vert = 1;

    public Texture2D[] bulletMarks;

    public int maxChildrenPerChunk = 255;
    public int maxInstancePerMap = -1;

    public BrickCategory category;

    public int hitPoint = 1000;

    public boolean disable;
    public boolean replace = true;
    public boolean bnd = true;

    public String[] ticket;

    public RoomType gameModeDependent = RoomType.NONE;

    public Brick(final String brickName, final int seq, final String brickAlias, final String brickComment, final boolean destructible,
        final boolean directionable, final int function, final boolean meshOptimize, final boolean chunkOptimize,
        final boolean chunkOptimizeOnMatch, final boolean onlyTutor, final int season, final int horz, final int vert,
        final int maxChildrenPerChunk, final int maxInstancePerMap, final int category, final int hitPoint, final boolean disable,
        final boolean replace, final boolean bnd, final int gameModeDependent) {
        this.brickName = brickName;
        this.seq = seq;
        this.brickAlias = brickAlias;
        this.brickComment = brickComment;
        this.destructible = destructible;
        this.directionable = directionable;
        this.function = BrickFunction.values()[function];
        this.meshOptimize = meshOptimize;
        this.chunkOptimize = chunkOptimize;
        this.chunkOptimizeOnMatch = chunkOptimizeOnMatch;
        this.onlyTutor = onlyTutor;
        this.horz = horz;
        this.vert = vert;
        this.maxChildrenPerChunk = maxChildrenPerChunk;
        this.maxInstancePerMap = maxInstancePerMap;
        this.category = BrickCategory.values()[category];
        this.hitPoint = hitPoint;
        this.disable = disable;
        this.replace = replace;
        this.bnd = bnd;
        this.gameModeDependent = RoomType.fromValue(gameModeDependent);
    }

    /* ===================== STATIC TABLES ===================== */

    public static final String[] NEED_FUNC = {
        "",
        "brickstar_builder"
    };

    public static final int[] SHADOW_CODE_SET = {
        256,
        512,
        1024,
        2048,
        4096,
        8192
    };

    public static final int[] SHADOW_CODE_RESET = new int[6];

    public static final int[] MESH_CODE_SET = {
        1,
        2,
        4,
        8,
        16,
        32
    };

    public static final int[] MESH_CODE_RESET = new int[6];

    public static final BrickDirection[] OPPOSITE = {
        BrickDirection.BOTTOM,
        BrickDirection.TOP,
        BrickDirection.BACK,
        BrickDirection.FRONT,
        BrickDirection.RIGHT,
        BrickDirection.LEFT
    };

    /* ===================== DERIVED PROPERTIES ===================== */

    /**
     * C# index property (byte cast of seq)
     */
    public byte getIndex() {
        return (byte) seq;
    }

    public boolean isClimbable() {
        return function == BrickFunction.LADDER;
    }

    public boolean isShootable() {
        return function == BrickFunction.CANNON;
    }

    public boolean isUnit() {
        return horz == 1 && vert == 1;
    }

    /* ===================== LOGIC ===================== */

    public boolean isEnable(final RoomType roomType) {
        if (disable) {
            return false;
        }
        if (roomType == RoomType.BND) {
            return bnd;
        }
        return true;
    }

    public BrickReplaceCheck checkReplace() {
        if (!isUnit() || directionable || !replace) {
            return BrickReplaceCheck.ERR;
        }
        return BrickReplaceCheck.OK;
    }

    public SpawnerType getSpawnerType() {
        if (brickName == null) {
            return SpawnerType.NONE;
        }

        if (brickName.contains("blue_team_spawner")) {
            return SpawnerType.BLUE_TEAM_SPAWNER;
        }
        if (brickName.contains("red_team_spawner")) {
            return SpawnerType.RED_TEAM_SPAWNER;
        }
        if (brickName.contains("single_spawner")) {
            return SpawnerType.SINGLE_SPAWNER;
        }
        if (brickName.contains("red_flag_spawner")) {
            return SpawnerType.RED_FLAG_SPAWNER;
        }
        if (brickName.contains("blue_flag_spawner")) {
            return SpawnerType.BLUE_FLAG_SPAWNER;
        }
        if (brickName.contains("flag_spawner")) {
            return SpawnerType.FLAG_SPAWNER;
        }
        if (brickName.contains("bomb_spawner")) {
            return SpawnerType.BOMB_SPAWNER;
        }
        if (brickName.contains("df_spawner_start")) {
            return SpawnerType.DEFENCE_SPAWNER;
        }

        return SpawnerType.NONE;
    }
}
