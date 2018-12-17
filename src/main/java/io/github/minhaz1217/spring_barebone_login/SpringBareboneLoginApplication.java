package io.github.minhaz1217.spring_barebone_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpringBareboneLoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBareboneLoginApplication.class, args);
	}
}

