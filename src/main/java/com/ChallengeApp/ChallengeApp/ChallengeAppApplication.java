package com.ChallengeApp.ChallengeApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableAutoConfiguration
@EnableWebSecurity
@ComponentScans({@ComponentScan("com/ChallengeApp/ChallengeApp/auth"),
				@ComponentScan("com/ChallengeApp/ChallengeApp/Repositories"),
				})

public class ChallengeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeAppApplication.class, args);
	}

}
