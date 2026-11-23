package de.brickforceaurora.server.net.tcp.login;

public interface ILoginData {

    ILoginData NONE = new ILoginData() {};

    public static record Password(String password) implements ILoginData {}

    public static record Transfer(int originChannel, String token) implements ILoginData {}

}
