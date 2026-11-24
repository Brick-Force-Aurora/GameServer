package de.brickforceaurora.server.net.tcp.login;

public interface ILoginResult {
    
    record Deny(String disconnectMessage) implements ILoginResult {}
    
    record Accepted() implements ILoginResult {}
    
    ILoginResult ACCEPTED = new Accepted();

    static ILoginResult deny(String disconnectMessage) {
        return new Deny(disconnectMessage);
    }

}
