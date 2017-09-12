package com.example.dellc.qq.utils;



/**
 * Created by dellc on 2017/9/12.
 */

public class StringUtils {
    public static final String TAG = "StringUtils";

    public static final String REGEX_USER_NAME = "^[a-zA-Z]\\w{2,19}$";
    public static final String REGEX_PASSWORD = "^[0-9]{3,20}$";

    public static boolean isValidUserName(String userName){
        return userName.matches(REGEX_USER_NAME);
    }

    public static boolean isValidPassword(String pwd){
        return pwd.matches(REGEX_PASSWORD);
    }
}
