package com.banyan.FullLoadRequest;

import java.io.IOException;

import org.pmw.tinylog.Configurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FullLoadRequestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullLoadRequestApplication.class, args);

		try {
			Configurator.fromResource("application.properties").activate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
