package com.example.user.generator;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class IdGenerator {

    private static final String SALT = "450d0b0db2bcf4adde5032eca1a7c416e560cf44";

    public String generateSHA1Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] result = md.digest((input + SALT).getBytes());

            // Convert byte array to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating SHA-1 hash", e);
        }
    }
}
