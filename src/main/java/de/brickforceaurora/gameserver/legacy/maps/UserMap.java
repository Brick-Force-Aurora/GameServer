package de.brickforceaurora.gameserver.legacy.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.legacy.math.Vector2;
import de.brickforceaurora.gameserver.legacy.math.Vector3;

public class UserMap {
    public static final int VERSION = 1;

    public boolean isLoaded;

    public int map = -1;
    public int skybox = -1;
    public int crc;

    public Map<Integer, BrickInst> dic;

    private final BrickInst[][][] geometry;

    private final Map<Byte, Integer> limitedBricks;

    private final List<Vector2> randomSpawners;

    private final List<SpawnerDesc> redTeamSpawners;
    private final List<SpawnerDesc> blueTeamSpawners;
    private final List<SpawnerDesc> singleSpawners;

    private final List<SpawnerDesc> redFlagSpawners;
    private final List<SpawnerDesc> blueFlagSpawners;
    private final List<SpawnerDesc> flagSpawners;

    private final List<SpawnerDesc> bombSpawners;
    private final List<SpawnerDesc> defenseSpawners;

    private final List<BrickInst> scriptables;

    private final List<SpawnerDesc> portalReds;
    private final List<SpawnerDesc> portalBlues;
    private final List<SpawnerDesc> portalNeutrals;

    private final List<SpawnerDesc> railSpawners;

    public static final byte X_MAX = 100;
    public static final byte Y_MAX = 100;
    public static final byte Z_MAX = 100;

    public float cenX;
    public float cenZ;

    public Vector3 min = Vector3.ZERO;
    public Vector3 max = Vector3.ZERO;

    public boolean isPortalMove;

    public UserMap() {
        dic = new HashMap<>();
        geometry = new BrickInst[X_MAX][Y_MAX][Z_MAX];

        randomSpawners = new ArrayList<>();

        blueTeamSpawners = new ArrayList<>();
        redTeamSpawners = new ArrayList<>();
        singleSpawners = new ArrayList<>();

        blueFlagSpawners = new ArrayList<>();
        redFlagSpawners = new ArrayList<>();
        flagSpawners = new ArrayList<>();

        bombSpawners = new ArrayList<>();
        defenseSpawners = new ArrayList<>();

        scriptables = new ArrayList<>();

        portalReds = new ArrayList<>();
        portalBlues = new ArrayList<>();
        portalNeutrals = new ArrayList<>();

        railSpawners = new ArrayList<>();

        limitedBricks = new HashMap<>();

        clear();
    }

    public void clear() {
        isLoaded = false;
        crc = 0;
        map = -1;
        skybox = -1;
        dic.clear();
        for (byte b = 0; b < X_MAX; b = (byte) (b + 1)) {
            for (byte b2 = 0; b2 < Y_MAX; b2 = (byte) (b2 + 1)) {
                for (byte b3 = 0; b3 < Z_MAX; b3 = (byte) (b3 + 1)) {
                    geometry[b][b2][b3] = null;
                }
            }
        }
        redTeamSpawners.clear();
        blueTeamSpawners.clear();
        singleSpawners.clear();
        limitedBricks.clear();
        blueFlagSpawners.clear();
        redFlagSpawners.clear();
        flagSpawners.clear();
        bombSpawners.clear();
        defenseSpawners.clear();
        scriptables.clear();
        railSpawners.clear();
        randomSpawners.clear();
    }

