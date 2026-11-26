package de.brickforceaurora.server.net.tcp.login;

import me.lauriichan.laylib.logger.util.StringUtil;

public interface ILoginResult {
    
    record Deny(String disconnectMessage) implements ILoginResult {}
    
    record Accepted() implements ILoginResult {}
    
    ILoginResult ACCEPTED = new Accepted();

    static ILoginResult deny(String disconnectMessage) {
        return new Deny(disconnectMessage);
    }

    static ILoginResult deny(String disconnectMessage, Object... placeholders) {
        return new Deny(StringUtil.format(disconnectMessage, placeholders));
    }

}
