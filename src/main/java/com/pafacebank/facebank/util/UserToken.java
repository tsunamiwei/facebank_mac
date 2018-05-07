package com.pafacebank.facebank.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken extends Token {
    private String userId;
    private String name;
    private String mobile;
    private String branchId;
    private String deviceId;
    private boolean passwordAuthenticated;
    private boolean otpAuthenticated;
    private boolean faceAuthenticated;

    public boolean isFullyAuthenticated() {
        return passwordAuthenticated && (otpAuthenticated || faceAuthenticated);
    }
}
