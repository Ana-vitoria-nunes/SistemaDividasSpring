package org.example.config;
import org.mindrot.jbcrypt.BCrypt;
public class Pass {
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static String hashCVV(Integer cvv) {
        return BCrypt.hashpw(String.valueOf(cvv), BCrypt.gensalt());
    }
    public static String hashNumeroCartao(String number) {
        return BCrypt.hashpw(number, BCrypt.gensalt());
    }

}

