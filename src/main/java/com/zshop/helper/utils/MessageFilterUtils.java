package com.zshop.helper.utils;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zshop.helper.utils.messaging.model.AnalyzeCommentRequest;
import com.zshop.helper.utils.messaging.model.AnalyzeCommentResponse;
import com.zshop.helper.utils.messaging.model.AttributeType;
import com.zshop.helper.utils.messaging.model.ContentType;
import com.zshop.helper.utils.messaging.model.Entry;
import com.zshop.helper.utils.messaging.model.WebPurifyProfenityConfig;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageFilterUtils {

	@Autowired
	private WebPurifyProfenityConfig webPurifyProfenityConfig;

	public AnalyzeCommentResponse analyze(String message) {
		AnalyzeCommentResponse analyze = new AnalyzeCommentResponse();
		if (BooleanUtils.isTrue(webPurifyProfenityConfig.getIsApiRequired())) {
			log.info("Checking for profanity in string message : {}", message);
			analyze =  analyze(getAnalyzeCommentRequest(message), webPurifyProfenityConfig.getApiUrl());	
		}
		return analyze;
	}

	private AnalyzeCommentResponse analyze(AnalyzeCommentRequest request, String analyzeEndpoint) {
		HttpResponse<AnalyzeCommentResponse> response = Unirest.post(analyzeEndpoint)
				.queryString("key", webPurifyProfenityConfig.getApiKey()).body(request)
				.asObject(AnalyzeCommentResponse.class);

		if (!response.isSuccess()) {
			return null;
		}
		return response.getBody();
	}

	private AnalyzeCommentRequest getAnalyzeCommentRequest(String comment) {
		
		return new AnalyzeCommentRequest.Builder().addRequestedAttribute(AttributeType.PROFANITY, null)
				.comment(Entry.builder().type(ContentType.PLAIN_TEXT).text(comment).build())
				.doNotStore(webPurifyProfenityConfig.isDataStoreFlag()).build();
	}

}
