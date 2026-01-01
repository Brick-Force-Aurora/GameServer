package de.brickforceaurora.gameserver.item.template;

import de.brickforceaurora.gameserver.item.ItemSlot;
import de.brickforceaurora.gameserver.item.ItemType;
import de.brickforceaurora.gameserver.item.UpgradeCategory;
import de.brickforceaurora.gameserver.util.Texture2D;

public class TAccessory extends TItem {

    public String prefab;
    public String bone;

    public int functionMask;
    public float functionFactor;
    public int armor;

    private int[] ah_armor;
    private int ah_key;
    private int ah_index;

    public TAccessory(
            String itemCode,
            String itemName,
            String itemBone,
            String itemPrefab,
            Texture2D itemIcon,
            int ct,
            int ck,
            boolean itemTakeoffable,
            ItemSlot itemSlot,
            String itemComment,
            TBuff tb,
            boolean itemDiscomposable,
            String itemBpBackCode,
            int functionMask,
            int armor,
            float functionFactor,
            int upCat,
            int season,
            String grp1,
            String grp2,
            String grp3,
            Texture2D funcIcon,
            int starRate
    ) {
        super(
                itemCode,
                ItemType.ACCESSORY,
                itemName,
                itemIcon,
                ct,
                ck,
                itemTakeoffable,
                itemSlot,
                itemComment,
                tb,
                itemDiscomposable,
                itemBpBackCode,
                1,
                UpgradeCategory.fromValue(upCat),
                false,
                starRate
        );

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

    public void resetArmor(int val) {
        this.armor = val;
        this.ah_armor = new int[5];
        this.ah_key = getName().length();
        this.ah_index = ah_key % 5;
        this.ah_armor[ah_index] = armor << 1;
    }
}
