package com.pafacebank.facebank.service.impl;

import com.pafacebank.facebank.service.CryptoService;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

@Service
public class CryptoServiceImpl implements CryptoService {
    @Override
    public String rsaSign(String plainText) {
        return null;
    }

    @Override
    public boolean rsaVerify(String plainText, String signature) {
        return true;
    }

    @Override
    public String rsaEnrypt(String plainText) {
        return null;
    }

    @Override
    public String rsaDecrypt(String cipherText) {
        return null;
    }

    @Override
    public KeyPair rsaKeyPairGenerate() throws Exception {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("BC");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
