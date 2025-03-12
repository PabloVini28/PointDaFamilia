package com.pointdafamilia.pointdafamilia.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    
    private String uploadDir;

}
