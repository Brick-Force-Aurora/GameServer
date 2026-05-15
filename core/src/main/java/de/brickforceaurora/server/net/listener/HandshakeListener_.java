package de.brickforceaurora.server.net.listener;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.ILoginHandler;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraHandshakeChallengePacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraRequestLoginPacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakeCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakeInitializePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraLoginPacket;
import de.brickforceaurora.server.util.Encryption;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class HandshakeListener_ implements INetListener {
    
    public static final String REQUEST_LOGIN_TIME = "login.requested_time";
    public static final long LOGIN_TIMEOUT_TIME = TimeUnit.MINUTES.toNanos(60);

    @PacketHandler
    public void onHandshake(final NetContext<ServerboundAuroraHandshakeInitializePacket> context) {
        context.manager().keepClientAlive(context.client());
        context.client().setupSecret(context.packet().clientKey());
        context.client().send(new ClientboundAuroraHandshakeChallengePacket().serverKey(Encryption.PUBLIC)
            .encryptedChallenge(Encryption.encryptString(context.client().encryptionChallenge(), context.client().encryptionKey())));
    }

    @PacketHandler
    public void onHandshakeChallenge(final NetContext<ServerboundAuroraHandshakeCompletePacket> context) {
        context.client().validateChallenge(Encryption.decryptString(context.packet().encryptedChallenge(), Encryption.PRIVATE));
        if (!context.client().wasChallanged()) {
            context.client().send(new ClientboundAuroraDisconnectPacket().message("Handshake challenge failed"));
            context.client().disconnect();
            return;
        }
        context.manager().keepClientAlive(context.client());
        context.client().attrSet(REQUEST_LOGIN_TIME, context.manager().netTime());
        context.client().send(new ClientboundAuroraRequestLoginPacket().supportedTypes(context.manager().snowFrame().app().loginHandler().supportedTypes()));
    }

    @PacketHandler
    public void onLogin(final NetContext<ServerboundAuroraLoginPacket> context) {
        if (!context.client().wasChallanged()) {
            context.client().send(new ClientboundAuroraDisconnectPacket().message("No successful handshake"));
            context.client().disconnect();
            return;
        }
        // So we don't prevent keep alive anymore
        context.client().attrUnset(REQUEST_LOGIN_TIME);
        ILoginHandler handler = context.manager().snowFrame().app().loginHandler();
        if (!handler.supportedTypes().is(context.packet().loginType())) {
            context.client()
                .send(new ClientboundAuroraDisconnectPacket().message("Unsupported login type: " + context.packet().loginType()));
            context.client().disconnect();
            return;
        }
        handler.login(context.client(), context.packet().version(), context.packet().loginType(), context.packet().username(),
            context.packet().tokenOrPassword());
        if (!context.client().isLoggedIn()) {
            context.client().send(new ClientboundAuroraDisconnectPacket().message("Invalid login information"));
            context.client().disconnect();
            return;
        }
        context.manager().keepClientAlive(context.client());
        context.client().send(ProtocolExtension.PACKET_LOGGED_IN);
        context.manager().signalManager().call(new NetSignal.ClientLoggedIn(context.manager(), context.client()));
    }

}
