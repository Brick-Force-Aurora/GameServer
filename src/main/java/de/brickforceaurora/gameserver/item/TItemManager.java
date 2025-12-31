package de.brickforceaurora.gameserver.item;

import de.brickforceaurora.gameserver.util.Texture2D;
import de.brickforceaurora.gameserver.util.CSVLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public final class TItemManager {

    private boolean isLoaded;
    private boolean isIconLoaded;
    private boolean isWeaponByLoaded;
    private boolean isMaterialLoaded;

    public int icnVersion = 1;
    public int wpnbyVersion = 1;
    public int mtlVersion = 1;

    public String[] materials;
    public Texture2D[] icons;
    public Texture2D[] weaponBy;

    private final Map<String, TItem> dic;
    public String[] prefabs;
    public String[] coatBodyCodes;
    public String[] onlyOneCountingCodes;

    private boolean iconRequesting;

    private final Map<Integer, Integer> wpnBy2Slot;
    private final Map<Integer, Integer> wpnBy2Category;

    /* ===================== SINGLETON ===================== */

    private static volatile TItemManager instance;

    public static TItemManager getInstance() {
        if (instance == null) {
            synchronized (TItemManager.class) {
                if (instance == null) {
                    instance = new TItemManager();
                    instance.loadAll();
                }
            }
        }
        return instance;
    }

    private TItemManager() {
        this.dic = new HashMap<>();
        this.wpnBy2Slot = new HashMap<>();
        this.wpnBy2Category = new HashMap<>();
        this.iconRequesting = false;
    }

    /* ===================== GETTERS ===================== */

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isIconLoaded() {
        return isIconLoaded;
    }

    public boolean isWeaponByLoaded() {
        return isWeaponByLoaded;
    }

    public boolean isMaterialLoaded() {
        return isMaterialLoaded;
    }

    @SuppressWarnings("unchecked")
    public <T extends TItem> T get(String key) {
        return (T) dic.get(key);
    }

    /* ===================== INTERNAL ===================== */

    private void add(String code, TItem item) {
        if (dic.containsKey(code)) {
            System.out.println("ERROR, duplicated item code " + code);
            return;
        }
        dic.put(code, item);
    }

    /* ===================== LOAD ALL ===================== */

    public void loadAll() {
        isLoaded = loadCostumeFromLocalFileSystem() && loadWeaponFromLocalFileSystem() && loadAccessoryFromLocalFileSystem() &&
                        loadCharacterFromLocalFileSystem() && loadSpecialFromLocalFileSystem() && loadUpgradeFromLocalFileSystem() &&
                        loadBundleFromLocalFileSystem();
    }

    /* ===================== FILE SYSTEM ===================== */

    private Path resourcesDir() {
        return Paths.get(System.getProperty("user.dir"), "Resources");
    }

    private CSVLoader loadCsv(String relativePath) {
        Path file = resourcesDir().resolve(relativePath);
        if (!Files.exists(file)) {
            System.out.println("ERROR, missing resource file " + file);
            return null;
        }
        CSVLoader loader = new CSVLoader();
        if (!loader.securedLoad(file.toString()) && !loader.load(file.toString())) {
            System.out.println("ERROR, failed to load " + file);
            return null;
        }
        return loader;
    }

    /* ===================== BUNDLE ===================== */

    private boolean loadBundleFromLocalFileSystem() {
        CSVLoader csv = loadCsv("Template/bundle.txt");
        if (csv == null) return false;
        parseBundle(csv);
        return true;
    }

    private void parseBundle(CSVLoader csv) {
        for (int i = 0; i < csv.getRows(); i++) {
            String code = csv.readString(0, i, "").trim().toLowerCase();
            String name = csv.readString(1, i, "").trim();
            String icon = csv.readString(2, i, "").trim();
            String typeStr = csv.readString(3, i, "").trim().toLowerCase();
            boolean discomposable = csv.readBool(4, i, false);
            String comment = csv.readString(5, i, "");
            int count = Integer.parseInt(csv.readString(6, i, "0").trim());
            int starRate = csv.readInt(7, i, 100);

            int ct = TItem.string2Type(typeStr);
            add(code, new TBundle(code, name, findIcon(icon), ct, discomposable, comment, count, starRate));
        }
    }

    /* ===================== WEAPON ===================== */

    private boolean loadWeaponFromLocalFileSystem() {
        CSVLoader csv = loadCsv("Template/weapon.txt");
        if (csv == null) return false;
        parseWeapon(csv);
        return true;
    }

    private void parseWeapon(CSVLoader csvLoader) {

        for (int row = 0; row < csvLoader.getRows(); row++) {

            String itemCode = csvLoader.readString(0, row, "").trim().toLowerCase();
            String itemName            = csvLoader.readString(1, row, "").trim();
            String iconName            = csvLoader.readString(2, row, "").trim();
            String icon11Name          = csvLoader.readString(3, row, "").trim();
            String typeString          = csvLoader.readString(4, row, "").trim().toLowerCase();
            String kindString          = csvLoader.readString(5, row, "").trim().toLowerCase();
            String slotString          = csvLoader.readString(6, row, "").trim().toLowerCase();
            String weaponCatString     = csvLoader.readString(7, row, "").trim().toLowerCase();
            boolean takeoffable        = csvLoader.readBool(8, row, true);
            String prefab              = csvLoader.readString(9, row, "").trim();
            String prefab11            = csvLoader.readString(10, row, "").trim();
            String bone                = csvLoader.readString(11, row, "").trim();
            String comment             = csvLoader.readString(12, row, "").trim();
            String buffKey             = csvLoader.readString(13, row, "").trim();
            boolean discomposable      = csvLoader.readBool(14, row, false);
            String bpBackCode          = csvLoader.readString(15, row, "").trim();
            int durabilityMax          = csvLoader.readInt(16, row, -1);
            String upgradeCatString    = csvLoader.readString(17, row, "").trim().toLowerCase();
            boolean basic              = csvLoader.readBool(18, row, false);
            String seasonString        = csvLoader.readString(19, row, "").trim();
            String grp1                = csvLoader.readString(20, row, "").trim();
            String grp2                = csvLoader.readString(21, row, "").trim();
            String grp3                = csvLoader.readString(22, row, "").trim();
            boolean twoHands           = csvLoader.readBool(23, row, false);
            int starRate               = csvLoader.readInt(24, row, 100);

            // ---- enum / lookup conversions ----
            int ct = TItem.string2Type(typeString);
            int ck = TItem.string2Kind(ct, kindString);
            int cc = TWeapon.String2WeaponCategory(weaponCatString);
            int upCat = TItem.string2UpgradeCategory(upgradeCatString);

            ItemSlot slot = TItem.string2Slot(slotString);
            TBuff buff = BuffManager.getInstance().get(buffKey);

            int season = Integer.parseInt(seasonString);

            // ---- construct weapon ----
            TWeapon weapon = new TWeapon(
                    itemCode,
                    itemName,
                    bone,
                    findPrefab(prefab),
                    findPrefab(prefab11),
                    findIcon(iconName),
                    findIcon(icon11Name),
                    ct,
                    ck,
                    cc,
                    takeoffable,
                    slot,
                    comment,
                    buff,
                    discomposable,
                    bpBackCode,
                    durabilityMax,
                    upCat,
                    basic,
                    season,
                    grp1,
                    grp2,
                    grp3,
                    twoHands,
                    starRate
            );

            add(itemCode, weapon);
        }
    }

    private boolean loadCostumeFromLocalFileSystem()
    {
        CSVLoader csv = loadCsv("Template/costume.txt");
        if (csv == null) return false;
        parseCostume(csv);
        return true;
    }

    private boolean loadAccessoryFromLocalFileSystem()
    {
        CSVLoader csv = loadCsv("Template/accessory.txt");
        if (csv == null) return false;
        parseAccessory(csv);
        return true;
    }

    private boolean loadCharacterFromLocalFileSystem()
    {
        CSVLoader csv = loadCsv("Template/character.txt");
        if (csv == null) return false;
        parseCharacter(csv);
        return true;
    }

    private boolean loadSpecialFromLocalFileSystem()
    {
        CSVLoader csv = loadCsv("Template/special.txt");
        if (csv == null) return false;
        parseSpecial(csv);
        return true;
    }

    private boolean loadUpgradeFromLocalFileSystem()
    {
        CSVLoader csv = loadCsv("Template/upgrade.txt");
        if (csv == null) return false;
        parseUpgrade(csv);
        return true;
    }


    /* ===================== STUBS ===================== */

    private Texture2D findIcon(String name) {
        return null; // server-side stub
    }

    private String findPrefab(String name) {
        return name;
    }

    private String findMaterial(String name) {
        return name;
    }

    /* ===================== LOOKUPS ===================== */

    public int weaponBy2Slot(int weaponBy) {
        return wpnBy2Slot.getOrDefault(weaponBy, -1);
    }

    public int weaponBy2Category(int weaponBy) {
        return wpnBy2Category.getOrDefault(weaponBy, -1);
    }
}
