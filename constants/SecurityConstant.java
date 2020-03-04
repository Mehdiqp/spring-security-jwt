package com.company.security.jwt.constants;

public class SecurityConstant {


    //register address
    public static final String AUTH_LOGIN_URL = "/auth/login";

    public static final String ROLE_CLIMS = "rol";


    //1hour for uncheck remember me
    public static final long EXPIRATION = 60L * 60L;

    //7day for check remember me
    public static final long EXPAIRTION_REMEMBER = 60 * 60 * 24 * 7L;

    //key
    public static final String JWT_KEY_SECRET = "testKey";

    //default
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    public SecurityConstant() {
    }
}
