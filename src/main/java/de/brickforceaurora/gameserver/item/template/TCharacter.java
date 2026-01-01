package de.brickforceaurora.gameserver.item.template;

import de.brickforceaurora.gameserver.item.ItemSlot;
import de.brickforceaurora.gameserver.item.ItemType;
import de.brickforceaurora.gameserver.item.UpgradeCategory;
import de.brickforceaurora.gameserver.util.Texture2D;

public class TCharacter extends TItem {

    public String prefix;
    public String gender;
    public String mainMat;

    public TCharacter(
            String itemCode,
            String itemName,
            Texture2D itemIcon,
            int ct,
            boolean itemTakeoffable,
            String gender,
            String prefix,
            String itemComment,
            TBuff tb,
            boolean itemDiscomposable,
            String itemBpBackCode,
            int season,
            String itemMainMat,
            String grp1,
            String grp2,
            String grp3,
            int starRate
    ) {
        super(
                itemCode,
                ItemType.CHARACTER,
                itemName,
                itemIcon,
                ct,
                0,
                itemTakeoffable,
                ItemSlot.CHARACTER,
                itemComment,
                tb,
                itemDiscomposable,
                itemBpBackCode,
                -1,
                UpgradeCategory.NONE,
                false,
                starRate
        );

        this.gender = gender;
        this.prefix = prefix;
        this.season = season;
        this.mainMat = itemMainMat;

        this.grp1 = grp1;
        this.grp2 = grp2;
        this.grp3 = grp3;
    }
}
