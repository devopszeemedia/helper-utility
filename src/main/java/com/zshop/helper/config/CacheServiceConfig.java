package com.zshop.helper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "cache-service")
@EnableConfigurationProperties
@Data
public class CacheServiceConfig {

	private String baseUrl;
	private String setDataUrl;
	private String getDataUrl ;
	private String mGetUrl;
	
}
