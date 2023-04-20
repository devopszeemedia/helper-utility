package com.zshop.helper.utils;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

public class GsonUtils {

    private static Gson gson = new Gson();
    private static Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

    private GsonUtils() {

    }

    /**
     * @param obj
     * @return json string of this object.
     */
    public static String getJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * @param obj
     * @return json string of this object (Pretty json).
     */
    public static String getPrettyJson(Object obj) {
        return prettyGson.toJson(obj);
    }

    /**
     * Convert given json string to object.
     *
     * @param json
     * @param obj
     * @return an object by populating properties with json data.
     */
    public static <T> T getObject(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T getType(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static JsonObject createJsonObject(Map<String, String> data) {

        JsonObject jsonObject = null;
        if (!MapUtils.isEmpty(data)) {
            jsonObject = new JsonObject();
            for (Entry<String, String> entry : data.entrySet()) {
                jsonObject.addProperty(entry.getKey(), entry.getValue());
            }
        }

        return jsonObject;
    }

    public static Gson registerGsonBuilder() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context) ->
                        LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                .create();
        return gson;
    }

    /**
     * used to get a register GsonBuilder with ZoneDateFormat s =>
     * yyyy-MM-dd'T'HH:mm:ssZ
     *
     * @return
     */
    public static Gson registerGsonBuilderZoneDateFormat() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context) ->
                        LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                .create();
        return gson;
    }

    public static Gson registerGsonBuilderSpecificDateFormat(String dateFormat) {
        gson = new GsonBuilder().setDateFormat(dateFormat)
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context) ->
                        LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                .create();
        return gson;
    }

    public static Gson registerGsonBuilderSpecificDateFormatWithDateAsLong(String dateFormat) {
        gson = new GsonBuilder().setDateFormat(dateFormat)
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, type, context) ->
                        new Date(json.getAsJsonPrimitive().getAsLong()))
                .create();
        return gson;
    }

    /**
     * @param obj
     * @return json string of this object.
     */
    public static String getJsonWithoutHtmlEscape(Object obj) {
        gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

}