    public boolean addBrickInst(final int seq, final byte template, final byte x, final byte y, final byte z, final byte rot,
        final List<Integer> morphes) {

        final Brick brick = BrickManager.instance().getBrick(template);
        if (brick == null) {
            return false;
        }

        final BrickInst brickInst = addBrickInst(seq, template, x, y, z, (short) 0, rot);
        if (brickInst == null) {
            return false;
        }

        brickInst.code = 0;

        if (brick.meshOptimize) {
            brickInst.code = calcMeshAndShadowCode(seq, x, y, z);

            for (final BrickDirection dir : BrickDirection.values()) {
                final BrickInst byCoord = getByCoord(x, y, z, dir);
                if (byCoord != null && BrickManager.instance().getBrick(byCoord.template).meshOptimize) {

                    final int opposite = Brick.OPPOSITE[dir.ordinal()].ordinal();
                    byCoord.code &= Brick.MESH_CODE_RESET[opposite];
                    byCoord.code &= Brick.SHADOW_CODE_RESET[opposite];
                    morphes.add(byCoord.seq);
                }
            }

            if ((brickInst.code & Brick.SHADOW_CODE_SET[0]) <= 0) {
                shadeBelow(true, x, y, z, morphes);
            }
        }

        return true;
    }

    public BrickInst addBrickInst(final int seq, final byte template, final byte x, final byte y, final byte z, final int meshCode,
        final byte rot) {

        if (dic.containsKey(seq)) {
            GameServerApp.logger().error("Duplicate brick seq " + seq);
            return null;
        }

        if (!isValidCoord(x, y, z)) {
            GameServerApp.logger().error("Invalid coord");
            return null;
        }

        final Brick brick = BrickManager.instance().getBrick(template);
        if (brick == null) {
            return null;
        }

        final BrickInst inst = new BrickInst(seq, template, x, y, z, meshCode, rot);
        geometry[x & 0xFF][y & 0xFF][z & 0xFF] = inst;
        dic.put(seq, inst);

        if (brick.function == BrickFunction.SCRIPT) {
            scriptables.add(inst);
            inst.updateScript(String.valueOf(seq), false, false, "");
        }

        if (brick.maxInstancePerMap > 0) {
            limitedBricks.merge(template, 1, Integer::sum);
        }

        return inst;
    }

    public BrickInst[] toTeamSpawnersArray() {
        final List<BrickInst> list = new ArrayList<>();

        for (int i = 0; i < blueTeamSpawners.size(); i++) {
            final int seq = blueTeamSpawners.get(i).sequence();
            final BrickInst inst = dic.get(seq);
            if (inst != null) {
                list.add(inst);
            }
        }

        for (int i = 0; i < redTeamSpawners.size(); i++) {
            final int seq = redTeamSpawners.get(i).sequence();
            final BrickInst inst = dic.get(seq);
            if (inst != null) {
                list.add(inst);
            }
        }

        return list.toArray(new BrickInst[0]);
    }

    private boolean checkRandomSpawnable(final byte x, final byte z) {
        if ((x & 0xFF) >= X_MAX || (z & 0xFF) >= Z_MAX) {
            return false;
        }

        for (byte y = 0; (y & 0xFF) < Y_MAX; y++) {
            if (geometry[x & 0xFF][y & 0xFF][z & 0xFF] != null) {
                return true;
            }
        }

        return false;
    }

    private void removeRandomSpawner(final byte x, final byte z) {
        final Vector2 rhs = new Vector2(x & 0xFF, z & 0xFF);

        for (int i = 0; i < randomSpawners.size(); i++) {
            if (rhs.equals(randomSpawners.get(i))) {
                randomSpawners.remove(i);
                return;
            }
        }
    }

    public void postLoadInit() {
        isLoaded = true;
        initRandomSpawners();
        calcMeshCodes();
    }

    private void calcMeshCodes() {
        for (final Map.Entry<Integer, BrickInst> entry : dic.entrySet()) {
            final BrickInst inst = entry.getValue();
            final Brick brick = BrickManager.instance().getBrick(inst.template);

            if (brick == null) {
                inst.code = 0;
            } else {
                inst.code = calcMeshAndShadowCode(inst.seq, inst.posX, inst.posY, inst.posZ);
            }
        }
    }

    private int calcMeshAndShadowCode(final int seq, final byte x, final byte y, final byte z) {
        int code = 0;

        for (final BrickDirection dir : BrickDirection.values()) {
            if (needMesh(seq, x, y, z, dir)) {
                code |= Brick.MESH_CODE_SET[dir.ordinal()];

                if (isShade(x, y, z, dir)) {
                    code |= Brick.SHADOW_CODE_SET[dir.ordinal()];
                }
            }
        }

        return code & 0xFFFF;
    }

