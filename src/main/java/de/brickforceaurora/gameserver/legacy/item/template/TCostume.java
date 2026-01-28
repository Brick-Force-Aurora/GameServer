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

    public TCostume(
            String itemCode,
            String itemName,
            String itemMain,
            String itemAux,
            String itemMainMat,
            String itemAuxMat,
            Texture2D itemIcon,
            int ct,
            int ck,
            boolean itemTakeoffable,
            ItemSlot itemSlot,
            String itemComment,
            TBuff tb,
            boolean itemDiscomposable,
            String itemBpBackCode,
            int itemArmor,
            int upCat,
            boolean basic,
            String itemMark,
            String itemMarkMat,
            int season,
            String grp1,
            String grp2,
            String grp3,
            int functionMask,
            float functionFactor,
            Texture2D funcIcon,
            int starRate
    ) {
        super(
                itemCode,
                ItemType.CLOTH,
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
                basic,
                starRate
        );

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

    public void resetArmor(int val) {
        this.armor = val;
        this.ahArmor = new int[5];
        this.ahKey = this.name.length();
        this.ahIndex = ahKey % 5;
        this.ahArmor[ahIndex] = armor << 1;
    }
}
