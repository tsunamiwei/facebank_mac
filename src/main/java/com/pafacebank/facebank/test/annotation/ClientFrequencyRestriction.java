package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.ClientFrequency;

public @interface ClientFrequencyRestriction {
    boolean restricted() default true;

    ClientFrequency value() default ClientFrequency.REGULAR;
}