package com.easybudget.user.auth.service.utils;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface AuthTokenUtil {

    String getUsernameFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails userDetails);

    Boolean canTokenBeRefreshed(String token);

    String refreshToken(String token);

    Boolean validateToken(String token, UserDetails userDetails);
}
