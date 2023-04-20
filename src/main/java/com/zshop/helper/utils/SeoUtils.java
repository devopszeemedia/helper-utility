package com.zshop.helper.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zshop.helper.exception.business.BusinessException;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SeoUtils {

	@Autowired
	private RedisService redisCache;

	/**
	 * This method is make and validate seo url.
	 * 
	 * @param showTypeEnum
	 * @return
	 * @throws BusinessException
	 */
	public String getSeoUrl(SeoBuilder seoBuilder, String seoOldUrl) throws BusinessException {
		if (StringUtils.isEmpty(seoOldUrl)) {
			seoBuilder.setSeoName(removeInvalidCharsInSeoName(seoBuilder.getSeoName()));
			return formSeoUrl(seoBuilder);
		} else {
			return seoOldUrl;
		}
	}
	
	public static String replaceSpecialCharacters(String str) {
		String[] strAry = new String[32];
		String[] repStrAry = new String[strAry.length];
		repStrAry[0] = " ";
		repStrAry[1] = "-";
		repStrAry[2] = "-";
		repStrAry[3] = "";
		repStrAry[4] = "";
		repStrAry[5] = "";
		repStrAry[6] = "";
		repStrAry[7] = "";
		repStrAry[8] = "-";
		repStrAry[9] = "/";
		repStrAry[10] = "-";
		repStrAry[11] = "-";
		repStrAry[12] = "-";
		repStrAry[13] = "-";
		repStrAry[14] = "-";
		repStrAry[15] = "-";
		repStrAry[16] = "-";
		repStrAry[17] = "-";
		repStrAry[18] = "-";
		repStrAry[19] = "-";
		repStrAry[20] = "-";
		repStrAry[21] = "-";
		repStrAry[22] = "-";
		repStrAry[23] = "-";
		repStrAry[24] = "-";
		repStrAry[25] = "-";
		repStrAry[26] = "-";
		repStrAry[27] = "-";
		repStrAry[28] = "-";
		repStrAry[29] = "-";
		repStrAry[30] = "-";
		repStrAry[31] = "-";

		strAry[0] = "\\.";
		strAry[1] = " ";
		strAry[2] = "\\&";
		strAry[3] = "\"";
		strAry[4] = "'";
		strAry[5] = "\\+";
		strAry[6] = "\\(";
		strAry[7] = "\\)";
		strAry[8] = "#";
		strAry[9] = "/";
		strAry[10] = "\\,";
		strAry[11] = "\\!";
		strAry[12] = "\\@";
		strAry[13] = "\\$";
		strAry[14] = "\\%";
		strAry[15] = "\\^";
		strAry[16] = "\\*";
		strAry[17] = "\\|";
		strAry[18] = "\\<";
		strAry[19] = "\\>";
		strAry[20] = "\\?";
		strAry[21] = "\\{";
		strAry[22] = "\\}";
		strAry[23] = "\\[";
		strAry[24] = "\\]";
		strAry[25] = "--";
		strAry[26] = "---";
		strAry[27] = "----";
		strAry[28] = "-----";
		strAry[29] = "------";
		strAry[30] = "--";
		strAry[31] = "---";

		if (str != null && str.trim().length() > 0) {
			for (int i = 0; i < strAry.length; i++) {
				str = str.replaceAll(strAry[i], repStrAry[i]);
			}
		}
		return str;
	}

	/**
	 * Method is used to remove invalid part of show name in Seo Url.
	 * 
	 * @param seoName
	 * @return
	 * @throws BusinessException
	 */
	private String removeInvalidCharsInSeoName(String seoName) throws BusinessException {

		String seoUrl = "";
		String name = seoName;
		name = name
				.replaceAll(HelperBusinessConstantsEnumUtils.SeoConstants.SPECIAL_CHARACTERS_KEYWORD.getSource(),
						StringUtils.EMPTY)
				.replaceAll(HelperBusinessConstantsEnumUtils.SeoConstants.EXTRA_CHAR_REGEX_EXP.getSource(),
						HelperBusinessConstantsEnumUtils.SeoConstants.HYPHEN_KEYWORD.getSource());

		SeoCacheResponse response = GsonUtils.getObject(
				redisCache.getDataFromCache(HelperBusinessConstantsEnumUtils.SeoConstants.SEO_REDIS_KEY.getSource()),
				SeoCacheResponse.class);
		if (response != null) {

			List<String> invalidKeywords = Arrays.asList(StringUtils.splitPreserveAllTokens(response.getWords(), ","));
			String[] showNameArray = name
					.split(HelperBusinessConstantsEnumUtils.SeoConstants.HYPHEN_KEYWORD.getSource());
			for (int i = 0; i < showNameArray.length; i++) {
				if (invalidKeywords.contains(showNameArray[i].toLowerCase())) {
					name = name.replaceAll(showNameArray[i], StringUtils.EMPTY);
				}
			}
			seoUrl = name
					.replaceAll(HelperBusinessConstantsEnumUtils.SeoConstants.HYPEN_REGEX_EXP.getSource(),
							HelperBusinessConstantsEnumUtils.SeoConstants.HYPHEN_KEYWORD.getSource())
					.replaceAll(HelperBusinessConstantsEnumUtils.SeoConstants.HYPEN_FIRST_LAST_OCCURENCE_REGEX_EXP
							.getSource(), StringUtils.EMPTY);

		}
		return seoUrl;
	}

	/**
	 * This method is used to form seo url.
	 * 
	 * @param seoId
	 * @param seoName
	 * @param seoPrefix
	 * @return seo url
	 */
	public static String formSeoUrl(SeoBuilder seoBuilder) {
		StringBuilder seoUrlBuilder = new StringBuilder(seoBuilder.getSeoPath());

		if (StringUtils.isNotEmpty(seoBuilder.getSeoPrefix())) {
			seoUrlBuilder.append(seoBuilder.getSeoPrefix())
					.append(HelperBusinessConstantsEnumUtils.SeoConstants.URL_DELIMITER.getSource());
		}
		seoUrlBuilder.append(seoBuilder.getSeoName().toLowerCase())
				.append(HelperBusinessConstantsEnumUtils.SeoConstants.URL_DELIMITER.getSource());

		if (seoBuilder.getSeoId() != null) {
			seoUrlBuilder.append(seoBuilder.getSeoId())
					.append(HelperBusinessConstantsEnumUtils.SeoConstants.URL_DELIMITER.getSource());
		}
		if (StringUtils.isNotEmpty(seoBuilder.getSeoSuffix())) {
			seoUrlBuilder.append(seoBuilder.getSeoSuffix())
					.append(HelperBusinessConstantsEnumUtils.SeoConstants.URL_DELIMITER.getSource());
			;
		}
		log.info("Seo url {}", seoUrlBuilder.toString());
		return seoUrlBuilder.toString();
	}

	@Data
	@Builder
	public static class SeoBuilder {

		private Long seoId;
		private String seoPath;
		private String seoPrefix;
		private String seoSuffix;
		private String seoName;
	}

	@Data
	@Builder
	public static class SeoCacheResponse {

		private String words;
	}
}
