package de.brickforceaurora.gameserver.item;

import de.brickforceaurora.gameserver.util.StringMgr;
import de.brickforceaurora.gameserver.util.Texture2D;

public class TItem {

    /* =====================
       Fields
       ===================== */

    public String code;

    private Texture2D icon;
    public Texture2D icon11;
    public Texture2D funcIcon;

    public int season;

    public String grp1 = "none";
    public String grp2 = "none";
    public String grp3 = "none";

    private boolean isBasic;

    public ItemType type;
    public String name;

    public int catType;
    public int catKind;

    public boolean takeoffable;
    public ItemSlot slot;

    public String comment = "";
    public String exp1 = "";
    public String exp2 = "";
    public String exp3 = "";

    public TBuff tBuff;
    public boolean discomposable;
    public String bpBackCode;

    public int upgradeType;
    public UpgradeCategory upgradeCategory;

    private int starRate = 100;

    public boolean isAmount;

    /* =====================
       Static lookup tables
       ===================== */

    private static final String[] types = {
            "premium",
            "weapon",
            "cloth",
            "accessory",
            "character",
            "action",
            "toolbox",
            "upgrade",
            "bundle",
            "etc"
    };

    private static final String[] weaponKinds = {
            "all",
            "main",
            "aux",
            "melee",
            "spec"
    };

    private static final String[] clothKinds = {
            "all",
            "helmet",
            "upper",
            "lower"
    };

    private static final String[] accessoryKinds = {
            "all",
            "holster",
            "magazine_l",
            "magazine_r",
            "bag",
            "mask",
            "legcase",
            "kit",
            "bottle"
    };

    private static final String[] slotString = {
            "upper",
            "lower",
            "melee",
            "aux",
            "main",
            "bomb",
            "head",
            "face",
            "back",
            "leg",
            "sash1",
            "sash2",
            "sash3",
            "launcher",
            "magazine_l",
            "magazine_r",
            "kit",
            "character"
    };

    protected static final String[] functionString = {
            "clan_mark",
            "fly",
            "fly_fast",
            "line_tool",
            "replace_tool",
            "auto_reload",
            "heal",
            "assault_ammo",
            "speedup",
            "heartbeat_radar",
            "grenade_ammo",
            "pistol_ammo",
            "heavy_ammo",
            "sniper_ammo",
            "submachine_ammo",
            "respawn",
            "auto_heal",
            "premium_account",
            "heal50",
            "heal30",
            "hp_cooltime",
            "main_ammo_inc",
            "aux_ammo_inc",
            "special_ammo_inc",
            "charge_coin",
            "add_map_slot",
            "reset_map_slot",
            "brickstar_builder",
            "circle_window_tckt",
            "computer_box01_tckt",
            "computer_box02_tckt",
            "computer_box03_tckt",
            "crater_tckt",
            "flammable_drum_tckt",
            "function_set_tckt",
            "gravity_bluea_tckt",
            "gravity_blueb_tckt",
            "gravity_reda_tckt",
            "gravity_redb_tckt",
            "hatch_tckt",
            "laserwall_tckt",
            "metalcabinet_tckt",
            "metalladder_tckt",
            "scifi_set_tckt",
            "solar_collector_tckt",
            "ground01_tckt",
            "ground02_tckt",
            "metal1_tckt",
            "metal2_tckt",
            "metal3_tckt",
            "metal4_tckt",
            "toxic_drum_tckt",
            "trampoline_hor_tckt",
            "trampoline_ver_tckt",
            "valve_tckt",
            "speed_tckt",
            "explosion_tckt",
            "gravity_tckt",
            "plank_tckt",
            "woodbarrel_tckt",
            "candle_tckt",
            "fence_tckt",
            "bench_tckt",
            "armor_tckt",
            "torch_tckt",
            "flagblue_tckt",
            "flagred_tckt",
            "stained_tckt",
            "window_tckt",
            "trap_tckt",
            "door_tckt",
            "free_protal_tckt",
            "wood_set_tckt",
            "fire_set_tckt",
            "flag_set_tckt",
            "window_set_tckt",
            "team_portal_tckt",
            "portal_set_tckt",
            "normal3_set_tckt",
            "function3_set_tckt",
            "charge_force_point",
            "consumable_xp_bonus",
            "consumable_fp_bonus",
            "consumable_xp_bonus2",
            "consumable_fp_bonus2",
            "consumable_xp_bonus3",
            "consumable_fp_bonus3",
            "nick_name",
            "dash_time_inc",
            "respwan_time_dec",
            "fallen_damage_reduce",
            "just_respawn",
            "record_fully_init",
            "record_team_init",
            "record_individual_init",
            "record_bungee_init",
            "record_explosion_init",
            "record_mission_init",
            "record_bnd_init",
            "record_flag_init",
            "record_weapon_init",
            "minerail01_tckt",
            "minerail02_tckt",
            "drygrass_tckt",
            "woodboard_tckt",
            "wheel_tckt",
            "cactus_tckt",
            "westerndoor_tckt",
            "haystack_tckt",
            "mineral_tckt",
            "normal4_set_tckt",
            "function4_set_tckt",
            "season4_set_tckt",
            "special_ammo_add",
            "gold_tckt",
            "tntbarrel_tckt",
            "woodbox_01_tckt",
            "woodbox_02_tckt",
            "reed_tckt",
            "bear_trap_tckt",
            "train_set_tckt"
    };

