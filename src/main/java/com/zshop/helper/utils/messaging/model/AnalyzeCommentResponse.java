package com.zshop.helper.utils.messaging.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalyzeCommentResponse {

	Map<AttributeType, AttributeScore> attributeScores;
	List<String> languages;
	String clientToken;
	boolean badWordInElastic;
	
	public AttributeScore getAttributeScore(AttributeType type) {
		return attributeScores.get(type);
	}

}
