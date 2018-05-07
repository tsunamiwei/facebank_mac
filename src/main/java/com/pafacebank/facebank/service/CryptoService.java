package com.pafacebank.facebank.service;

import java.security.KeyPair;

public interface CryptoService {
    String rsaSign(String plainText);

    boolean rsaVerify(String plainText, String signature);

    String rsaEnrypt(String plainText);

    String rsaDecrypt(String cipherText);

    KeyPair rsaKeyPairGenerate() throws Exception;
}