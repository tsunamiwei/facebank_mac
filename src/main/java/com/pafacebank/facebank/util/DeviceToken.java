package com.pafacebank.facebank.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceToken extends Token {
    private String deviceId;
    private String branchId;
    private String workMode;
    private String deviceAlias;
    private String userId;
}
