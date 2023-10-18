package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


//(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication// -> indica que não precisa de autorização para executar a classe por enquanto
public class SistemaDividasApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaDividasApplication.class, args);
	}

}
