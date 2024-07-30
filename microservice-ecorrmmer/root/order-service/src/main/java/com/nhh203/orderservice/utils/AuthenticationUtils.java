package com.nhh203.orderservice.utils;

import com.nhh203.orderservice.exception.SignInRequiredException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
public class AuthenticationUtils {
    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new SignInRequiredException(Constants.ERROR_CODE.SIGN_IN_REQUIRED);
        }
        JwtAuthenticationToken contextHolder = (JwtAuthenticationToken) authentication;
        return contextHolder.getToken().getSubject();
    }
}
