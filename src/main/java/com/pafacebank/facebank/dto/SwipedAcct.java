package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SwipedAcct {
    private String cardOrAcctNo;
    private String track2;
    private String track3;
    private String password;
    private String passbookNo;
}
