package de.brickforceaurora.server.net.tcp.login;

import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public interface ILoginDetails {

    LoginType type();

    void write(PacketBuf buffer);

    public static record OAuth(String verificationUri) implements ILoginDetails {
        @Override
        public LoginType type() {
            return LoginType.OAUTH;
        }

        @Override
        public void write(final PacketBuf buffer) {
            buffer.writeString(verificationUri);
        }
    }

}
