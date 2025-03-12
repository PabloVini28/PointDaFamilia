package com.pointdafamilia.pointdafamilia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.pointdafamilia.pointdafamilia.configurations.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class PointdafamiliaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointdafamiliaApplication.class, args);
	}

}
