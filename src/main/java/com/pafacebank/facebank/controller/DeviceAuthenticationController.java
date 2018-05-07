package com.pafacebank.facebank.controller;

import com.pafacebank.facebank.dto.DeviceLoginResult;
import com.pafacebank.facebank.dto.DeviceSignature;
import com.pafacebank.facebank.dto.DeviceWorkMode;
import com.pafacebank.facebank.dto.Ticket;
import com.pafacebank.facebank.entity.DeviceMngtEntity;
import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.repository.DeviceMngtReposotry;
import com.pafacebank.facebank.service.CryptoService;
import com.pafacebank.facebank.service.TicketService;
import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.DeviceToken;
import com.pafacebank.facebank.util.JwtUtils;
import com.pafacebank.facebank.util.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device/authentication")
public class DeviceAuthenticationController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private DeviceMngtReposotry deviceMngtReposotry;
    @Autowired
    private TokenService tokenService;

    @GetMapping("/ticket")
    public Ticket ticket() {
        return Ticket.builder().ticketId(ticketService.issue(30 * 60)).build();
    }

    @PostMapping("/workmode")
    public DeviceWorkMode workMode(@RequestBody DeviceSignature deviceSignature) {
        deviceSignatureVerify(deviceSignature);
        DeviceMngtEntity deviceMngtEntity = deviceMngtReposotry.findOne(deviceSignature.getDeviceId());
        return DeviceWorkMode.builder().workMode(deviceMngtEntity.getWorkMode()).build();
    }

    @PostMapping("/login")
    public DeviceLoginResult login(@RequestHeader(value = "UserJwt", required = false) String userJwt, @RequestBody DeviceSignature deviceSignature) {
        deviceSignatureVerify(deviceSignature);
        DeviceMngtEntity deviceMngtEntity = deviceMngtReposotry.findOne(deviceSignature.getDeviceId());
        if (deviceMngtEntity.getWorkMode() == null) {
            throw new AuthenticationException("设备尚未设置工作模式，请在工作台进行设置");
        }
        DeviceToken deviceToken = null;
        if ("M".equals(deviceMngtEntity.getWorkMode())) {
            if (StringUtils.isEmpty(userJwt)) {
                throw new AuthenticationException("设备工作模式为移动式，需要先进行柜员登陆");
            }
            UserToken userToken = JwtUtils.convertToUser(userJwt);
//            if (!"M".equals(userToken.getRoleId())) {
//                throw new AuthenticationException("登陆柜员需移动式FB角色");
//            }
//            if (!deviceMngtEntity.getBranchId().equals(userToken.getBranchId())) {
//                throw new AuthenticationException("登陆柜员所属网点与设备所属网点不一致");
//            }
            deviceToken = DeviceToken.builder().deviceId(deviceSignature.getDeviceId()).branchId(deviceMngtEntity.getBranchId()).workMode(deviceMngtEntity.getWorkMode()).deviceAlias(deviceMngtEntity.getDeviceAlias()).userId(userToken.getUserId()).build();
            tokenService.issue(deviceToken);
        } else {
            deviceToken = DeviceToken.builder().deviceId(deviceSignature.getDeviceId()).branchId(deviceMngtEntity.getBranchId()).workMode(deviceMngtEntity.getWorkMode()).deviceAlias(deviceMngtEntity.getDeviceAlias()).userId(deviceMngtEntity.getVirtualUserId()).build();
            tokenService.issue(deviceToken);
        }
        return DeviceLoginResult.builder().deviceId(deviceSignature.getDeviceId()).workMode(deviceMngtEntity.getWorkMode()).deviceJwt(JwtUtils.convertToJwt(deviceToken)).build();
    }

    private void deviceSignatureVerify(@RequestBody DeviceSignature deviceSignature) {
        if (!ticketService.verify(deviceSignature.getTicketId())) {
            throw new AuthenticationException("ticket校验失败");
        }
        if (!cryptoService.rsaVerify(deviceSignature.getTicketId(), deviceSignature.getSignature())) {
            throw new AuthenticationException("签名校验失败");
        }
    }
}
