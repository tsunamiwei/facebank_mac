package com.pafacebank.facebank.service.impl;

import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String issue(long expire) {
        return null;
    }

    @Override
    public Token issue(Token token, long expire) {
        return null;
    }

    @Override
    public void refresh(String tokenId, long expire) {

    }

    @Override
    public void refresh(Token token, long expire) {

    }

    @Override
    public String issue() {
        return null;
    }

    @Override
    public Token issue(Token token) {
        return null;
    }

    @Override
    public void refresh(String tokenId) {

    }

    @Override
    public void refresh(Token token) {

    }

    @Override
    public boolean exists(String tokenId) {
        return false;
    }

    @Override
    public boolean exists(Token token) {
        return false;
    }

    @Override
    public <T> T get(String tokenId, Class<T> tokenClass) {
        return null;
    }
}
