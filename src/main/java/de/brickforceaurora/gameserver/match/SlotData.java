package de.brickforceaurora.gameserver.match;

import de.brickforceaurora.gameserver.net.ClientReference;

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

    public void toggleLock(boolean value)
    {
        if (!isUsed)
            isLocked = value;
    }
}

