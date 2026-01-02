package de.brickforceaurora.gameserver.maps;

import de.brickforceaurora.gameserver.math.Vector3;

public record SpawnerDesc(
        int sequence,
        Vector3 position,
        byte rotation
) {}

