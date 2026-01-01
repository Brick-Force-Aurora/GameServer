package de.brickforceaurora.gameserver.item;

import de.brickforceaurora.gameserver.item.template.TBuff;
import de.brickforceaurora.gameserver.util.Texture2D;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;

import java.io.File;
import java.util.*;

public final class BuffManager {

    public static final int BF_NON_NMCAFE_USER = 0;
    public static final int BF_NMCAFE_USER_CHECKIN = 1;

    public BuffDesc[] why;
    public Texture2D[] icons;

    private boolean isLoaded;
    private boolean isLoading;

    private final Map<Integer, TBuff> dic = new HashMap<>();
    private final Map<String, TBuff> dicByName = new HashMap<>();

    public int netCafeCode;
    public boolean isPcBangShowDialog;

    /* =======================
       Singleton
       ======================= */

    private static final BuffManager INSTANCE = new BuffManager();

    public static BuffManager getInstance() {
        INSTANCE.ensureLoaded();
        return INSTANCE;
    }

    private BuffManager() {
        // do not load here
    }

    private synchronized void ensureLoaded() {
        if (!isLoaded && !isLoading) {
            isLoading = true;
            isLoaded = loadFromLocalFileSystem();
            isLoading = false;
        }
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    /* =======================
       Icon helpers
       ======================= */

    public Texture2D getPointUpTex() {
        return icons[0];
    }

    public Texture2D getXpUpTex() {
        return icons[1];
    }

    public Texture2D getLuckUpTex() {
        return icons[2];
    }

    /* =======================
       Buff access
       ======================= */

    private boolean add(int index, String name, TBuff buff) {
        if (dic.containsKey(index) || dicByName.containsKey(name)) {
            return false;
        }
        dic.put(index, buff);
        dicByName.put(name, buff);
        return true;
    }

    public TBuff get(int index) {
        return dic.get(index);
    }

    public TBuff get(String name) {
        return dicByName.get(name);
    }

    /* =======================
       WHY mask helpers
       ======================= */

    public BuffDesc[] toWhyArray(long mask) {
        List<BuffDesc> list = new ArrayList<>();
        long bit = 1L;

        for (int i = 0; i < 64 && i < why.length; i++) {
            if ((bit & mask) != 0L && !why[i].icon().name.contains("premium")) {
                list.add(why[i]);
            }
            bit <<= 1;
        }

        return list.toArray(new BuffDesc[0]);
    }

    /* =======================
       Loading
       ======================= */

    private boolean loadFromLocalFileSystem() {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/buff.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parse(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parse(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            int index = o.getAsInt("index");
            String name = o.getAsString("name");

            TBuff buff = new TBuff(
                    index,
                    o.getAsBoolean("isPoint"),
                    o.getAsBoolean("isXp"),
                    o.getAsBoolean("isLuck"),
                    o.getAsFloat("factor")
            );

            if (!add(index, name, buff)) {
                System.err.println("Fail to add buff: " + index);
            }
        }
    }
}

