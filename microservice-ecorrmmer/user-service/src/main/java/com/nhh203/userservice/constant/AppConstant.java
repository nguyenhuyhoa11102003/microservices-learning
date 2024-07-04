package com.nhh203.userservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppConstant {

    public static final String LOCAL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String ZONED_DATE_TIME_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";
    public static final String INSTANT_FORMAT = "dd-MM-yyyy__HH:mm:ss:SSSSSS";


    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public abstract static class DiscoveredDomainsApi {

        public static final String API_GATEWAY_HOST = "http://localhost:8080";

        public static final String USER_SERVICE_HOST = "http://USER-SERVICE/user-service";
        public static final String USER_SERVICE_API_URL = "http://USER-SERVICE/user-service/api/users";
    }
}
