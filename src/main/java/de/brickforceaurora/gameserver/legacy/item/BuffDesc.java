package de.brickforceaurora.gameserver.legacy.item;

import de.brickforceaurora.gameserver.legacy.util.Texture2D;

import java.io.Serial;
import java.io.Serializable;

public record BuffDesc(
        Texture2D icon,
        String tooltip
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}

