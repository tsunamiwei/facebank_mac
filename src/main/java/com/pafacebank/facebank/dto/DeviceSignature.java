package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceSignature {
    private String deviceId;
    private String ticketId;
    private String signature;
}
