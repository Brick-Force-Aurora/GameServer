package de.brickforceaurora.server.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class Encryption {

    public static final RSAPublicKey PUBLIC;
    public static final RSAPrivateKey PRIVATE;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final char[] CHARS;

    static {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024, SECURE_RANDOM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Couldn't initialize key gen", e);
        }
        KeyPair pair = generator.generateKeyPair();
        PUBLIC = (RSAPublicKey) pair.getPublic();
        PRIVATE = (RSAPrivateKey) pair.getPrivate();
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

    public static PublicKey createPublicKey(BigInteger modulus, BigInteger exponent) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus, exponent));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Couldn't create public key", e);
        }
    }

    public static byte[] encryptString(String string, PublicKey key) {
        try {
            return encrypt(string.getBytes(StandardCharsets.UTF_8), key);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Couldn't encrypt string", e.getCause());
        }
    }

    public static String decryptString(byte[] bytes, PrivateKey key) {
        try {
            return new String(decrypt(bytes, key), StandardCharsets.UTF_8);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Couldn't decrypt string", e.getCause());
        }
    }

    public static byte[] encrypt(byte[] bytes, PublicKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException("Couldn't encrypt buffer", e);
        }
    }

    public static byte[] decrypt(byte[] bytes, PrivateKey key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(bytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException("Couldn't decrypt buffer", e);
        }
    }

}
