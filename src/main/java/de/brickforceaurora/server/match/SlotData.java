package de.brickforceaurora.server.match;

import de.brickforceaurora.server.net.ClientReference;

public final class SlotData
{
    public ClientReference client;
    public boolean isUsed = false;
    public boolean isLocked = false;
    public int slotIndex;
    public boolean isRed = false;

    public SlotData(int _slotIndex)
    {
        slotIndex = _slotIndex;
    }

    public void ToggleLock(boolean value)
    {
        if (!isUsed)
            isLocked = value;
    }
}

