package com.zshop.helper.exception.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({ "classpath:error-mapping-config.properties" })
public class ExceptionUtil {
	
	@Autowired
	private Environment env;
	
	public  String resolveErrorMessage(String errorCode){
		return  env.getProperty(errorCode);
	}

}
