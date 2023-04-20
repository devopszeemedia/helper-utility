package com.zshop.helper.utils.messaging.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "webpurify.profenity.api")
@EnableConfigurationProperties
public class WebPurifyProfenityConfig {

	private Boolean isApiRequired = Boolean.FALSE;

	private String apiUrl;

	private String apiKey;

	private String language;

	private boolean dataStoreFlag;

}
