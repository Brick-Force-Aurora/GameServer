package de.brickforceaurora.gameserver.legacy.data;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import de.brickforceaurora.gameserver.legacy.data.flag.CommonOpt;
import de.brickforceaurora.gameserver.legacy.data.flag.FlagSet;
import de.brickforceaurora.gameserver.legacy.data.flag.IFlag;
import de.brickforceaurora.gameserver.legacy.item.Item;
import de.brickforceaurora.gameserver.legacy.item.ItemUsage;

public final class MyInfoManager {

    /* ================= SINGLETON ================= */

    private static final MyInfoManager INSTANCE = new MyInfoManager();

    public static MyInfoManager getInstance() {
        return INSTANCE;
    }

    private MyInfoManager() {
        autoLogin = AutoLogin.NONE;
        controlMode = ControlMode.PLAY_MODE;

        inventory = new HashMap<>();
        friends = new HashMap<>();
        bans = new HashMap<>();
        clanee = new HashMap<>();

        qResultEvent = new ArrayDeque<>();
        qDurabilityEvent = new ArrayDeque<>();
        qBattleStartRemain = new ArrayDeque<>();

        shooterTools = new long[5];
        weaponSlots = new long[10];

        clearShooterTool();
        clearWeaponSlot();
        resetWpnChg();
        resetDrpWpn();
    }

    /* ================= FIELDS ================= */

    private final Map<Long, Item> inventory;
    private final Map<Integer, NameCard> friends;
    private final Map<Integer, NameCard> bans;
    private final Map<Integer, NameCard> clanee;

    private final Deque<ResultEvent> qResultEvent;
    private final Deque<DurabilityEvent> qDurabilityEvent;
    private final Deque<String> qBattleStartRemain;

    private final AutoLogin autoLogin;
    private ControlMode controlMode;

    private int seq;
    private int xp;
    private int rank;
    private final int clanSeq = -1;
    private final String clanName = "";
    private final int clanLv = -1;

    private int gm;
    private boolean switchGOD;

    public final FlagSet<CommonOpt> commonOptions = new FlagSet<>(IFlag.combine(CommonOpt.ALL));

    private final long[] shooterTools;
    private final long[] weaponSlots;
    private final long[] wpnChg = new long[4];
    private final long[] drpItm = new long[4];

    /* ================= BASIC GETTERS ================= */

    public boolean isGM() {
        return gm != 0;
    }

    public boolean isPremiumAccount() {
        return haveFunction("premium_account") >= 0;
    }

    public boolean isClanMember() {
        return clanLv >= 0;
    }

    /* ================= INVENTORY ================= */

    public Item getItemBySequence(final long seq) {
        return inventory.get(seq);
    }

    public void erase(final long seq) {
        for (int i = 0; i < weaponSlots.length; i++) {
            if (weaponSlots[i] == seq) {
                weaponSlots[i] = -1;
            }
        }
        for (int i = 0; i < shooterTools.length; i++) {
            if (shooterTools[i] == seq) {
                shooterTools[i] = -1;
            }
        }
        inventory.remove(seq);
    }

    public Item getUsingEquipByCode(final String code) {
        for (final Item item : inventory.values()) {
            if (code.equals(item.getCode()) && item.getUsage() == ItemUsage.EQUIP) {
                return item;
            }
        }
        return null;
    }

    /* ================= GM ================= */

    public int clearGmFunction() {
        switchGOD = false;
        controlMode = ControlMode.PLAY_MODE;
        return 0;
    }

    /* ================= HELPERS ================= */

    private void clearWeaponSlot() {
        Arrays.fill(weaponSlots, -1L);
    }

    private void clearShooterTool() {
        Arrays.fill(shooterTools, -1L);
    }

    private void resetWpnChg() {
        Arrays.fill(wpnChg, -1L);
    }

    private void resetDrpWpn() {
        Arrays.fill(drpItm, -1L);
    }

    /* ================= STUBS (match original behavior) ================= */

    public long haveFunction(final String func) {
        // intentionally stubbed like your commented C# code
        return -1L;
    }

    /* ================= PLACEHOLDER TYPES ================= */

    public static class NameCard {
        public int seq;
        public String nickname;
        public int lv;
        public int svrId;
        public int rank;
        public boolean isConnected;

        public NameCard(final int seq, final String nickname, final int lv, final int svrId, final int rank) {
            this.seq = seq;
            this.nickname = nickname;
            this.lv = lv;
            this.svrId = svrId;
            this.rank = rank;
        }
    }

    public static class DurabilityEvent {
        public final String code;
        public final int durability;
        public final int diff;

        public DurabilityEvent(final String code, final int durability, final int diff) {
            this.code = code;
            this.durability = durability;
            this.diff = diff;
        }
    }
}
