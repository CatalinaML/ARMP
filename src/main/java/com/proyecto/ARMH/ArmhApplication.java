package com.proyecto.ARMH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.proyecto.modelos")
public class ArmhApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArmhApplication.class, args);
	}

}
