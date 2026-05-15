package de.brickforceaurora.server.net.login;

public interface ILoginData {
    
    public static final ILoginData NONE = new ILoginData() {};
    
    public static record Password(String password) implements ILoginData {}
    
    public static record Transfer(int originChannel, String token) implements ILoginData {}

}
