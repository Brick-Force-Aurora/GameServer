package de.brickforceaurora.gameserver.match;

import java.util.Map;

import de.brickforceaurora.gameserver.combat.WeaponBy;

public class KillLogEntry {
    public int id;
    public byte killerType;
    public int killer;
    public byte victimType;
    public int victim;
    public WeaponBy weaponBy;
    public int slot;
    public int category;
    public int hitpart;
    public final Map<Integer, Integer> damageLog;
    public KillLogEntry(int _id, byte _killerType, int _killer, byte _victimType, int _victim, WeaponBy _weaponBy, int _slot, int _category, int _hitpart, Map<Integer, Integer> _damageLog)
    {
        id = _id;
        killerType = _killerType;
        killer = _killer;
        victimType = _victimType;
        victim = _victim;
        weaponBy = _weaponBy;
        slot = _slot;
        category = _category;
        hitpart = _hitpart;
        damageLog = _damageLog;
    }
}
