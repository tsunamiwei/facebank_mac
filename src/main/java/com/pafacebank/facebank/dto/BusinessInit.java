package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessInit {
    private String busiessType;
    private String deviceJwt;
}
