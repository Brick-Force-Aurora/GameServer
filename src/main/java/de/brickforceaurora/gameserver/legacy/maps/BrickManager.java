package de.brickforceaurora.gameserver.legacy.maps;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.legacy.math.Vector3;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;
import me.lauriichan.laylib.json.io.JsonSyntaxException;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.resource.source.IDataSource;

public final class BrickManager {

    /* ===================== ENUMS ===================== */

    public enum DRUM_BOOM {
        EXP(115),
        TOXIC(116),
        TNT(193);

        public final int seq;

        DRUM_BOOM(final int seq) {
            this.seq = seq;
        }
    }

    public enum GRAVITY_BRICK_SEQ {
        MINUS(155),
        PLUS(156),
        MINUS_DESTROY(157),
        PLUS_DESTROY(158);

        public final int seq;

        GRAVITY_BRICK_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum BOOSTER_SEQ {
        Y_UP(159),
        Z_MINUS(160);

        public final int seq;

        BOOSTER_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum DAMAGE_SEQ {
        FIRE(133),
        DOOR(165),
        TRAP(166),
        CACTUS(190),
        DOOR_T(191);

        public final int seq;

        DAMAGE_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum PORTAL_SEQ {
        RED(163),
        BLUE(164),
        NEUTRAL(178);

        public final int seq;

        PORTAL_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum ETC_SPAWNER_SEQ {
        RAIL(196);

        public final int seq;

        ETC_SPAWNER_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum SPECIAL_SEQ {
        RAIL(197),
        RAILLINK_R(198),
        RAILSLOPE_UP(199),
        HIGHGRASS(200),
        BEARTRAP(201),
        RAILLINK_L(202),
        RAILSLOPE_DN(203),
        DEFENSE_END(136);

        public final int seq;

        SPECIAL_SEQ(final int seq) {
            this.seq = seq;
        }
    }

    public enum SYSTEM_MAP {
        TEAM_MATCH_AWARDS,
        LOBBY,
        WAITING,
        BATTLE_TUTOR,
        INDIVIDUAL_MATCH_AWARDS,
        DEFENSE_MODE_AWARDS
    }

    /* ===================== FIELDS ===================== */

    public Brick[] bricks;
    private final Map<Byte, Brick> dicTBrick;

    public float bungee = -50f;

    @SuppressWarnings("unchecked")
    private final List<BrickInst>[][][][] chunks;

    public UserMap userMap;
    public UserMap result4TeamMatch;
    public UserMap lobby;
    public UserMap tutorMap;

    private boolean isLoaded;
    private boolean isIconLoaded;
    private boolean isMaterialLoaded;
    private boolean isBrightLoaded;
    private boolean isDarkLoaded;

    private int specialCount;
    private int gravityValue;
    private int numPlusBrick;
    private int numMinusBrick;

    private final SnowFrame<?> frame;

    private final Map<Integer, Vector3> dicDoorT = new HashMap<>();

    /* ===================== SINGLETON ===================== */

    private static final BrickManager INSTANCE = new BrickManager();

    public static BrickManager instance() {
        return INSTANCE;
    }

    /* ===================== CONSTRUCTOR ===================== */

    private BrickManager() {
        this.frame = GameServerApp.get().snowFrame();

        // Build mesh/shadow reset masks
        for (int i = 0; i < 6; i++) {
            Brick.MESH_CODE_RESET[i] = (short) ~Brick.MESH_CODE_SET[i];
            Brick.SHADOW_CODE_RESET[i] = (short) ~Brick.SHADOW_CODE_SET[i];
        }

        dicTBrick = new HashMap<>();

        bricks = loadBricks();
        for (final Brick brick : bricks) {
            final byte idx = brick.getIndex();
            if (dicTBrick.containsKey(idx)) {
                GameServerApp.logger().error("Duplicate brick index found: " + idx);
            }
            dicTBrick.put(idx, brick);
        }

        final int cx = (UserMap.X_MAX + 9) / 10;
        final int cy = (UserMap.Y_MAX + 9) / 10;
        final int cz = (UserMap.Z_MAX + 9) / 10;

        chunks = new ArrayList[cx][cy][cz][bricks.length];
        for (int x = 0; x < cx; x++) {
            for (int y = 0; y < cy; y++) {
                for (int z = 0; z < cz; z++) {
                    for (int i = 0; i < bricks.length; i++) {
                        chunks[x][y][z][i] = new ArrayList<>();
                    }
                }
            }
        }
    }

    /* ===================== LOAD BRICKS ===================== */

    private IJson<?> readJson(final String path) throws IllegalStateException, IOException, JsonSyntaxException {
        final IDataSource source = frame.resource(path);
        if (!source.exists()) {
            throw new IllegalStateException("File doesn't exist: " + source.getPath());
        }
        try (InputStream stream = source.openReadableStream()) {
            return JsonParser.fromStream(stream);
        }
    }

    private Brick[] loadBricks() {
        try {
            final IJson<?> root = readJson("jar://bricks.json");

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            return parseBricks(root.asJsonArray());

        } catch (final Exception e) {
            GameServerApp.logger().error("Error when loading bricks.json " + e.getMessage());
        }
        return new Brick[0];
    }

    private Brick[] parseBricks(final JsonArray array) {
        final Brick[] bricks = new Brick[array.size()];

        for (int index = 0; index < array.size(); index++) {
            final JsonObject o = array.get(index).asJsonObject();

            final Brick brick = new Brick(o.getAsString("brickName"), o.getAsInt("seq"), o.getAsString("brickAlias"),
                o.getAsString("brickComment"), o.getAsBoolean("destructible"), o.getAsBoolean("directionable"), o.getAsInt("function"),
                o.getAsBoolean("meshOptimize"), o.getAsBoolean("chunkOptimize"), o.getAsBoolean("chunkOptimizeOnMatch"),
                o.getAsBoolean("onlyTutor"), o.getAsInt("season"), o.getAsInt("horz"), o.getAsInt("vert"),
                o.getAsInt("maxChildrenPerChunk"), o.getAsInt("maxChildrenPerMap"), o.getAsInt("category"), o.getAsInt("hitPoint"),
                o.getAsBoolean("disable"), o.getAsBoolean("replace"), o.getAsBoolean("bnd"), o.getAsInt("gameModeDependant"));

            bricks[index] = brick;
        }
        return bricks;
    }

    /* ===================== GETTERS ===================== */

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isIconLoaded() {
        return isIconLoaded;
    }

    public boolean isMaterialLoaded() {
        return isMaterialLoaded;
    }

    public boolean isBrightLoaded() {
        return isBrightLoaded;
    }

    public boolean isDarkLoaded() {
        return isDarkLoaded;
    }

    public int getSpecialCount() {
        return specialCount;
    }

    public int getGravityValue() {
        return gravityValue;
    }

    public void setGravityValue(final int v) {
        gravityValue = v;
    }

    public int getNumPlusBrick() {
        return numPlusBrick;
    }

    public void setNumPlusBrick(final int v) {
        numPlusBrick = v;
    }

    public int getNumMinusBrick() {
        return numMinusBrick;
    }

    public void setNumMinusBrick(final int v) {
        numMinusBrick = v;
    }

    /* ===================== MAP / BRICK ACCESS ===================== */

    public BrickInst[] toBrickInstArray() {
        return userMap == null ? null : userMap.toArray();
    }

    public int countLimitedBrick(final byte template) {
        return userMap == null ? 0 : userMap.countLimitedBrick(template);
    }

    public BrickInst getBrickInst(final int seq) {
        return userMap == null ? null : userMap.get(seq);
    }

    public Brick getBrick(final int index) {
        final Brick brick = dicTBrick.get((byte) index);
        if (brick == null) {
            GameServerApp.logger().error("Invalid brick index requested: " + index);
        }
        return brick;
    }

    /* ===================== SPAWNERS ===================== */

    public SpawnerDesc getSpawner(final SpawnerType type, final int ticket) {
        return userMap == null ? null : userMap.getSpawner(type, ticket);
    }

    public SpawnerDesc getAwardSpawner4TeamMatch(final SpawnerType type, final int rank) {
        return result4TeamMatch.getSpawner(type, rank);
    }

    /* ===================== MAP BUILD ===================== */

    public void cacheBrick(final int seq, final byte template, final byte x, final byte y, final byte z, final short meshCode,
        final byte rot) {
        userMap.calcCRC(seq, template);
        if (userMap.addBrickInst(seq, template, x, y, z, meshCode, rot) == null) {
            GameServerApp.logger().error("CacheBrick failed");
        }
    }

    public void addDoorTDic(final int seq, final Vector3 pos) {
        dicDoorT.putIfAbsent(seq, pos);
    }

    public void updateScript(final int seq, final String alias, final boolean enableOnAwake, final boolean visibleOnAwake,
        final String commands) {

        final BrickInst inst = getBrickInst(seq);
        if (inst == null) {
            return;
        }

        final Brick brick = getBrick(inst.template);
        if (brick != null && brick.function == BrickFunction.SCRIPT) {
            inst.updateScript(alias, enableOnAwake, visibleOnAwake, commands);
        } else {
            GameServerApp.logger().error("Brick is not scriptable");
        }
    }
}
