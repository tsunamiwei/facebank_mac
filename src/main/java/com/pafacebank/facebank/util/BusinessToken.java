package com.pafacebank.facebank.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BusinessToken extends Token {
    private boolean authenticated;
    private BusinessInfo businessInfo;
    private ClientInfo clientInfo;
    private ClientCertificate clientCertificate;
    private ClientAcct clientAcct;

    @Data
    @Builder
    public static class BusinessInfo {
        private String businessType;
        private String businessJnlNo;
        private List<String> additionalImages;
        private Object initResult;
    }

    @Data
    @Builder
    public static class ClientInfo {
        private String clientNo;
        private String name;
        private String idType;
        private String idNo;
        private String sex;
        private String birthDate;
        private String expiryDate;
    }

    @Data
    @Builder
    public static class ClientCertificate {
        private String idType;
        private String idNo;
        private String name;
        private String sex;
        private String birthDate;
        private String idIssuer;
        private String idIssDate;
        private String idExpiryDate;
        private String idHeadPhoto;
        private String idFrontImage;
        private String idBackImage;
    }

    @Data
    @Builder
    public static class ClientAcct {
        private String cardOrAcctNo;
        private String passwordVerified;
        private String acctLevel;// I,II,III
        private boolean entitative;
        private boolean degraded;
    }
}
