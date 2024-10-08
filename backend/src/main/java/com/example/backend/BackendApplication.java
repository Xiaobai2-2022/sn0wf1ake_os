package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/**
	 * This Method Enables CORS to communicate with React
	 */
	@Bean
	public WebMvcConfigurer cors_configurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry reg) {
				reg.addMapping("/**")													// Allow all endpoints
                        .allowedOrigins("http://localhost:3000") 						// Allow React app domain
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 		// Allowed HTTP methods
                        .allowedHeaders("*"); 											// Allow all headers
			}
		};
	}

}
