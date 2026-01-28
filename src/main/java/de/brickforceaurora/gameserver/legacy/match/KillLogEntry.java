package de.brickforceaurora.gameserver.legacy.match;

import java.util.Map;

import de.brickforceaurora.gameserver.legacy.combat.WeaponBy;

public record KillLogEntry(int id, byte killerType, int killer, byte victimType, int victim, WeaponBy weaponBy, int slot, int category,
    int hitpart, Map<Integer, Integer> damageLog) {}
