package com.pafacebank.facebank.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.apachecommons.CommonsLog;

import java.util.HashMap;
import java.util.Map;

@CommonsLog
public class JwtUtils {
    private static final String userJwtKey = "user";
    private static final String deviceJwtKey = "device";


    public static String convertToJwt(UserToken userToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", userToken.getName());
        claims.put("mobile", userToken.getMobile());
        claims.put("branchId", userToken.getBranchId());
        claims.put("passwordAuthenticated", userToken.isPasswordAuthenticated());
        claims.put("otpAuthenticated", userToken.isOtpAuthenticated());
        claims.put("faceAuthenticated", userToken.isFaceAuthenticated());

        String jwt = Jwts.builder().setId(userToken.getTokenId()).setSubject(userToken.getUserId()).addClaims(claims).signWith(SignatureAlgorithm.HS512, userJwtKey).compact();
        return jwt;
    }

    public static String convertToJwt(DeviceToken deviceToken) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("branchId", deviceToken.getBranchId());
        claims.put("workMode", deviceToken.getWorkMode());
        claims.put("deviceAlias", deviceToken.getDeviceAlias());
        claims.put("userId", deviceToken.getUserId());

        String jwt = Jwts.builder().setId(deviceToken.getTokenId()).setSubject(deviceToken.getDeviceId()).addClaims(claims).signWith(SignatureAlgorithm.HS512, deviceJwtKey).compact();
        return jwt;
    }

    public static UserToken convertToUser(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(userJwtKey).parseClaimsJws(jwt).getBody();
        UserToken userToken = UserToken.builder().userId(claims.getSubject()).name((String) claims.get("name")).mobile((String) claims.get("mobile")).branchId((String) claims.get("branchId")).passwordAuthenticated((Boolean) claims.get("passwordAuthenticated")).otpAuthenticated((Boolean) claims.get("otpAuthenticated")).faceAuthenticated((Boolean) claims.get("faceAuthenticated")).build();
        userToken.setTokenId(claims.getId());
        return userToken;
    }

    public static DeviceToken convertToDevice(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(deviceJwtKey).parseClaimsJws(jwt).getBody();
        DeviceToken deviceToken = DeviceToken.builder().deviceId(claims.getSubject()).branchId((String) claims.get("branchId")).workMode((String) claims.get("workMode")).deviceAlias((String) claims.get("deviceAlias")).userId((String) claims.get("userId")).build();
        deviceToken.setTokenId(claims.getId());
        return null;
    }

}