    private static final String[] upgradeTypes = {
            "weapon",
            "cloth"
    };

    private static final String[] upgradeCategories = {
            "pimp_cat_heavy",
            "pimp_cat_assault",
            "pimp_cat_sniper",
            "pimp_cat_sub_machine",
            "pimp_cat_hand_gun",
            "pimp_cat_melee",
            "pimp_cat_grenade",
            "pimp_cat_flash_bang",
            "pimp_cat_smoke",
            "pimp_cat_upper_lower",
            "pimp_cat_helmet",
            "pimp_cat_other",
            "pimp_cat_shotgun"
    };

    /* =====================
       Properties
       ===================== */

    public boolean isBasic() {
        return isBasic;
    }

    public String getName() {
        return StringMgr.getInstance().get(name);
    }

    public int getStarRateRaw() {
        return starRate;
    }

    public void setStarRateRaw(int value) {
        starRate = value;
    }

    public float getStarRate() {
        return starRate / 100f;
    }

    public boolean isEquipable() {
        return (slot.value >= ItemSlot.UPPER.value && slot.value < ItemSlot.NUM.value)
                || code.equals("s07")
                || code.equals("s08")
                || code.equals("s09")
                || code.equals("s92");
    }

    /* =====================
       Constructor
       ===================== */

    public TItem(String itemCode,
                 ItemType itemType,
                 String itemName,
                 Texture2D itemIcon,
                 int ct,
                 int ck,
                 boolean itemTakeoffable,
                 ItemSlot itemSlot,
                 String itemComment,
                 TBuff tb,
                 boolean itemDiscomposable,
                 String itemBpBackCode,
                 int upType,
                 UpgradeCategory upCat,
                 boolean basic,
                 int starRate) {

        this.code = itemCode;
        this.type = itemType;
        this.name = itemName;
        this.icon = itemIcon;
        this.catType = ct;
        this.catKind = ck;
        this.takeoffable = itemTakeoffable;
        this.slot = itemSlot;
        this.comment = itemComment;
        this.isAmount = false;
        this.tBuff = tb;
        this.discomposable = itemDiscomposable;
        this.bpBackCode = itemBpBackCode;
        this.upgradeType = upType;
        this.upgradeCategory = upCat;
        this.isBasic = basic;
        this.starRate = starRate;
    }

    /* =====================
       Utility methods
       ===================== */

    public void setIcon(Texture2D icon) {
        this.icon = icon;
    }

    public Texture2D getCurIcon() {
        return icon;
    }

    public String getOptionStringByOption(int opt) {
        if (opt >= 1_000_000) {
            return StringMgr.getInstance().get("INFINITE");
        }
        if (isAmount) {
            return opt + " " + StringMgr.getInstance().get("TIMES_UNIT");
        }
        return opt + " " + StringMgr.getInstance().get("DAYS");
    }

    public static int string2Type(String typeString) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(typeString)) {
                return i;
            }
        }
        return types.length;
    }

    public static int string2Kind(int type, String kindString) {
        String[] arr;
        if (type == 1) arr = weaponKinds;
        else if (type == 2) arr = clothKinds;
        else if (type == 3) arr = accessoryKinds;
        else return -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(kindString)) {
                return i;
            }
        }
        return arr.length;
    }

    public static ItemSlot string2Slot(String text) {
        for (int i = 0; i < slotString.length; i++) {
            if (slotString[i].equals(text)) {
                return ItemSlot.values()[i];
            }
        }
        return ItemSlot.NUM;
    }

    public static int string2UpgradeType(String upType) {
        for (int i = 0; i < upgradeTypes.length; i++) {
            if (upgradeTypes[i].equals(upType)) {
                return i;
            }
        }
        return -1;
    }

    public static int string2UpgradeCategory(String cat) {
        for (int i = 0; i < upgradeCategories.length; i++) {
            if (upgradeCategories[i].equals(cat)) {
                return i;
            }
        }
        return -1;
    }

    public static String functionMaskToString(int func) {
        if (func < 0 || func >= functionString.length) {
            return "";
        }
        return functionString[func];
    }

    public static int string2FunctionMask(String func) {
        func = func.toLowerCase();
        for (int i = 0; i < functionString.length; i++) {
            if (functionString[i].equals(func)) {
                return i;
            }
        }
        return -1;
    }
}

