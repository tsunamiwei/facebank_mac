package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceSetup {
    private String deviceId;
    private String workMode;
    private String deviceAlias;
}
