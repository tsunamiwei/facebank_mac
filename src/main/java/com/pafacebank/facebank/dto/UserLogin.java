package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    private String ticketId;
    private String userId;
    private String password;
    private String deviceId;
    private String loginMode;// A, T, M
}
