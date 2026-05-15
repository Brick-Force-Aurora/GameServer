package de.brickforceaurora.server.net.listener;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.login.ILoginHandler;
import de.brickforceaurora.server.net.login.IOAuthLoginHandler;
import de.brickforceaurora.server.net.login.LoginType;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraHandshakeChallengePacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraLoginDetailsPacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraRequestLoginPacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakeCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHandshakeInitializePacket;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraLoginPacket;
import de.brickforceaurora.server.util.Encryption;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class HandshakeListener_ implements INetListener {

    public static final String ATTR_REQUEST_LOGIN_TIME = "login.requested_time";
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
            context.client().disconnect("Handshake challenge failed");
            return;
        }
        context.manager().keepClientAlive(context.client());
        context.client().attrSet(ATTR_REQUEST_LOGIN_TIME, context.manager().netTime());
        context.client().send(
            new ClientboundAuroraRequestLoginPacket().supportedTypes(context.manager().snowFrame().app().loginHandler().supportedTypes()));
    }

    @PacketHandler
    public void onLogin(final NetContext<ServerboundAuroraLoginPacket> context) {
        if (!context.client().wasChallanged()) {
            context.client().disconnect("No successful handshake");
            return;
        }
        ILoginHandler handler = context.manager().snowFrame().app().loginHandler();
        LoginType loginType = context.packet().loginType();
        if (handler.supportedTypes().value() == 0) {
            // This if is for debug logins, its not a full login type but instead if no login types are considered supported we default back to debug login
            // Which **should** mean no password required but still considered password login
            loginType = LoginType.PASSWORD;
            // We have to set it to PASSWORD as DEBUG login is usually null, also we don't support any other login method
            // So the client **should** only send null
        } else if (loginType == null || !handler.supportedTypes().is(loginType)) {
            context.client().disconnect("Unsupported login type: " + (loginType == null ? "DEBUG" : loginType));
            return;
        }
        switch (loginType) {
        case OAUTH -> {
            if (!(handler instanceof IOAuthLoginHandler oauthHandler)) {
                context.client().disconnect("Server Error: Login handler doesn't support OAuth even tho it claims to support it");
                return;
            }
            String verificationUri = oauthHandler.generateOAuthRequest(context.client(), context.packet().version());
            if (verificationUri == null) {
                context.client().disconnect("Server Error: Couldn't create OAuth request");
                return;
            }
            context.client().send(new ClientboundAuroraLoginDetailsPacket());
            // Reset login time for the users sake
            context.client().attrSet(ATTR_REQUEST_LOGIN_TIME, context.manager().netTime());
        }
        default -> {
            // So we don't prevent keep alive anymore
            context.client().attrUnset(ATTR_REQUEST_LOGIN_TIME);
            handler.login(context.client(), context.packet().version(), context.packet().loginType(), context.packet().username(),
                context.packet().loginData());
            if (!context.client().isLoggedIn()) {
                context.client().disconnect("Invalid login information");
                return;
            }
            context.manager().keepClientAlive(context.client());
            context.client().send(ProtocolExtension.PACKET_LOGGED_IN);
            context.manager().signalManager().call(new NetSignal.ClientLoggedIn(context.manager(), context.client()));
        }
        }
    }

}
