package de.brickforceaurora.gameserver.maps;

import de.brickforceaurora.gameserver.util.Texture2D;

import java.time.LocalDateTime;

public class UserMapInfo {
    public int slot;

    private String alias;

    private int brickCount;

    private LocalDateTime lastModified;

    private byte premium;

    private Texture2D thumbnail;

    public RegMap regMap; //added to support editing of existing maps

    public UserMapInfo(int _slot, String _alias, int _brickCount, LocalDateTime _lastModified, byte _premium)
    {
        slot = _slot;
        alias = _alias;
        brickCount = _brickCount;
        lastModified = _lastModified;
        premium = _premium;
    }
}