    private boolean isShade(final byte x, final byte y, final byte z, final BrickDirection meshDir) {
        switch (meshDir) {
        case BOTTOM:
            return false;
        case TOP:
            return isShadeAbove(x, y, z);
        default:
            final byte[] pos = moveTo(meshDir, x, y, z);
            return isShadeAbove(pos[0], pos[1], pos[2]);
        }
    }

    private boolean isShadeAbove(byte x, byte y, byte z) {
        while (true) {
            final byte[] pos = moveTo(BrickDirection.TOP, x, y, z);
            x = pos[0];
            y = pos[1];
            z = pos[2];

            if (!isValidCoord(x, y, z)) {
                return false;
            }

            final BrickInst inst = getByCoord(x, y, z);
            if (inst != null && BrickManager.instance().getBrick(inst.template).meshOptimize) {
                return true;
            }
        }
    }

    private boolean needMesh(final int seq, final byte x, final byte y, final byte z, final BrickDirection meshDir) {
        if (!isValidCoord(x, y, z) || !dic.containsKey(seq)) {
            return false;
        }

        final BrickInst neighbor = getByCoord(x, y, z, meshDir);
        if (neighbor == null) {
            return true;
        }

        return !BrickManager.instance().getBrick(neighbor.template).meshOptimize;
    }

    public BrickInst[] toArray() {
        return dic.values().toArray(new BrickInst[0]);
    }

    private void initRandomSpawners() {
        randomSpawners.clear();

        for (byte x = 0; x < X_MAX; x++) {
            for (byte z = 0; z < Z_MAX; z++) {
                for (byte y = 0; y < Y_MAX; y++) {
                    if (geometry[x][y][z] != null) {
                        randomSpawners.add(new Vector2(x, z));
                        break;
                    }
                }
            }
        }
    }

    private void shadeBelow(final boolean shade, byte x, byte y, byte z, final List<Integer> morphes) {
        while (true) {

            // Move one block down
            final byte[] pos = moveTo(BrickDirection.BOTTOM, x, y, z);
            x = pos[0];
            y = pos[1];
            z = pos[2];

            if (!isValidCoord(x, y, z)) {
                return;
            }

            final BrickInst byCoord = getByCoord(x, y, z);

            if (byCoord != null && BrickManager.instance().getBrick(byCoord.template).meshOptimize) {

                if (shade) {
                    if ((byCoord.code & Brick.MESH_CODE_SET[0]) != 0) {
                        byCoord.code |= Brick.SHADOW_CODE_SET[0];
                        morphes.add(byCoord.seq);
                    }
                } else {
                    byCoord.code &= Brick.SHADOW_CODE_RESET[0];
                    morphes.add(byCoord.seq);
                }
                return;
            }

            // Check neighbors if empty below
            for (BrickDirection dir = BrickDirection.FRONT; dir.ordinal() <= BrickDirection.RIGHT
                .ordinal(); dir = BrickDirection.values()[dir.ordinal() + 1]) {

                final BrickInst side = getByCoord(x, y, z, dir);
                if (side != null && BrickManager.instance().getBrick(side.template).meshOptimize) {

                    final int opposite = Brick.OPPOSITE[dir.ordinal()].ordinal();

                    if (shade) {
                        if ((side.code & Brick.MESH_CODE_SET[opposite]) != 0) {
                            side.code |= Brick.SHADOW_CODE_SET[opposite];
                            morphes.add(side.seq);
                        }
                    } else {
                        side.code &= Brick.SHADOW_CODE_RESET[opposite];
                        morphes.add(side.seq);
                    }
                }
            }
        }
    }

