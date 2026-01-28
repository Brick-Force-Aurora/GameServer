package de.brickforceaurora.gameserver.legacy.maps;

import de.brickforceaurora.gameserver.legacy.script.BfScript;

public class BrickInst {

    public int seq;

    public byte template;

    public byte posX;
    public byte posY;
    public byte posZ;

    /**
     * C# ushort → Java int (0–65535)
     */
    public int code;

    public byte rot;

    public BfScript brickForceScript;

    public int pathcnt;

    public BrickInst(
            int seq,
            byte template,
            byte x,
            byte y,
            byte z,
            int code,
            byte rot
    ) {
        this.seq = seq;
        this.template = template;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.rot = rot;
        this.code = code & 0xFFFF; // ensure ushort semantics
        this.brickForceScript = null;
        this.pathcnt = 0;
    }

    public void updateScript(
            String alias,
            boolean enableOnAwake,
            boolean visibleOnAwake,
            String commands
    ) {
        this.brickForceScript = new BfScript(
                alias,
                enableOnAwake,
                visibleOnAwake,
                commands
        );
    }
}
