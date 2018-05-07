package com.pafacebank.facebank.controller;

import com.pafacebank.facebank.dto.Ticket;
import com.pafacebank.facebank.dto.UserJwt;
import com.pafacebank.facebank.dto.UserLogin;
import com.pafacebank.facebank.dto.UserOtp;
import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.service.OtpService;
import com.pafacebank.facebank.service.TicketService;
import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.JwtUtils;
import com.pafacebank.facebank.util.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/authentication")
public class UserAuthenticationController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OtpService otpService;

    @GetMapping("/ticket")
    public Ticket ticket() {
        return Ticket.builder().ticketId(ticketService.issue(30 * 60)).build();
    }

    @PostMapping("/login")
    public UserJwt login(@RequestBody UserLogin userLogin) {
        if (!ticketService.verify(userLogin.getTicketId())) {
            throw new AuthenticationException("ticket校验失败");
        }
        if (!authenticate(userLogin)) {
            throw new AuthenticationException("用户名或密码错误");
        }

        userLogin.getUserId();
        String deviceBranchId = "";
        //userBranchId =  deviceBranchId;

        UserToken userToken = UserToken.builder().userId(userLogin.getUserId()).passwordAuthenticated(true).build();
        tokenService.issue(userToken);
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().setHeader(HttpHeaders.AUTHORIZATION, "Fb-User " + JwtUtils.convertToJwt(currentUser));
        return UserJwt.builder().userJwt(JwtUtils.convertToJwt(userToken)).build();
    }

    private boolean authenticate(@RequestBody UserLogin userLogin) {
        return "tsunamiwei".equals(userLogin.getUserId()) && "weihaixiao".equals(userLogin.getPassword());
    }

    @PostMapping("/otp/send")
    public void otpSend(@RequestHeader("UserJwt") String userJwt) {
        UserToken userToken = JwtUtils.convertToUser(userJwt);
        if (!userToken.isPasswordAuthenticated()) {
            throw new AuthenticationException("尚未验证登陆密码，请先登陆");
        }
    }

    @PostMapping("/otp/verify")
    public UserJwt otpVerify(@RequestHeader("UserJwt") String userJwt, @RequestBody UserOtp userOtp) {
        UserToken userToken = JwtUtils.convertToUser(userJwt);
        if (!userToken.isPasswordAuthenticated()) {
            throw new AuthenticationException("尚未验证登陆密码，请先登陆");
        }
        if (!otpService.verify(userToken.getMobile(), userOtp.getOtpToken())) {
            throw new AuthenticationException("短信验证码错误，请重新输入");
        }
        userToken.setOtpAuthenticated(true);
//        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse().setHeader(HttpHeaders.AUTHORIZATION, "Fb-User " + JwtUtils.convertToJwt(userto));
        return UserJwt.builder().userJwt(JwtUtils.convertToJwt(userToken)).build();
    }
}
