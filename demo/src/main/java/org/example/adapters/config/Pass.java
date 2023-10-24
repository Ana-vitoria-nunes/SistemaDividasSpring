package org.example.adapters.config;
import org.mindrot.jbcrypt.BCrypt;
public class Pass {
    public static String hashCrypto(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

}

