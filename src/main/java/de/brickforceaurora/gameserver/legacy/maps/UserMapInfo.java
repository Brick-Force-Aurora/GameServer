package de.brickforceaurora.gameserver.legacy.maps;

import java.time.LocalDateTime;

import de.brickforceaurora.gameserver.legacy.util.Texture2D;

public class UserMapInfo {
    public int slot;

    private final String alias;

    private final int brickCount;

    private final LocalDateTime lastModified;

    private final byte premium;

    private Texture2D thumbnail;

    public RegMap regMap; //added to support editing of existing maps

    public UserMapInfo(final int _slot, final String _alias, final int _brickCount, final LocalDateTime _lastModified,
        final byte _premium) {
        slot = _slot;
        alias = _alias;
        brickCount = _brickCount;
        lastModified = _lastModified;
        premium = _premium;
    }
}
