package com.zshop.helper.utils.request;

import lombok.Data;

@Data
public class PageRequest {

	private Integer pageNo = 0;
	private Integer pageSize = 10;
	private boolean isAllRecordsFlag = false;
}

