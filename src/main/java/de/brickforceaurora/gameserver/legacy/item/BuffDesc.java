package de.brickforceaurora.gameserver.legacy.item;

import java.io.Serial;
import java.io.Serializable;

import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public record BuffDesc(Texture2D icon, String tooltip) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
