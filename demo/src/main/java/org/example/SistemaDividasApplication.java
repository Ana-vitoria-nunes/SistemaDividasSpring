package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
//@ComponentScan(basePackages = "org.example")
public class SistemaDividasApplication {
	public static void main(String[] args) {
		SpringApplication.run(SistemaDividasApplication.class, args);
	}

}
