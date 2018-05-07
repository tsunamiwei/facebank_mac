package com.pafacebank.facebank.controller;

import com.pafacebank.facebank.dto.DeviceSetup;
import com.pafacebank.facebank.dto.DeviceSignup;
import com.pafacebank.facebank.dto.DeviceSignupResult;
import com.pafacebank.facebank.entity.DeviceMngtEntity;
import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.repository.DeviceMngtReposotry;
import com.pafacebank.facebank.service.CryptoService;
import com.pafacebank.facebank.util.JwtUtils;
import com.pafacebank.facebank.util.UserToken;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/device/management")
@CommonsLog
public class DeviceManagementController {

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private DeviceMngtReposotry deviceMngtReposotry;

    @PostMapping("/signup")
    public DeviceSignupResult signup(@RequestHeader("UserJwt") String userJwt, @RequestBody DeviceSignup deviceSignup) throws Exception {
        UserToken userToken = JwtUtils.convertToUser(userJwt);
        if (!userToken.isFaceAuthenticated()) {
            throw new AuthenticationException("用户未完成登陆");
        }
//        if (!"A".equals(userToken.getRoleId())) {
//            throw new AuthenticationException("设备注册需登陆用户具有柜台式FB角色");
//        }
//        String branchId = "9998";
//        if (!branchId.equals(userToken.getBranchId())) {
//            throw new AuthenticationException("登陆柜员所属网点与设备所属网点不一致");
//        }

        KeyPair keyPair = cryptoService.rsaKeyPairGenerate();
        String rsaPubKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String rsaPriKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        DeviceMngtEntity deviceMngtEntity = deviceMngtReposotry.findOne(deviceSignup.getDeviceId());

        if (deviceMngtEntity == null) {
            deviceMngtEntity = DeviceMngtEntity.builder().deviceId(deviceSignup.getDeviceId()).branchId("9998").signupUserId(userToken.getUserId()).rsaPubKey(rsaPubKey).build();
        } else {
            deviceMngtEntity.setBranchId("9998");
            deviceMngtEntity.setSignupUserId(userToken.getUserId());
            deviceMngtEntity.setRsaPubKey(rsaPubKey);
        }
        deviceMngtReposotry.save(deviceMngtEntity);
        return DeviceSignupResult.builder().deviceId(deviceSignup.getDeviceId()).rsaPriKey(rsaPriKey).build();
    }

    @PostMapping("/setup")
    public void setup(@RequestHeader("UserJwt") String userJwt, @RequestBody DeviceSetup deviceSetup) throws Exception {
        UserToken userToken = JwtUtils.convertToUser(userJwt);
        if (!userToken.isFaceAuthenticated()) {
            throw new AuthenticationException("用户未完成登陆");
        }
//        if (!"T".equals(userToken.getRoleId())) {
//            throw new AuthenticationException("设备设置需工作台FB角色");
//        }
        DeviceMngtEntity deviceMngtEntity = deviceMngtReposotry.findOne(deviceSetup.getDeviceId());
//        if (!deviceMngtEntity.getBranchId().equals(userToken.getBranchId())) {
//            throw new AuthenticationException("登陆柜员所属网点与设备所属网点不一致");
//        }
        deviceMngtEntity.setSetupUserId(userToken.getUserId());
        deviceMngtEntity.setWorkMode(deviceSetup.getWorkMode());
        deviceMngtEntity.setDeviceAlias(deviceSetup.getDeviceAlias());
        if ("A".equals(deviceSetup.getWorkMode())) {
            deviceMngtEntity.setVirtualUserId("");
        }
        deviceMngtReposotry.save(deviceMngtEntity);
    }

}