    private void removeSpawner(final SpawnerType spawnerType, final byte x, final byte y, final byte z) {
        final Vector3 rhs = new Vector3(x & 0xFF, y & 0xFF, z & 0xFF);

        final List<SpawnerDesc> list = switch (spawnerType) {
        case BLUE_TEAM_SPAWNER -> blueTeamSpawners;
        case RED_TEAM_SPAWNER -> redTeamSpawners;
        case SINGLE_SPAWNER -> singleSpawners;
        case RED_FLAG_SPAWNER -> redFlagSpawners;
        case BLUE_FLAG_SPAWNER -> blueFlagSpawners;
        case FLAG_SPAWNER -> flagSpawners;
        case BOMB_SPAWNER -> bombSpawners;
        case DEFENCE_SPAWNER -> defenseSpawners;
        default -> null;
        };

        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (rhs.equals(list.get(i).position())) {
                list.remove(i);
                return;
            }
        }
    }

    public BrickInst[] getAllScriptables() {
        return scriptables.toArray(new BrickInst[0]);
    }

    private void increaseLimitedBrick(final byte template) {
        limitedBricks.put(template, limitedBricks.getOrDefault(template, 0) + 1);
    }

    private void decreaseLimitedBrick(final byte template) {
        final Integer count = limitedBricks.get(template);
        if (count != null) {
            limitedBricks.put(template, count - 1);
        }
    }

    public int countLimitedBrick(final byte template) {
        return limitedBricks.getOrDefault(template, 0);
    }

    public void moveTo(final BrickDirection dir, final ByteHolder x, final ByteHolder y, final ByteHolder z) {
        switch (dir) {
        case TOP -> y.value++;
        case BOTTOM -> y.value--;
        case FRONT -> z.value++;
        case BACK -> z.value--;
        case LEFT -> x.value++;
        case RIGHT -> x.value--;
        }
    }

    public SpawnerDesc getSpawner(final SpawnerType type, final int ticket) {
        final List<SpawnerDesc> list = switch (type) {
        case BLUE_TEAM_SPAWNER -> blueTeamSpawners;
        case RED_TEAM_SPAWNER -> redTeamSpawners;
        case SINGLE_SPAWNER -> singleSpawners;
        case BLUE_FLAG_SPAWNER -> blueFlagSpawners;
        case RED_FLAG_SPAWNER -> redFlagSpawners;
        case FLAG_SPAWNER -> flagSpawners;
        case BOMB_SPAWNER -> bombSpawners;
        case DEFENCE_SPAWNER -> defenseSpawners;
        default -> null;
        };

        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(ticket % list.size());
    }

    // HELPERS

    public BrickInst get(final int seq) {
        if (!dic.containsKey(seq)) {
            return null;
        }
        return dic.get(seq);
    }

    public void calcCRC(final int seq, final byte template) {
        crc ^= seq + template;
    }

    public boolean isValidCoord(final byte x, final byte y, final byte z) {
        return (x & 0xFF) < X_MAX && (y & 0xFF) < Y_MAX && (z & 0xFF) < Z_MAX;
    }

    public BrickInst getByCoord(final byte x, final byte y, final byte z) {
        if (!isValidCoord(x, y, z)) {
            return null;
        }
        return geometry[x & 0xFF][y & 0xFF][z & 0xFF];
    }

    public BrickInst getByCoord(final byte x, final byte y, final byte z, final BrickDirection meshDir) {
        final byte[] pos = moveTo(meshDir, x, y, z);
        return getByCoord(pos[0], pos[1], pos[2]);
    }

    public int getSeqByCoord(final byte x, final byte y, final byte z) {
        final BrickInst bi = getByCoord(x, y, z);
        return bi != null ? bi.seq : -1;
    }

    public static final class ByteHolder {
        public byte value;

        public ByteHolder(final byte v) {
            value = v;
        }
    }

    private byte[] moveTo(final BrickDirection dir, byte x, byte y, byte z) {
        switch (dir) {
        case TOP -> y++;
        case BOTTOM -> y--;
        case FRONT -> z++;
        case BACK -> z--;
        case LEFT -> x++;
        case RIGHT -> x--;
        }
        return new byte[] {
            x,
            y,
            z
        };
    }

}
