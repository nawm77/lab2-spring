package ru.ilya.lab2_spring.model.api;

public class ApiConstants {
    //URLs
    public static final String API_V1_PATH = "/api/v1";

    public static final String BRAND_PATH = "/brand";
    public static final String MODEL_PATH = "/model";

    //Services APIs
    public static final String BRAND_API_V1_PATH = API_V1_PATH + BRAND_PATH;
    public static final String MODEL_API_V1_PATH = API_V1_PATH + MODEL_PATH;

    //Content types
    public static final String JSON_TYPE = "application/json";
    public static final String ALL_TYPE = "*/*";
}
