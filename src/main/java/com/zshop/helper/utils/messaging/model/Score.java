package com.zshop.helper.utils.messaging.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {

	float value;
	ScoreType type;

}
