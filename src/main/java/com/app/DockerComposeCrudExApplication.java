package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sound.midi.Soundbank;

@SpringBootApplication
@EnableCaching
public class DockerComposeCrudExApplication {


	public static void main(String[] args) {
		System.out.println("this is docker images latest update");
		SpringApplication.run(DockerComposeCrudExApplication.class, args);
	}

}
