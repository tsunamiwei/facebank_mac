package com.pafacebank.facebank.service.impl;

import com.pafacebank.facebank.service.OtpService;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {
    @Override
    public void send(String mobile) {

    }

    @Override
    public boolean verify(String mobile, String otp) {
        return true;
    }
}
