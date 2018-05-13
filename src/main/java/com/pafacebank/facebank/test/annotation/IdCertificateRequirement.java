package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.IdCertificate;

public @interface IdCertificateRequirement {
    boolean required() default true;

    IdCertificate[] value() default IdCertificate.ID_CARD;
}
