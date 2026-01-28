package de.brickforceaurora.gameserver.legacy.item.template;

import de.brickforceaurora.gameserver.legacy.item.ItemSlot;
import de.brickforceaurora.gameserver.legacy.item.ItemType;
import de.brickforceaurora.gameserver.legacy.item.UpgradeCategory;
import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class TCostume extends TItem {

    public String main;
    public String aux;
    public String mark;

    public int functionMask;
    public float functionFactor;

    public String mainMat;
    public String auxMat;
    public String markMat;

    public int armor;

    private int[] ahArmor;
    private int ahKey;
    private int ahIndex;

    public TCostume(final String itemCode, final String itemName, final String itemMain, final String itemAux, final String itemMainMat,
        final String itemAuxMat, final Texture2D itemIcon, final int ct, final int ck, final boolean itemTakeoffable,
        final ItemSlot itemSlot, final String itemComment, final TBuff tb, final boolean itemDiscomposable, final String itemBpBackCode,
        final int itemArmor, final int upCat, final boolean basic, final String itemMark, final String itemMarkMat, final int season,
        final String grp1, final String grp2, final String grp3, final int functionMask, final float functionFactor,
        final Texture2D funcIcon, final int starRate) {
        super(itemCode, ItemType.CLOTH, itemName, itemIcon, ct, ck, itemTakeoffable, itemSlot, itemComment, tb, itemDiscomposable,
            itemBpBackCode, 1, UpgradeCategory.fromValue(upCat), basic, starRate);

        this.main = itemMain;
        this.aux = itemAux;
        this.mainMat = itemMainMat;
        this.auxMat = itemAuxMat;
        this.armor = itemArmor;
        this.mark = itemMark;
        this.markMat = itemMarkMat;

        this.ahArmor = new int[5];
        this.ahKey = itemName.length();
        this.ahIndex = ahKey % 5;
        this.ahArmor[ahIndex] = armor << 1;

        this.season = season;
        this.grp1 = grp1;
        this.grp2 = grp2;
        this.grp3 = grp3;

        this.functionMask = functionMask;
        this.functionFactor = functionFactor;
        this.funcIcon = funcIcon;
    }

    public void resetArmor(final int val) {
        this.armor = val;
        this.ahArmor = new int[5];
        this.ahKey = this.name.length();
        this.ahIndex = ahKey % 5;
        this.ahArmor[ahIndex] = armor << 1;
    }
}
