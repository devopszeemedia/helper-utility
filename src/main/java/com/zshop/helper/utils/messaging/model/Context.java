package com.zshop.helper.utils.messaging.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Context {

	List<Entry> entries;
}
