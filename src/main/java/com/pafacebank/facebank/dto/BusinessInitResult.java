package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessInitResult {
    private String businessType;
    private String businessJnlNo;
    private String businessTokenId;
}
