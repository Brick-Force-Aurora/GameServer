package de.brickforceaurora.server.net.listener;

import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraHandshakePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakeChallengePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraLoginPacket;
import de.brickforceaurora.server.util.Encryption;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class HandshakeListener_ implements INetListener {

    @PacketHandler
    public void onHandshake(final NetContext<ServerboundAuroraHandshakePacket> context) {
        context.manager().keepClientAlive(context.client());
        context.client().setupSecret(context.packet().clientKey());
        context.client().send(new ClientboundAuroraHandshakePacket().serverKey(Encryption.KEYS.getPublic())
            .encryptedChallenge(Encryption.encrypt(context.client().encryptionChallenge(), context.client().encryptionKey())));
    }

    @PacketHandler
    public void onHandshakeChallenge(final NetContext<ServerboundAuroraHandshakeChallengePacket> context) {
        context.client().validateChallenge(Encryption.decrypt(context.packet().encryptedChallenge(), Encryption.KEYS.getPrivate()));
        if (!context.client().wasChallanged()) {
            // TODO: KICK CLIENT
            return;
        }
        context.manager().keepClientAlive(context.client());
        context.client().send(ProtocolExtension.PACKET_HANDSHAKE_CHALLENGE);
    }

    @PacketHandler
    public void onLogin(final NetContext<ServerboundAuroraLoginPacket> context) {
        PacketBuf loginData = Encryption.decryptBuffer(context.packet().encryptedLoginData(), Encryption.KEYS.getPrivate());
        String userName = loginData.readString();
        String passwordHash = loginData.readString();
        context.manager().snowFrame().app().loginHandler().login(context.client(), context.packet().version(), userName, passwordHash);
        if (!context.client().isLoggedIn()) {
            // TODO: KICK CLIENT
            return;
        }
        context.manager().keepClientAlive(context.client());
        context.client().send(ProtocolExtension.PACKET_LOGIN);
        context.manager().signalManager().call(new NetSignal.ClientLoggedIn(context.manager(), context.client()));
    }

}
