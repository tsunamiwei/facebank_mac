package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.BusinessCategory;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BusinessType {

    BusinessCategory businessCategory() default BusinessCategory.DEBIT;

    ClientFrequencyRestriction clientFrequency() default @ClientFrequencyRestriction;

    IdCertificateRequirement idCertificate() default @IdCertificateRequirement();

    AcctCertificateRequirement acctCertificate() default @AcctCertificateRequirement();

    AuthorizationRequirement authorization() default @AuthorizationRequirement();
}