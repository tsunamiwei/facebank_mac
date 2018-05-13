package com.pafacebank.facebank.test.datadict;

import com.pafacebank.facebank.test.annotation.*;
import lombok.extern.apachecommons.CommonsLog;

import static com.pafacebank.facebank.test.datadict.AcctCertificate.CREDIT_CARD;
import static com.pafacebank.facebank.test.datadict.AcctCertificate.PASSBOOK;
import static com.pafacebank.facebank.test.datadict.BusinessCategory.CREDIT;

@CommonsLog
public enum BusinessTypeEnum {
    @BusinessType(clientFrequency = @ClientFrequencyRestriction(restricted = false))
    AcctOpen,

    @BusinessType(acctCertificate = @AcctCertificateRequirement(PASSBOOK))
    AcctClose,

    @BusinessType(idCertificate = @IdCertificateRequirement(required = false), acctCertificate = @AcctCertificateRequirement({CREDIT_CARD, PASSBOOK}), authorization = @AuthorizationRequirement(required = false))
    AcctTransfer,

    @BusinessType(businessCategory = CREDIT, acctCertificate = @AcctCertificateRequirement(CREDIT_CARD))
    CreditCardActivate,
    //========
    ;

    private BusinessType businessType;

    BusinessTypeEnum() {
        try {
            businessType = this.getClass().getField(this.name()).getAnnotation(BusinessType.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
