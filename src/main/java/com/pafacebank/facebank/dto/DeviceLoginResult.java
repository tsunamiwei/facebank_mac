package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceLoginResult {
    private String deviceId;
    private String workMode;
    private String deviceJwt;
}
