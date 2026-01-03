package de.brickforceaurora.gameserver.combat;

public class HitPart
{
    public enum TYPE
    {
        HEAD,
        BODY,
        ARM,
        FOOT,
        BRAIN
    }

    public float damageFactor = 1f;

    public TYPE part;
}

