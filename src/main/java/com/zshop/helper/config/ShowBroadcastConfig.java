package com.zshop.helper.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "show-broadcast")
@EnableConfigurationProperties
public class ShowBroadcastConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String baseUrl;
	private String syncStatus;
	private String statusUpdate;

}
