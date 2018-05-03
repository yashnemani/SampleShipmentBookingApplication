package com.banyan.FullLoadRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FullLoadRequestApplication {
  /*  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FullLoadRequestApplication.class);
    }*/
	public static void main(String[] args) {
		SpringApplication.run(FullLoadRequestApplication.class, args);
	}
}
