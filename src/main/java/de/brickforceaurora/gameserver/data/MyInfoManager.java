package de.brickforceaurora.gameserver.data;

import java.util.*;

import de.brickforceaurora.gameserver.data.flag.CommonOpt;
import de.brickforceaurora.gameserver.item.Item;
import de.brickforceaurora.gameserver.item.ItemUsage;

public final class MyInfoManager {

    /* ================= SINGLETON ================= */

    private static final MyInfoManager INSTANCE = new MyInfoManager();
    public static MyInfoManager getInstance() { return INSTANCE; }

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

    private Map<Long, Item> inventory;
    private Map<Integer, NameCard> friends;
    private Map<Integer, NameCard> bans;
    private Map<Integer, NameCard> clanee;

    private Deque<ResultEvent> qResultEvent;
    private Deque<DurabilityEvent> qDurabilityEvent;
    private Deque<String> qBattleStartRemain;

    private AutoLogin autoLogin;
    private ControlMode controlMode;

    private int seq;
    private int xp;
    private int rank;
    private int clanSeq = -1;
    private String clanName = "";
    private int clanLv = -1;

    private int gm;
    private boolean switchGOD;

    private int qjCommonMask;

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

    public Item getItemBySequence(long seq) {
        return inventory.get(seq);
    }

    public void erase(long seq) {
        for (int i = 0; i < weaponSlots.length; i++) {
            if (weaponSlots[i] == seq) weaponSlots[i] = -1;
        }
        for (int i = 0; i < shooterTools.length; i++) {
            if (shooterTools[i] == seq) shooterTools[i] = -1;
        }
        inventory.remove(seq);
    }

    public Item getUsingEquipByCode(String code) {
        for (Item item : inventory.values()) {
            if (code.equals(item.getCode()) && item.getUsage() == ItemUsage.EQUIP) {
                return item;
            }
        }
        return null;
    }

    /* ================= GM ================= */

    public boolean getCommonMask(CommonOpt mask) {
        return (qjCommonMask & mask.mask()) != 0;
    }

    public void setCommonMask(CommonOpt mask) {
        qjCommonMask |= mask.mask();
    }

    public void removeCommonMask(CommonOpt mask) {
        qjCommonMask &= ~mask.mask();
    }

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

    public long haveFunction(String func) {
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

        public NameCard(int seq, String nickname, int lv, int svrId, int rank) {
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

        public DurabilityEvent(String code, int durability, int diff) {
            this.code = code;
            this.durability = durability;
            this.diff = diff;
        }
    }
}
