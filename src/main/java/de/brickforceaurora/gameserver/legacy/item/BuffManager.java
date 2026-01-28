package de.brickforceaurora.gameserver.legacy.item;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.legacy.item.template.TBuff;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;
import me.lauriichan.laylib.json.io.JsonSyntaxException;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.resource.source.IDataSource;

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

    private final SnowFrame<?> frame;

    /* =======================
       Singleton
       ======================= */

    private static final BuffManager INSTANCE = new BuffManager();

    public static BuffManager getInstance() {
        INSTANCE.ensureLoaded();
        return INSTANCE;
    }

    private BuffManager() {
        this.frame = GameServerApp.get().snowFrame();
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

    private boolean add(final int index, final String name, final TBuff buff) {
        if (dic.containsKey(index) || dicByName.containsKey(name)) {
            return false;
        }
        dic.put(index, buff);
        dicByName.put(name, buff);
        return true;
    }

    public TBuff get(final int index) {
        return dic.get(index);
    }

    public TBuff get(final String name) {
        return dicByName.get(name);
    }

    /* =======================
       WHY mask helpers
       ======================= */

    public BuffDesc[] toWhyArray(final long mask) {
        final List<BuffDesc> list = new ArrayList<>();
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

    private IJson<?> readJson(final String path) throws IllegalStateException, IOException, JsonSyntaxException {
        final IDataSource source = frame.resource(path);
        if (!source.exists()) {
            throw new IllegalStateException("File doesn't exist: " + source.getPath());
        }
        try (InputStream stream = source.openReadableStream()) {
            return JsonParser.fromStream(stream);
        }
    }

    private boolean loadFromLocalFileSystem() {
        try {
            final IJson<?> root = readJson("jar://buff.json");

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parse(root.asJsonArray());
            return true;

        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parse(final JsonArray array) {

        for (final IJson<?> elem : array) {
            final JsonObject o = elem.asJsonObject();

            final int index = o.getAsInt("index");
            final String name = o.getAsString("name");

            final TBuff buff = new TBuff(index, o.getAsBoolean("isPoint"), o.getAsBoolean("isXp"), o.getAsBoolean("isLuck"),
                o.getAsFloat("factor"));

            if (!add(index, name, buff)) {
                GameServerApp.logger().error("Fail to add buff: " + index);
            }
        }
    }
}
