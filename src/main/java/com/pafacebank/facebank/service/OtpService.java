package com.pafacebank.facebank.service;

public interface OtpService {

    void send(String mobile);

    boolean verify(String mobile, String otp);
}