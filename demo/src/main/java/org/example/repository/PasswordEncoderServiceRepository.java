package org.example.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface PasswordEncoderServiceRepository {
    String encodePassword(String password, BCryptPasswordEncoder encoder);

}

