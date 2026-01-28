package de.brickforceaurora.gameserver.legacy.protocol;

import java.util.Arrays;

public final class Msg4Recv {

    public enum Status {
        COMPLETE,
        INCOMPLETE,
        INVALID,
        OVERFLOW
    }

    private byte[] buffer;
    private int io;
    public MsgHdr hdr;

    public Msg4Recv() {
        io = 0;
        hdr = new MsgHdr();
        buffer = new byte[4092];
    }

    public Msg4Recv(final byte[] src) {
        io = 0;
        hdr = new MsgHdr();
        buffer = Arrays.copyOf(src, src.length);
    }

    private void expandBuffer() {
        buffer = Arrays.copyOf(buffer, buffer.length * 2);
    }

    public void append(final byte[] data) {
        if (io + data.length > buffer.length) {
            expandBuffer();
        }
        System.arraycopy(data, 0, buffer, io, data.length);
        io += data.length;
    }

    public Status getStatus(final byte recvKey) {
        if (io >= buffer.length) {
            expandBuffer();
        }

        if (io < MsgHdr.SIZE) {
            return Status.INCOMPLETE;
        }

        hdr.fromArray(buffer);

        if (io < MsgHdr.SIZE + hdr.size) {
            return Status.INCOMPLETE;
        }

        if ((recvKey & 0xFF) == 0xFF) {
            byte crc = 0;
            for (int i = 0; i < hdr.size; i++) {
                crc ^= buffer[MsgHdr.SIZE + i];
            }
            if (crc != hdr.crc) {
                return Status.INVALID;
            }
        }

        return Status.COMPLETE;
    }

    public int getId() {
        return hdr.id;
    }

    public MsgBody flush() {
        final MsgBody body = new MsgBody(buffer, MsgHdr.SIZE, (int) hdr.size);

        io -= MsgHdr.SIZE + hdr.size;

        if (io > 0) {
            System.arraycopy(buffer, MsgHdr.SIZE + (int) hdr.size, buffer, 0, io);
        }

        return body;
    }
}
