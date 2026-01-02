package de.brickforceaurora.gameserver.maps;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.util.CRC32;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


public final class MapGenerator {

    /* ===================== SINGLETON ===================== */

    public static final MapGenerator instance = new MapGenerator();

    private final Map<Integer, Landscape> landscapeTemplates;

    private MapGenerator() {
        landscapeTemplates = new HashMap<>();

        landscapeTemplates.put(0, new Landscape(new byte[]{0, 1}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 1));
        landscapeTemplates.put(1, new Landscape(new byte[]{0, 1}, new float[]{0.7f, 0.3f}, (byte) 100, (byte) 1));
        landscapeTemplates.put(2, new Landscape(new byte[]{0, 1}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 5));

        landscapeTemplates.put(3, new Landscape(new byte[]{8, 9}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 1));
        landscapeTemplates.put(4, new Landscape(new byte[]{8, 9}, new float[]{0.7f, 0.3f}, (byte) 100, (byte) 1));
        landscapeTemplates.put(5, new Landscape(new byte[]{8, 9}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 5));

        landscapeTemplates.put(6, new Landscape(new byte[]{10, 25}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 1));
        landscapeTemplates.put(7, new Landscape(new byte[]{10, 25}, new float[]{0.7f, 0.3f}, (byte) 100, (byte) 1));
        landscapeTemplates.put(8, new Landscape(new byte[]{10, 25}, new float[]{0.7f, 0.3f}, (byte) 50, (byte) 5));

        landscapeTemplates.put(9, new Landscape(
                new byte[]{9, (byte) 137, (byte) 138},
                new float[]{0.5f, 0.25f, 0.25f},
                (byte) 50,
                (byte) 1
        ));

        landscapeTemplates.put(10, new Landscape(
                new byte[]{9, (byte) 137, (byte) 138},
                new float[]{0.5f, 0.25f, 0.25f},
                (byte) 100,
                (byte) 1
        ));

        landscapeTemplates.put(11, new Landscape(
                new byte[]{9, (byte) 137, (byte) 138},
                new float[]{0.5f, 0.25f, 0.25f},
                (byte) 50,
                (byte) 5
        ));
    }

    /* ===================== HASH ===================== */

    public int getHashIdForTime(LocalDateTime time) {
        long bin = time
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();


        int hashId = CRC32.compute(ByteBuffer.allocate(8).putLong(bin).array());

        int finalHashId = hashId;
        while (GameServerLogic.getInstance().regMaps
                .values()
                .stream()
                .anyMatch(x -> x.getMap() == finalHashId)) {

            bin ^= hashId;
            hashId = CRC32.compute(ByteBuffer.allocate(8).putLong(bin).array());
        }

        return hashId;
    }

    /* ===================== INTERNAL ===================== */

    private byte getNextTemplateByDistribution(Landscape landscape) {
        float rnd = (float) (
                Math.random() *
                        (landscape.distribution[landscape.ratios.length] - landscape.distribution[0])
                        + landscape.distribution[0]
        );

        for (int i = 0; i < landscape.bricks.length; i++) {
            if (rnd >= landscape.distribution[i] &&
                    rnd < landscape.distribution[i + 1]) {
                return landscape.bricks[i];
            }
        }

        return landscape.bricks[0];
    }

    /* ===================== GENERATION ===================== */

    public UserMap generateInternal(Landscape landscape, int skyboxIndex) {

        byte size = landscape.size;
        byte height = landscape.height;

        UserMap map = new UserMap();
        map.skybox = skyboxIndex;

        map.min.x = 0;
        map.min.y = 0;
        map.min.z = 0;

        map.max.x = size;
        map.max.y = size;
        map.max.z = size;

        map.cenX = (map.min.x + map.max.x) * 0.5f;
        map.cenZ = (map.min.z + map.max.z) * 0.5f;

        List<Integer> morphes = new ArrayList<>();

        int seq = 0;

        for (byte x = 0; x < size; x++) {
            for (byte z = 0; z < size; z++) {
                for (byte y = 0; y < height; y++) {
                    byte template = getNextTemplateByDistribution(landscape);
                    map.addBrickInst(seq, template, x, y, z, (byte) 0, morphes);
                    seq++;
                }
            }
        }

        return map;
    }

    public UserMap generate(int landscapeIndex, int skyboxIndex) {
        return generateInternal(landscapeTemplates.get(landscapeIndex), skyboxIndex);
    }
}
