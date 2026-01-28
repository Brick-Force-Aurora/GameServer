package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TAccessory extends TItem {

    public String prefab;
    public String bone;

    public int functionMask;
    public float functionFactor;
    public int armor;

    private int[] ah_armor;
    private int ah_key;
    private int ah_index;

    public TAccessory(final String itemCode, final String itemName, final String itemBone, final String itemPrefab,
        final Texture2D itemIcon, final int ct, final int ck, final boolean itemTakeoffable, final ItemSlot itemSlot,
        final String itemComment, final TBuff tb, final boolean itemDiscomposable, final String itemBpBackCode, final int functionMask,
        final int armor, final float functionFactor, final int upCat, final int season, final String grp1, final String grp2,
        final String grp3, final Texture2D funcIcon, final int starRate) {
        super(itemCode, ItemType.ACCESSORY, itemName, itemIcon, ct, ck, itemTakeoffable, itemSlot, itemComment, tb, itemDiscomposable,
            itemBpBackCode, 1, UpgradeCategory.fromValue(upCat), false, starRate);

        this.armor = armor;
        this.functionMask = functionMask;
        this.prefab = itemPrefab;
        this.bone = itemBone;
        this.season = season;

        this.ah_armor = new int[5];
        this.ah_key = itemName.length();
        this.ah_index = ah_key % 5;
        this.ah_armor[ah_index] = armor << 1;

        this.functionFactor = functionFactor;

        this.grp1 = grp1;
        this.grp2 = grp2;
        this.grp3 = grp3;

        this.funcIcon = funcIcon;
    }

    public void resetArmor(final int val) {
        this.armor = val;
        this.ah_armor = new int[5];
        this.ah_key = getName().length();
        this.ah_index = ah_key % 5;
        this.ah_armor[ah_index] = armor << 1;
    }
}
