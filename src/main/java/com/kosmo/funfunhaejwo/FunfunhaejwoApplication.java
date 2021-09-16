package com.kosmo.funfunhaejwo;

import com.kosmo.funfunhaejwo.jpa.repository.MemberRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FunfunhaejwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunfunhaejwoApplication.class, args);
	}


}
