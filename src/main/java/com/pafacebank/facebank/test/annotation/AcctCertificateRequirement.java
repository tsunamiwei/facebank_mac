package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.AcctCertificate;

public @interface AcctCertificateRequirement {
    boolean required() default true;

    AcctCertificate[] value() default {AcctCertificate.DEBIT_CARD};
}