package com.zshop.helper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "url.path")
@EnableConfigurationProperties
public class UrlPathsConfig {

	private String seoPath;

	private String urlPrefix;

	private String urlSuffix;
}