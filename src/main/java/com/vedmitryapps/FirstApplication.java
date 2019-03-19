package com.vedmitryapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*@SpringBootApplication(scanBasePackages={
		"com.vedmitryapps.dao", "com.vedmitryapps.repository"})
@EnableJpaRepositories("com.vedmitryapps.repository")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})*/

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
/*@EnableJpaRepositories("com.vedmitryapps.repository")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages={
		"com.vedmitryapps.dao", "com.vedmitryapps.repository"})*/

@SpringBootApplication
@EnableJpaAuditing
public class FirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApplication.class, args);
	}

}
