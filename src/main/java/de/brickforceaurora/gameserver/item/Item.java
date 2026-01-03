package de.brickforceaurora.gameserver.item;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.data.MyInfoManager;
import de.brickforceaurora.gameserver.item.template.TItem;
import de.brickforceaurora.gameserver.item.template.TSpecial;
import de.brickforceaurora.gameserver.util.StringMgr;

public class Item {

    public static final int MAX_UPGRADE_LEVEL = 10;

    private long seq;
    private TItem tItem;
    private String code;
    private ItemUsage usage;

    private int remain;
    private int amount;
    private int amountBuf;

    // sbyte in C#
    private byte premium;

    private int durability;

    // sbyte in C#
    public byte toolSlot = -1;

    public UpgradeProp[] upgradeProps;

    /* =====================
       Getters / setters
       ===================== */

    public long getSeq() {
        return seq;
    }

    public TItem getTemplate() {
        return tItem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ItemUsage getUsage() {
        return usage;
    }

    public void setUsage(ItemUsage usage) {
        this.usage = usage;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountBuf() {
        return amountBuf;
    }

    public void setAmountBuf(int amountBuf) {
        this.amountBuf = amountBuf;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    /* =====================
       Premium flags
       ===================== */

    public boolean isPremium() {
        validatePremium();
        return premium == 1;
    }

    public boolean isPCBang() {
        validatePremium();
        return premium == 2;
    }

    public boolean isPremiumOrPCBang() {
        validatePremium();
        return premium != 0;
    }

    private void validatePremium() {
        if (premium != 0 && premium != 1 && premium != 2) {
            GameServerApp.logger().error("Invalid Premium value");
        }
    }

    /* =====================
       Derived properties
       ===================== */

    public float getStarRate() {
        return tItem.getStarRate();
    }

    public boolean isEnoughToConsume() {
        if (tItem == null || !tItem.isAmount) {
            return false;
        }
        if (premium == 1) {
            long num = MyInfoManager.getInstance().haveFunction("premium_account");
            if (num < 0) {
                return false;
            }
        }
        return amount != 0;
    }

    public boolean isTakeoffable() {
        return tItem.takeoffable;
    }

    public boolean isEquipable() {
        if (premium == 1) {
            long num = MyInfoManager.getInstance().haveFunction("premium_account");
            if (num < 0) {
                return false;
            }
        }
        return tItem.isEquipable();
    }

    public boolean isAmount() {
        return tItem.isAmount;
    }

    public int getOpt() {
        if (usage != ItemUsage.NOT_USING) {
            return -1;
        }
        return Math.round(remain / 86400f);
    }

    public String getBpBackCode() {
        return tItem.bpBackCode;
    }

    public boolean isPimped() {
        for (UpgradeProp prop : upgradeProps) {
            if (prop.grade() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeaponSlotAble() {
        return tItem.type == ItemType.WEAPON &&
                (usage == ItemUsage.EQUIP || usage == ItemUsage.UNEQUIP);
    }

    public boolean isShooterSlotAble() {
        if (tItem.type == ItemType.SPECIAL && isEnoughToConsume()) {
            TSpecial tSpecial = (TSpecial) tItem;
            // same as C#, intentionally commented
        }
        return false;
    }

    /* =====================
       Constructor
       ===================== */

    public Item(long itemSeq,
                TItem itemTemplate,
                String itemCode,
                ItemUsage itemUsage,
                int itemRemain,
                byte itemPremium,
                int itemDurability) {

        this.seq = itemSeq;
        this.tItem = itemTemplate;
        this.code = itemCode;
        this.usage = itemUsage;
        this.premium = itemPremium;
        this.durability = itemDurability;

        if (tItem.isAmount) {
            remain = -1;
            amount = itemRemain;
        } else {
            remain = itemRemain;
            amount = -1;
        }

        upgradeProps = new UpgradeProp[13];
        for (int i = 0; i < upgradeProps.length; i++) {
            upgradeProps[i] = new UpgradeProp(false, 0);
        }
    }

    /* =====================
       Logic methods
       ===================== */

    public void refresh(ItemUsage itemUsage, int itemRemain, byte itemPremium, int itemDurability) {
        if (tItem.isAmount) {
            amount = itemRemain;
        } else {
            remain = itemRemain;
        }
        usage = itemUsage;
        if (itemPremium == 0 || itemPremium == 1) {
            premium = itemPremium;
        }
        durability = itemDurability;
    }

    public void buy(int itemRemain, ItemUsage initialUsage, int itemDurability) {
        if (tItem.isAmount) {
            amount = itemRemain;
            if (amount > 0) {
                usage = ItemUsage.UNEQUIP;
            }
        } else {
            remain = itemRemain;
            usage = initialUsage;
        }
        durability = itemDurability;
    }

    public void tickTok() {
        if ((remain >= 0 || amount >= 0)
                && !tItem.isAmount
                && usage != ItemUsage.DELETED
                && usage != ItemUsage.NOT_USING) {

            remain--;
            if (remain < 0) remain = 0;
        }
    }

    public boolean isExpiring() {
        if (remain < 0 && amount < 0) return false;
        if (tItem.isAmount) return false;
        if (usage == ItemUsage.DELETED) return false;
        return remain == 0;
    }

    /* =====================
       String helpers
       ===================== */

    public String getAmountString() {
        if (tItem == null || !tItem.isAmount) return "";
        if (isPremium()) return StringMgr.getInstance().get("JUST_PREMIUM");
        if (isPCBang()) return StringMgr.getInstance().get("PCBANG_INVEN_TAB");
        if (usage == ItemUsage.DELETED) return "";
        if (remain < 0 && amount < 0) return StringMgr.getInstance().get("INFINITE");
        return Integer.toString(amount);
    }

    public String getRemainString() {
        if (isPremium()) return StringMgr.getInstance().get("JUST_PREMIUM");
        if (isPCBang()) return StringMgr.getInstance().get("PCBANG_INVEN_TAB");
        if (usage == ItemUsage.DELETED) return "";
        if (remain < 0 && amount < 0) return StringMgr.getInstance().get("INFINITE");
        if (tItem.isAmount) {
            return amount + StringMgr.getInstance().get("TIMES_UNIT");
        }
        return getRemainString4TermsItem();
    }

    private String getRemainString4TermsItem() {
        if (usage == ItemUsage.DELETED) {
            return StringMgr.getInstance().get("EXPIRED_ITEM");
        }
        int days = Math.round(remain / 86400f);
        int hours = Math.round(remain / 3600f);
        int mins = Math.round(remain / 60f);

        if (remain > 315360000) {
            return StringMgr.getInstance().get("REMAIN_OVERFLOW");
        }
        if (days > 0) return days + StringMgr.getInstance().get("DAYS");
        if (hours > 0) return hours + StringMgr.getInstance().get("HOURS");
        if (mins > 0) return mins + StringMgr.getInstance().get("MINUTES");
        return StringMgr.getInstance().get("UNDER_ONE_MINUTE");
    }

    /* =====================
       Filtering / sorting
       ===================== */

    public int compare(Item a) {
        int s1 = tItem.season <= 0 ? 1 : tItem.season;
        int s2 = a.tItem.season <= 0 ? 1 : a.tItem.season;

        if (s1 == s2) {
            return tItem.name.compareTo(a.tItem.name);
        }
        return -Integer.compare(tItem.season, a.tItem.season);
    }

    public boolean isFiltered(int filter) {
        switch (filter) {
            case 0: return true;
            case 1: return usage == ItemUsage.EQUIP || usage == ItemUsage.UNEQUIP;
            case 2: return usage == ItemUsage.NOT_USING;
            case 3: return usage != ItemUsage.DELETED
                    && usage != ItemUsage.NOT_USING
                    && tItem.upgradeCategory != UpgradeCategory.NONE;
            case 4: return canBeMerged();
            default: return false;
        }
    }

    public boolean canBeMerged() {
        if (tItem.isAmount) return false;
        if (isPremium() || isPCBang()) return false;
        if (usage == ItemUsage.DELETED) return true;
        if (remain < 0 && amount < 0) return false;
        return true;
    }

    public boolean canUpgradeable() {

        int usableUpgradeSlots = 0;
        int maxUpgradeSlots = 13;

        for (int slotIndex = 0; slotIndex < maxUpgradeSlots; slotIndex++) {
            if (UpgradePropManager.getInstance()
                    .useProp(this.getTemplate().upgradeCategory.value, slotIndex)) {
                usableUpgradeSlots++;
            }
        }

        int maxedUpgradeCount = 0;
        for (int slotIndex = 0; slotIndex < maxUpgradeSlots; slotIndex++) {
            if (upgradeProps[slotIndex].use() && upgradeProps[slotIndex].grade() >= 10) {
                maxedUpgradeCount++;
            }
        }

        // If all usable slots are already maxed, no upgrade is possible
        return usableUpgradeSlots != maxedUpgradeCount;
    }


    public boolean isUpgradedItem() {
        if (usage == ItemUsage.NOT_USING ||
                tItem.upgradeCategory == UpgradeCategory.NONE) {
            return false;
        }
        for (UpgradeProp prop : upgradeProps) {
            if (prop.grade() > 0) return true;
        }
        return false;
    }
}
