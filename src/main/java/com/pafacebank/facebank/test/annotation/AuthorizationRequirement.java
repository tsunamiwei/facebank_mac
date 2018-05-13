package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.AuthorizationMode;

public @interface AuthorizationRequirement {
    boolean required() default true;

    AuthorizationMode[] value() default {AuthorizationMode.VIDEO, AuthorizationMode.ARTIFICIAL};
}