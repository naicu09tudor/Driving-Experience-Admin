package com.drivingexperience.admin.constant;

public class JWTUtil {

    public static final long EXPIRE_ACCESS_TOKEN = 5*60*1000; // 5 minutes

    public static final long EXPIRE_REFRESH_TOKEN = 120*60*1000; // 2 hours

    public static final String BEARER_PREFIX= "Bearer ";

    public static final String ISSUER = "springBootApp";

    public static final String SECRET ="myPrivateSecret";

    public static final String AUTH_HEADER = "Authorization";
}
