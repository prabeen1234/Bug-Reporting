package com.bug.reporting.system.bugreportingsystem.shared;



public class ApiURL {
    public final static String USER_BASE_URL = "/api/user";
    public final static String USER_SIGN_IN = USER_BASE_URL + "/signin";
    public final static String USER_SIGN_UP = USER_BASE_URL + "/signup";
    public final static String CHANGE_PASSWORD = USER_BASE_URL + "change_password";
    public final static String FORGET_PASSWORD = USER_BASE_URL + "forget_password";
    public final static String CODE_GENERATE = USER_BASE_URL + "code_generate";
    public final static String BUG_BASE_URL = "/api";
    public final static String USER_SAVE_BUG="/user/save";
    public final static String ADMIN_GET_BUG="/admin/get";



}
