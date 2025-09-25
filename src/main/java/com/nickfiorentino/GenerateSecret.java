package com.nickfiorentino;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateSecret {
    public static void main(String[] args) {
        byte[] randomBytes = new byte[64];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        String jwtSecretBase64 = Base64.getEncoder().encodeToString(randomBytes);
        System.out.println("Base64 JWT Secret: " + jwtSecretBase64);
    }
}