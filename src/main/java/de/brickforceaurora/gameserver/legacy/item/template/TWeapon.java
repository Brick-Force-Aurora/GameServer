package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.item.WeaponType;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TWeapon extends TItem {

    private final String prefab;      // Main prefab (game object) of the weapon
    private final String prefab11;    // Alternative prefab of the weapon
    public String bone;             // Bone associated with the weapon
    public int cat;                 // Weapon category index
    public int durabilityMax;       // Maximum durability of the weapon
    public boolean IsTwoHands;         // Flag indicating if the weapon requires both hands

    private static final String[] categories = {
        "heavy",
        "assault",
        "sniper",
        "submachine",
        "handgun",
        "melee",
        "special"
    };

    public TWeapon(final String itemCode, final String itemName, final String itemBone, final String itemPrefab, final String itemPrefab11,
        final Texture2D itemIcon, final Texture2D itemIcon11, final int ct, final int ck, final int cc, final boolean itemTakeoffable,
        final ItemSlot itemSlot, final String itemComment, final TBuff tb, final boolean itemDiscomposable, final String itemBpBackCode,
        final int itemDurabilityMax, final int upCat, final boolean basic, final int season, final String grp1, final String grp2,
        final String grp3, final boolean twoHands, final int starRate) {
        super(itemCode, ItemType.WEAPON, itemName, itemIcon, ct, ck, itemTakeoffable, itemSlot, itemComment, tb, itemDiscomposable,
            itemBpBackCode, 0, UpgradeCategory.fromValue(upCat), basic, starRate);

        this.prefab = itemPrefab;
        this.prefab11 = itemPrefab11;
        this.icon11 = itemIcon11;
        this.bone = itemBone;
        this.cat = cc;
        this.season = season;
        this.grp1 = grp1;
        this.grp2 = grp2;
        this.grp3 = grp3;
        this.IsTwoHands = twoHands;
        this.durabilityMax = itemDurabilityMax;
    }

    // Returns the current prefab of the weapon
    // Check developer options and age restrictions to determine which prefab to return
    public String CurPrefab() {
        return prefab;
    }

    // Converts a string category to an integer index
    public static int String2WeaponCategory(final String category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == category) {
                return i;
            }
        }
        //logger.Error("[ERROR]: Error, Trying to find category with invalid category " + category);
        return -1;
    }

    // Gets the weapon type based on the slot value
    public WeaponType GetWeaponType() {
        return WeaponType.values()[slot.value - 2];
    }

    // Calculates and returns the discount ratio based on the provided level
    public static int GetDiscountRatio(final int lv) {
        if (lv >= 5) {
            return 15;
        }
        if (lv >= 4) {
            return 8;
        }
        if (lv >= 3) {
            return 5;
        }
        if (lv >= 2) {
            return 3;
        }
        return 0;
    }
}
