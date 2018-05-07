package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceLogin {
    private DeviceSignature deviceSignature;
    private String userJwt;
}
