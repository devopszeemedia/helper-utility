package com.zshop.helper.utils.messaging.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestedAttribute {

	ScoreType scoreType;
	Double scoreThreshold;
	Boolean spanAnnotations;

}
