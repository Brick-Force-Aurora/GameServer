package de.brickforceaurora.gameserver.maps;

import de.brickforceaurora.gameserver.math.Vector2;
import de.brickforceaurora.gameserver.math.Vector3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMap {
    public static final int VERSION = 1;

    public boolean isLoaded;

    public int map = -1;
    public int skybox = -1;
    public int crc;

    public Map<Integer, BrickInst> dic;

    private BrickInst[][][] geometry;

    private Map<Byte, Integer> limitedBricks;

    private List<Vector2> randomSpawners;

    private List<SpawnerDesc> redTeamSpawners;
    private List<SpawnerDesc> blueTeamSpawners;
    private List<SpawnerDesc> singleSpawners;

    private List<SpawnerDesc> redFlagSpawners;
    private List<SpawnerDesc> blueFlagSpawners;
    private List<SpawnerDesc> flagSpawners;

    private List<SpawnerDesc> bombSpawners;
    private List<SpawnerDesc> defenseSpawners;

    private List<BrickInst> scriptables;

    private List<SpawnerDesc> portalReds;
    private List<SpawnerDesc> portalBlues;
    private List<SpawnerDesc> portalNeutrals;

    private List<SpawnerDesc> railSpawners;

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

    public void clear()
    {
        isLoaded = false;
        crc = 0;
        map = -1;
        skybox = -1;
        dic.clear();
        for (byte b = 0; b < X_MAX; b = (byte)(b + 1))
        {
            for (byte b2 = 0; b2 < Y_MAX; b2 = (byte)(b2 + 1))
            {
                for (byte b3 = 0; b3 < Z_MAX; b3 = (byte)(b3 + 1))
                {
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

    public boolean addBrickInst(int seq, byte template, byte x, byte y, byte z, byte rot, List<Integer> morphes) {

        Brick brick = BrickManager.instance().getBrick(template);
        if (brick == null) {
            return false;
        }

        BrickInst brickInst = addBrickInst(seq, template, x, y, z, (short) 0, rot);
        if (brickInst == null) {
            return false;
        }

        brickInst.code = 0;

        if (brick.meshOptimize) {
            brickInst.code = calcMeshAndShadowCode(seq, x, y, z);

            for (BrickDirection dir : BrickDirection.values()) {
                BrickInst byCoord = getByCoord(x, y, z, dir);
                if (byCoord != null &&
                        BrickManager.instance().getBrick(byCoord.template).meshOptimize) {

                    int opposite = Brick.OPPOSITE[dir.ordinal()].ordinal();
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


    public BrickInst addBrickInst(int seq, byte template, byte x, byte y, byte z, int meshCode, byte rot) {

        if (dic.containsKey(seq)) {
            System.out.println("Duplicate brick seq " + seq);
            return null;
        }

        if (!isValidCoord(x, y, z)) {
            System.out.println("Invalid coord");
            return null;
        }

        Brick brick = BrickManager.instance().getBrick(template);
        if (brick == null) return null;

        BrickInst inst = new BrickInst(seq, template, x, y, z, meshCode, rot);
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
        List<BrickInst> list = new ArrayList<>();

        for (int i = 0; i < blueTeamSpawners.size(); i++) {
            int seq = blueTeamSpawners.get(i).sequence();
            BrickInst inst = dic.get(seq);
            if (inst != null) {
                list.add(inst);
            }
        }

        for (int i = 0; i < redTeamSpawners.size(); i++) {
            int seq = redTeamSpawners.get(i).sequence();
            BrickInst inst = dic.get(seq);
            if (inst != null) {
                list.add(inst);
            }
        }

        return list.toArray(new BrickInst[0]);
    }

    private boolean checkRandomSpawnable(byte x, byte z) {
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

    private void removeRandomSpawner(byte x, byte z) {
        Vector2 rhs = new Vector2((float) (x & 0xFF), (float) (z & 0xFF));

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
        for (Map.Entry<Integer, BrickInst> entry : dic.entrySet()) {
            BrickInst inst = entry.getValue();
            Brick brick = BrickManager.instance().getBrick(inst.template);

            if (brick == null) {
                inst.code = 0;
            } else {
                inst.code = calcMeshAndShadowCode(
                        inst.seq,
                        inst.posX,
                        inst.posY,
                        inst.posZ
                );
            }
        }
    }

    private int calcMeshAndShadowCode(int seq, byte x, byte y, byte z) {
        int code = 0;

        for (BrickDirection dir : BrickDirection.values()) {
            if (needMesh(seq, x, y, z, dir)) {
                code |= Brick.MESH_CODE_SET[dir.ordinal()];

                if (isShade(x, y, z, dir)) {
                    code |= Brick.SHADOW_CODE_SET[dir.ordinal()];
                }
            }
        }

        return code & 0xFFFF;
    }

    private boolean isShade(byte x, byte y, byte z, BrickDirection meshDir) {
        switch (meshDir) {
            case BOTTOM:
                return false;
            case TOP:
                return isShadeAbove(x, y, z);
            default:
                byte[] pos = moveTo(meshDir, x, y, z);
                return isShadeAbove(pos[0], pos[1], pos[2]);
        }
    }

    private boolean isShadeAbove(byte x, byte y, byte z) {
        while (true) {
            byte[] pos = moveTo(BrickDirection.TOP, x, y, z);
            x = pos[0];
            y = pos[1];
            z = pos[2];

            if (!isValidCoord(x, y, z)) {
                return false;
            }

            BrickInst inst = getByCoord(x, y, z);
            if (inst != null && BrickManager.instance().getBrick(inst.template).meshOptimize) {
                return true;
            }
        }
    }

    private boolean needMesh(int seq, byte x, byte y, byte z, BrickDirection meshDir) {
        if (!isValidCoord(x, y, z)) {
            return false;
        }

        if (!dic.containsKey(seq)) {
            return false;
        }

        BrickInst neighbor = getByCoord(x, y, z, meshDir);
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

    private void shadeBelow(boolean shade, byte x, byte y, byte z, List<Integer> morphes) {
        while (true) {

            // Move one block down
            byte[] pos = moveTo(BrickDirection.BOTTOM, x, y, z);
            x = pos[0];
            y = pos[1];
            z = pos[2];

            if (!isValidCoord(x, y, z)) {
                return;
            }

            BrickInst byCoord = getByCoord(x, y, z);

            if (byCoord != null &&
                    BrickManager.instance().getBrick(byCoord.template).meshOptimize) {

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
            for (BrickDirection dir = BrickDirection.FRONT; dir.ordinal() <= BrickDirection.RIGHT.ordinal(); dir = BrickDirection.values()[dir.ordinal() + 1]) {

                BrickInst side = getByCoord(x, y, z, dir);
                if (side != null &&
                        BrickManager.instance().getBrick(side.template).meshOptimize) {

                    int opposite = Brick.OPPOSITE[dir.ordinal()].ordinal();

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


    private void removeSpawner(SpawnerType spawnerType, byte x, byte y, byte z) {
        Vector3 rhs = new Vector3(
                (float) (x & 0xFF),
                (float) (y & 0xFF),
                (float) (z & 0xFF)
        );

        List<SpawnerDesc> list = switch (spawnerType) {
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

        if (list == null) return;

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

    private void increaseLimitedBrick(byte template) {
        limitedBricks.put(
                template,
                limitedBricks.getOrDefault(template, 0) + 1
        );
    }

    private void decreaseLimitedBrick(byte template) {
        Integer count = limitedBricks.get(template);
        if (count != null) {
            limitedBricks.put(template, count - 1);
        }
    }

    public int countLimitedBrick(byte template) {
        return limitedBricks.getOrDefault(template, 0);
    }

    public void moveTo(BrickDirection dir, ByteHolder x, ByteHolder y, ByteHolder z) {
        switch (dir) {
            case TOP -> y.value++;
            case BOTTOM -> y.value--;
            case FRONT -> z.value++;
            case BACK -> z.value--;
            case LEFT -> x.value++;
            case RIGHT -> x.value--;
        }
    }

    public SpawnerDesc getSpawner(SpawnerType type, int ticket) {
        List<SpawnerDesc> list = switch (type) {
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

        if (list == null || list.isEmpty()) return null;
        return list.get(ticket % list.size());
    }



    // HELPERS

    public BrickInst get(int seq)
    {
        if (!dic.containsKey(seq))
        {
            return null;
        }
        return dic.get(seq);
    }

    public void calcCRC(int seq, byte template)
    {
        crc ^= seq + template;
    }

    public boolean isValidCoord(byte x, byte y, byte z) {
        return (x & 0xFF) < X_MAX && (y & 0xFF) < Y_MAX && (z & 0xFF) < Z_MAX;
    }

    public BrickInst getByCoord(byte x, byte y, byte z) {
        if (!isValidCoord(x, y, z)) return null;
        return geometry[x & 0xFF][y & 0xFF][z & 0xFF];
    }

    public BrickInst getByCoord(byte x, byte y, byte z, BrickDirection meshDir) {
        byte[] pos = moveTo(meshDir, x, y, z);
        return getByCoord(pos[0], pos[1], pos[2]);
    }

    public int getSeqByCoord(byte x, byte y, byte z) {
        BrickInst bi = getByCoord(x, y, z);
        return bi != null ? bi.seq : -1;
    }

    public static final class ByteHolder {
        public byte value;
        public ByteHolder(byte v) { value = v; }
    }

    private byte[] moveTo(BrickDirection dir, byte x, byte y, byte z) {
        switch (dir) {
            case TOP -> y++;
            case BOTTOM -> y--;
            case FRONT -> z++;
            case BACK -> z--;
            case LEFT -> x++;
            case RIGHT -> x--;
        }
        return new byte[]{x, y, z};
    }


}
