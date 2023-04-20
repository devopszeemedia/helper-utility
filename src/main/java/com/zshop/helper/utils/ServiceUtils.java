package com.zshop.helper.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.zshop.helper.exception.business.BusinessException;
import com.zshop.helper.utils.enums.CommunicationTypeEnum;
import com.zshop.helper.utils.enums.HelperEnum;
import com.zshop.helper.utils.request.SmsRequest;
import com.zshop.helper.utils.request.WhatsappRequest;
import com.zshop.helper.utils.response.ErrorResponse;
import com.zshop.helper.utils.response.Response;
import com.zshop.helper.utils.response.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServiceUtils {


	private static final String JSON_APPLICATION_TYPE = "application/json";

	private static final String CONTENT_TYPE = "Content-type";

	private static final String CLIENT_PROTOCOL_EXC = "CLIENT_PROTOCOL_EXC";

	private static final String UTF_8 = "UTF-8";

	private static final String IO_EXC = "IO_EXC";

	public static String post(final String url) throws BusinessException, IOException {
		log.info("In http post request body {} , url {}", url);
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpPost httpPost = new HttpPost(url);
		String responseStr = null;
		try {
			httpPost.setHeader(HelperEnum.ACCEPT.getSource(), JSON_APPLICATION_TYPE);
			final CloseableHttpResponse response = client.execute(httpPost);
			if (response != null) {
				final HttpEntity httpEntity = response.getEntity();
				responseStr = EntityUtils.toString(httpEntity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("Client Protocol Exception occurred in ServiceUtils.post:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IO Exception occurred in ServiceUtils.post:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}
	}

	public static String post(final String url, final String reqBody) throws BusinessException, IOException {
		log.info("In http post request body {} , url {}", reqBody, url);
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpPost httpPost = new HttpPost(url);
		String responseStr = null;
		try {
			final StringEntity entity = new StringEntity(reqBody, UTF_8);
			httpPost.setEntity(entity);

			httpPost.setHeader(HelperEnum.ACCEPT.getSource(), JSON_APPLICATION_TYPE);
			httpPost.setHeader(CONTENT_TYPE, JSON_APPLICATION_TYPE);

			final CloseableHttpResponse response = client.execute(httpPost);

			if (response != null) {
				final HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.post:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.post:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

	public static String post(final String url, final String reqBody, final String tokenValue)
			throws BusinessException, IOException {
		log.info("In http post  with token request body {} , url {}", reqBody, url);
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpPost httpPost = new HttpPost(url);
		String responseStr = null;
		try {
			final StringEntity entity = new StringEntity(reqBody, UTF_8);

			String token = new StringBuilder(HelperEnum.BEARER.getSource()).append(tokenValue).toString();

			httpPost.setEntity(entity);

			httpPost.setHeader(HelperEnum.ACCEPT.getSource(), JSON_APPLICATION_TYPE);
			httpPost.setHeader(CONTENT_TYPE, JSON_APPLICATION_TYPE);
			httpPost.addHeader(HelperEnum.AUTHORIZATION.getSource(), token);

			final CloseableHttpResponse response = client.execute(httpPost);

			if (response != null) {
				final HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.post:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.post:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

	public static String put(final String url, final String reqBody) throws BusinessException, IOException {
		log.info("In http put request body {}, url {}", reqBody, url);
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpPut httpPut = new HttpPut(url);
		String responseStr = null;
		try {
			final StringEntity entity = new StringEntity(reqBody, UTF_8);
			httpPut.setEntity(entity);
			httpPut.setHeader(HelperEnum.ACCEPT.getSource(), JSON_APPLICATION_TYPE);
			httpPut.setHeader(CONTENT_TYPE, JSON_APPLICATION_TYPE);

			final CloseableHttpResponse response = client.execute(httpPut);

			if (response != null) {
				final HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);

			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.put:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.put:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

	public static String get(String url) throws IOException, BusinessException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		log.info("Get url {}", url);
		try {
			CloseableHttpResponse response = client.execute(httpGet);
			String responseStr = null;
			if (response != null) {
				HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.get:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.get:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

	public static String get(String url, String tokenValue) throws IOException, BusinessException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		log.info("Get url {}", url);
		try {
			String token = new StringBuilder(HelperEnum.BEARER.getSource()).append(tokenValue).toString();
			httpGet.addHeader(HelperEnum.AUTHORIZATION.getSource(), token);
			CloseableHttpResponse response = client.execute(httpGet);
			String responseStr = null;
			if (response != null) {
				HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.get:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.get:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

	public static String sendSms(String mobileNumber, Map<String, String> dataValues, String templateFileName,
			String url) throws BusinessException, IOException {

		SmsRequest request = new SmsRequest();

		request.setCommunicationType(CommunicationTypeEnum.SMS);
		request.setMobileNo(mobileNumber);
		request.setTemplateFileName(templateFileName);
		request.setDataValues(dataValues);
		return ServiceUtils.post(url, GsonUtils.getJson(request));

	}

	public static String sendSmsV1(String mobileNumber, Map<String, String> dataValues, String templateFileName,
								 String url, String smsHeader) throws BusinessException, IOException {

		SmsRequest request = new SmsRequest();

		request.setCommunicationType(CommunicationTypeEnum.SMS);
		request.setMobileNo(mobileNumber);
		request.setTemplateFileName(templateFileName);
		request.setDataValues(dataValues);
		request.setSmsHeader(smsHeader);
		return ServiceUtils.post(url, GsonUtils.getJson(request));

	}
	
	public static String sendWhatsapp(String mobileNumber, Map<String, String> dataValues, String templateFileName,
			String url) throws BusinessException, IOException {
		WhatsappRequest request = new WhatsappRequest();
		
		request.setRecipientContactNo("91"+mobileNumber);
		request.setRecipientName("User");
		request.setTemplateName(templateFileName);
		request.setTemplateBody(dataValues);
		
		return ServiceUtils.post(url, GsonUtils.getJson(request));
	}

	public static boolean isSuccessResponse(String jsonResponse) throws BusinessException {

		BaseResponse validateBaseResponse = GsonUtils.getObject(jsonResponse, BaseResponse.class);

		if (validateBaseResponse.getCode().equals(StatusCodeEnum.SUCCESS_CODE.getCode())) {
			return true;
		} else {
			throw new BusinessException(validateBaseResponse.getCode(), validateBaseResponse.getMessage(),
					validateBaseResponse.getData());
		}

	}

	public static <T> T getDataPart(String response, Class<T> clazz) {

		return GsonUtils.getObject(GsonUtils.getJson(GsonUtils.getObject(response, BaseResponse.class).getData()),
				clazz);
	}

	public static <T> T getDataPart(String response, Class<T> clazz, Gson gson) {

		return gson.fromJson(gson.toJson(gson.fromJson(response, BaseResponse.class).getData()), clazz);
	}

	public static <T> T getDataPart(String response, Gson gson, Type type) {

		return gson.fromJson(gson.toJson(gson.fromJson(response, BaseResponse.class).getData()), type);
	}

	/**
	 * Method to support automatic exception handling for old response.
	 * 
	 * @param response
	 * @throws BusinessException
	 */
	public static void handleExceptionBackwardCompatibility(String response) throws BusinessException {

		Response object = GsonUtils.getObject(response, Response.class);

		if (StatusEnum.Failure.equals(object.getStatus())) {

			String errorMessage = GsonUtils.getJson(object.getMessage());
			if (!CollectionUtils.isEmpty(object.getErrors())) {
				errorMessage = object.getErrors().stream().map(ErrorResponse::getErrorMessage)
						.collect(Collectors.joining(","));
			}

			throw new BusinessException(StatusCodeEnum.PARAMS_MISSING_CODE.getCode(), errorMessage, null);
		}
	}

	public static URL buildURI(String url, Map<String, String> params)
			throws URISyntaxException, MalformedURLException {
		// build url with parameters.
		URIBuilder b = new URIBuilder(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			b.addParameter(entry.getKey(), entry.getValue());
		}
		return b.build().toURL();
	}

	public static URL buildURI(String url, List<String> values, String key)
			throws URISyntaxException, MalformedURLException {
		// build url with parameters.
		URIBuilder b = new URIBuilder(url);
		b.addParameters(buildParamList(key, values));
		return b.build().toURL();
	}

	private static List<NameValuePair> buildParamList(String key, List<String> values) {
		List<NameValuePair> output = new ArrayList<>();
		for (String string : values) {
			output.add(new BasicNameValuePair(key, string));
		}
		return output;
	}
	
	public static String postWithSession(final String url, final String reqBody, final String session)
			throws BusinessException, IOException {
		log.info("In http post  with token request body {} , url {}", reqBody, url);
		final CloseableHttpClient client = HttpClients.createDefault();
		final HttpPost httpPost = new HttpPost(url);
		String responseStr = null;
		try {
			final StringEntity entity = new StringEntity(reqBody, UTF_8);

			httpPost.setEntity(entity);

			httpPost.setHeader(HelperEnum.ACCEPT.getSource(), JSON_APPLICATION_TYPE);
			httpPost.setHeader(CONTENT_TYPE, JSON_APPLICATION_TYPE);
			httpPost.addHeader(HelperEnum.SESSION.getSource(), session);

			final CloseableHttpResponse response = client.execute(httpPost);

			if (response != null) {
				final HttpEntity httpentity = response.getEntity();
				responseStr = EntityUtils.toString(httpentity, UTF_8);
			}
			return responseStr;
		} catch (final ClientProtocolException ex) {
			log.error("ClientProtocolException occurred in ServiceUtils.post:", ex);
			throw new BusinessException(CLIENT_PROTOCOL_EXC, ex.getMessage(), ex);
		} catch (final IOException ex) {
			log.error("IOException occurred in ServiceUtils.post:{}", ex);
			throw new BusinessException(IO_EXC, ex.getMessage(), ex);
		} finally {
			client.close();
		}

	}

}
