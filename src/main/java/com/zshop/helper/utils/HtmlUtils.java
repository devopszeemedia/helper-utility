package com.zshop.helper.utils;

import org.jsoup.Jsoup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmlUtils {

	private HtmlUtils(){
		
	}

	public static String html2text(String html) {
		
	    return Jsoup.parse(html).text();
	}
}