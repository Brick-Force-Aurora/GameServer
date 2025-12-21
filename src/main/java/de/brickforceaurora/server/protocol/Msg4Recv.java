package de.brickforceaurora.server.protocol;

import java.util.Arrays;

public final class Msg4Recv {

    public enum Status { COMPLETE, INCOMPLETE, INVALID }

    private byte[] buffer;
    private int io;
    public final MsgHdr hdr = new MsgHdr();

    public Msg4Recv(byte[] src) {
        buffer = Arrays.copyOf(src, src.length);
        io = src.length;
    }

    public Status getStatus(byte recvKey) {
        if (io < MsgHdr.SIZE)
            return Status.INCOMPLETE;

        hdr.fromArray(buffer);

        if (io < MsgHdr.SIZE + hdr.size)
            return Status.INCOMPLETE;

        if ((recvKey & 0xFF) == 0xFF) {
            byte crc = 0;
            for (int i = 0; i < hdr.size; i++) {
                crc ^= buffer[MsgHdr.SIZE + i];
            }
            if (crc != hdr.crc)
                return Status.INVALID;
        }

        return Status.COMPLETE;
    }

    public int getId() {
        return hdr.id;
    }

    public MsgBody flush() {
        MsgBody body = new MsgBody(buffer, MsgHdr.SIZE, (int) hdr.size);
        io -= MsgHdr.SIZE + hdr.size;
        return body;
    }
}
