package com.nickfiorentino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);



   /*  Generate a secure random JWT secret and print it in Base64 format

    byte[] randomBytes = new byte[64];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(randomBytes);

    String jwtSecretBase64 = Base64.getEncoder().encodeToString(randomBytes);

        System.out.println("Base64 JWT Secret: " + jwtSecretBase64);
    */



    }
}
