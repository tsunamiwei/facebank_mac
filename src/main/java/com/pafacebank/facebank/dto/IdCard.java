package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdCard {
    private String idType;
    private String idNo;
    private String name;
    private String sex;
    private String birthDate;
    private String idIssuer;
    private String idIssDate;
    private String idExpiryDate;
    private String idHeadPhoto;
}
