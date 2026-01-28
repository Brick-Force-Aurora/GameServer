package de.brickforceaurora.gameserver.legacy.maps;

import de.brickforceaurora.gameserver.legacy.math.Vector3;

public record SpawnerDesc(
        int sequence,
        Vector3 position,
        byte rotation
) {}

