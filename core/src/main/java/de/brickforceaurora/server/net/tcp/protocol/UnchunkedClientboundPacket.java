package de.brickforceaurora.server.net.tcp.protocol;

import java.io.IOException;

final class UnchunkedClientboundPacket implements IClientboundPacket {

    private final IClientboundPacket delegate;

    public UnchunkedClientboundPacket(IClientboundPacket delegate) {
        this.delegate = delegate;
    }

    @Override
    public int packetId() {
        return delegate.packetId();
    }

    @Override
    public void write(PacketBuf buffer) throws IOException {
        delegate.write(buffer);
    }
    
    @Override
    public IClientboundPacket unchunked() {
        return this;
    }

}
