package com.zshop.helper.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.zshop.helper.utils.request.FormDataBuilder;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Component
@Slf4j
public class FileUploadServiceUtils {

	FileUploadServiceUtils() {

	}

	public static String formDataPost(FormDataBuilder obj) throws IOException {

		String originalFilename = getFormattedFileName(obj.getData().get("imageName"));
		OkHttpClient client = new OkHttpClient();
		File file = saveFileToTmp(obj);
		RequestBody reqBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		builder.addFormDataPart("filename", originalFilename, reqBody);
		builder.addFormDataPart("file", originalFilename, reqBody);
		for (Map.Entry<String, String> data : obj.getData().entrySet()) {
			builder.addFormDataPart(data.getKey(), data.getValue());
		}
		Request request = new Request.Builder().url(obj.getUrl()).method("POST", builder.build()).build();
		String fileUploadResponseStr = client.newCall(request).execute().body().string();
		Files.delete(file.toPath());
		return fileUploadResponseStr;
	}

	private static File saveFileToTmp(FormDataBuilder obj) throws IOException {
		String path = tempFilePath(obj);
		File file = new File(path);

		if (obj.getFile() != null) {
			obj.getFile().transferTo(file);
		} else if (obj.getInputStream() != null) {
			FileUtils.copyInputStreamToFile(obj.getInputStream(), file);
		}

		return file;
	}

	private static String tempFilePath(FormDataBuilder obj) {

		String originalFilename = getFileName(obj);

		return new StringBuilder(System.getProperty("java.io.tmpdir")).append("/").append(originalFilename).toString();

	}

	private static String getFileName(FormDataBuilder obj) {
		return obj.getFile() == null ? new StringBuilder().append(obj.getData().get("imageName")).toString()
				: obj.getFile().getOriginalFilename();
	}

	public static String getFormattedFileName(String fileName) {
		return fileName.replace(" ", "_");
	}

	public static String formDataPostV2(FormDataBuilder obj) throws IOException {

		String originalFilename = getFormattedFileName(obj.getData().get("imageName"));
		OkHttpClient client = new OkHttpClient();
		File file = saveFileToTmp(obj);
		RequestBody reqBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);

		okhttp3.MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
		builder.addFormDataPart("filename", originalFilename, reqBody);
		builder.addFormDataPart("file", originalFilename, reqBody);
		for (Map.Entry<String, String> data : obj.getData().entrySet()) {
			if (!StringUtils.isEmpty(data.getValue())) {
				builder.addFormDataPart(data.getKey(), data.getValue());
			}
		}
		Request request = new Request.Builder().url(obj.getUrl()).method("POST", builder.build()).build();
		// Files.delete(file.toPath());
		return client.newCall(request).execute().body().string();

	}

}
