package com.pafacebank.facebank.service;

import com.pafacebank.facebank.util.Token;

public interface TokenService {

    String issue(long expire);

    Token issue(Token token, long expire);

    void refresh(String tokenId, long expire);

    void refresh(Token token, long expire);


    String issue();

    Token issue(Token token);

    void refresh(String tokenId);

    void refresh(Token token);

    boolean exists(String tokenId);

    boolean exists(Token token);

    <T> T get(String tokenId, Class<T> tokenClass);
}