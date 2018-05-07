package com.pafacebank.facebank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMngtEntity {
    @Id
    private String deviceId;
    private String branchId;

    private String signupUserId;
    private String rsaPubKey;

    private String setupUserId;
    private String workMode;
    private String virtualUserId;
    private String deviceAlias;

}
