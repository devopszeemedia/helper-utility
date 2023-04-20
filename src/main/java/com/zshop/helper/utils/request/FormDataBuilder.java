package com.zshop.helper.utils.request;

import java.io.InputStream;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormDataBuilder {

	private MultipartFile file;
	
	private InputStream inputStream;

	private String url;
	
	private Map<String, String> data;
}
