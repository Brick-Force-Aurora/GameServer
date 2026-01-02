package de.brickforceaurora.gameserver.data;

import de.brickforceaurora.gameserver.data.flag.CommonOpt;
import de.brickforceaurora.gameserver.data.flag.IFlag;

public class DummyData {

    public int xp = 7_000_000;
    public int forcePoints = 100_000_000;
    public int brickPoints = 100_000_000;
    public int tokens = 100_000_000;
    public int coins = 100_000_000;
    public int starDust = 100_000_000;
    public int gm = 0;

    public int clanSeq = 0;
    public String clanName = "Clan";
    public int clanMark = 0;
    public int clanLv = 0;

    public int rank = 60;

    public int heavy = 0;
    public int assault = 0;
    public int sniper = 0;
    public int subMachine = 0;
    public int handGun = 0;
    public int melee = 0;
    public int special = 0;

    public byte tutorialed = 3;
    public int countryFilter = -1;
    public byte tos = 1;

    public int extraSlots = 0;
    public int firstLoginFp = 0;

    public int qjCommonMask = IFlag.combine(CommonOpt.ALL);

    public int qjModeMask = 0;
    public int qjOfficialMask = 0;

    public static final String[][] startingGear = {
            { "MAIN", "wau" },
            { "AUX", "wax" },
            { "BOMB", "wba" },
            { "MELEE", "wap" },
            { "UPPER", "aac" },
            { "LOWER", "aad" },
            { "BRICK-GUN", "s07" },
            { "NONE", "sb7", "sb8", "sb9", "sc0", "sc1", "sc2", "sc3", "sc4", "s34", "s43", "s78", "s79" }
    };
}

