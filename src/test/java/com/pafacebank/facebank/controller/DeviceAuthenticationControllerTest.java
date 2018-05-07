package com.pafacebank.facebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pafacebank.facebank.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DeviceAuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Test
    public void login() throws Exception {
        Ticket ticket = om.readValue(mockMvc.perform(get("/api/v1/user/authentication/ticket")).andExpect(jsonPath("$.ticketId", is(notNullValue()))).andReturn().getResponse().getContentAsString(), Ticket.class);
        UserLogin userLogin = UserLogin.builder().ticketId(ticket.getTicketId()).userId("tsunamiwei").password("weihaixiao").build();
        UserJwt userJwt = om.readValue(mockMvc.perform(post("/api/v1/user/authentication/login").contentType("application/json").content(om.writeValueAsString(userLogin))).andReturn().getResponse().getContentAsString(), UserJwt.class);
        System.out.println("userJwt = " + userJwt);

        UserOtp userOtp = UserOtp.builder().otpToken("123456").build();
        userJwt = om.readValue(mockMvc.perform(post("/api/v1/user/authentication/otp/verify").header("UserJwt", userJwt.getUserJwt()).contentType("application/json").content(om.writeValueAsString(userJwt))).andReturn().getResponse().getContentAsString(), UserJwt.class);
        System.out.println("userJwt = " + userJwt);

        DeviceSignup deviceSignup = DeviceSignup.builder().deviceId("aaabbb").build();
        DeviceSignupResult deviceSignupResult = om.readValue(mockMvc.perform(post("/api/v1/device/registration/signup").contentType("application/json").content(om.writeValueAsString(deviceSignup))).andReturn().getResponse().getContentAsString(), DeviceSignupResult.class);
        System.out.println("deviceSignupResult.getRsaPriKey() = " + deviceSignupResult.getRsaPriKey());

        DeviceSetup deviceSetup = DeviceSetup.builder().deviceId("aaabbb").workMode("M").deviceAlias("haha").build();
        mockMvc.perform(post("/api/v1/device/management/setup").contentType("application/json").content(om.writeValueAsString(deviceSetup))).andExpect(status().isOk());

        DeviceLogin deviceLogin = DeviceLogin.builder().deviceSignature(DeviceSignature.builder().deviceId("aaabbb").ticketId("111").signature("222").build()).userJwt(userJwt.getUserJwt()).build();
        DeviceLoginResult deviceLoginResult = om.readValue(mockMvc.perform(post("/api/v1/device/authentication/login").contentType("application/json").content(om.writeValueAsString(deviceLogin))).andReturn().getResponse().getContentAsString(), DeviceLoginResult.class);
        System.out.println("deviceLoginResult.getWorkMode() = " + deviceLoginResult.getWorkMode());

    }
}