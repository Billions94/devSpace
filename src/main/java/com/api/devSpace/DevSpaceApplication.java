package com.api.devSpace;

import com.api.devSpace.config.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class DevSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevSpaceApplication.class, args);
	}

}
