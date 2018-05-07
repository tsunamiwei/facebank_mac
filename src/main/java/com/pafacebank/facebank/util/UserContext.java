package com.pafacebank.facebank.util;

public class UserContext {
    private static final ThreadLocal<UserToken> userTokenThreadLocal = new ThreadLocal<>();

    public static UserToken currentUser(){
        return userTokenThreadLocal.get();
    }

    public static void setCurrentUser(UserToken currentUser){
        if(currentUser!=null){
            userTokenThreadLocal.set(currentUser);
        }
        else {
            userTokenThreadLocal.remove();
        }
    }
}
