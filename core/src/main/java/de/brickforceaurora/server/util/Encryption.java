package de.brickforceaurora.server.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class Encryption {

    public static final KeyPair KEYS;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final char[] CHARS;

    static {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Couldn't initialize key gen", e);
        }
        KEYS = generator.generateKeyPair();
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        CHARS = new char[str.length() * 2 + 10];
        System.arraycopy(str.toCharArray(), 0, CHARS, 0, str.length());
        System.arraycopy(str.toLowerCase().toCharArray(), 0, CHARS, str.length(), str.length());
        System.arraycopy("0123456789".toCharArray(), 0, CHARS, str.length() * 2, 10);
    }

    public static String generateSecret(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(CHARS[SECURE_RANDOM.nextInt(CHARS.length)]);
        }
        return builder.toString();
    }

    public static byte[] encrypt(String string, PublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(string.getBytes(StandardCharsets.UTF_16LE));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
            | InvalidKeyException e) {
            throw new IllegalStateException("Couldn't encrypt", e);
        }
    }

    public static String decrypt(byte[] bytes, PrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_16LE);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
            | InvalidKeyException e) {
            throw new IllegalStateException("Couldn't decrypt", e);
        }
    }

    public static PacketBuf decryptBuffer(byte[] bytes, PrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new PacketBuf(cipher.doFinal(bytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException
            | InvalidKeyException e) {
            throw new IllegalStateException("Couldn't decrypt", e);
        }
    }

}
