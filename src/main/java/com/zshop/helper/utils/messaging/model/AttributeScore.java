package com.zshop.helper.utils.messaging.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttributeScore {

	Score summaryScore;
	List<SpanScore> spanScores;

}
