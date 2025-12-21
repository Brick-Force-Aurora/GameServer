package de.brickforceaurora.server.protocol;

import java.util.Arrays;

public final class Msg4Recv {

    public enum Status { COMPLETE, INCOMPLETE, INVALID, OVERFLOW }
    private static final int MAX_PACKET_SIZE = 8192;

    private byte[] buffer;
    private int io;
    public MsgHdr hdr = new MsgHdr();

    public Msg4Recv() {
        this.io = 0;
        this.hdr = new MsgHdr();
        this.buffer = new byte[4092];
    }

    public Msg4Recv(byte[] src) {
        buffer = Arrays.copyOf(src, src.length);
        io = src.length;
    }

    public void append(byte[] data) {
        if (buffer.length + data.length > MAX_PACKET_SIZE) {
            throw new IllegalStateException("Packet overflow");
        }
        buffer = data;
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
