package ru.ilya.lab2_spring.model.api;

public class ApiConstants {
    //URLs
    public static final String API_V1_PATH = "/api/v1";

    public static final String BRAND_PATH = "/brand";
    public static final String MODEL_PATH = "/model";
    public static final String OFFER_PATH = "/offer";
    public static final String USER_PATH = "/user";
    public static final String USER_ROLE_PATH = "/role";
    public static final String ADMIN_PATH = "/admin";
    public static final String CREATE_PATH = "/create";
    public static final String DELETE_PATH = "/delete";
    public static final String ALL_PATH = "/all";
    public static final String EDIT_PATH = "/edit";
    public static final String SPLIT_PATH = "/";
    public static final String REDIRECT_PATH = "redirect:";
    public static final String AUTH_PATH = "/auth";
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";
    public static final String REGISTER_PATH = "/registration";


    //Services APIs
    public static final String BRAND_API_V1_PATH = API_V1_PATH + BRAND_PATH;
    public static final String MODEL_API_V1_PATH = API_V1_PATH + MODEL_PATH;
    public static final String OFFER_API_V1_PATH = API_V1_PATH + OFFER_PATH;
    public static final String USER_API_V1_PATH = API_V1_PATH + USER_PATH;
    public static final String USER_ROLE_API_V1_PATH = API_V1_PATH + USER_ROLE_PATH;

    //Content types
    public static final String JSON_TYPE = "application/json";
    public static final String ALL_TYPE = "*/*";
}
