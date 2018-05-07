package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceSignupResult {
    private String deviceId;
    private String rsaPriKey;
}
