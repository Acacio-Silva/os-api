package com.OsSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.OsSystem.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Autowired
	private DBService dbService;

	@Bean
	public boolean iniciaDB() {
		if (ddl.equals("create")) {
			dbService.instanciaDB();
		}
		return false;

	}

}
