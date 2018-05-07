package com.pafacebank.facebank.controller;

import com.pafacebank.facebank.dto.BusinessInit;
import com.pafacebank.facebank.dto.BusinessInitResult;
import com.pafacebank.facebank.dto.IdCard;
import com.pafacebank.facebank.dto.SwipedAcct;
import com.pafacebank.facebank.exception.AuthenticationException;
import com.pafacebank.facebank.service.TicketService;
import com.pafacebank.facebank.service.TokenService;
import com.pafacebank.facebank.util.BusinessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/business/authentication")
public class BusinessAuthenticationController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/init")
    public BusinessInitResult init(@RequestBody BusinessInit businessInit) {
        String businessJnlNo = "123456";
        BusinessToken.BusinessInfo businessInfo = BusinessToken.BusinessInfo.builder().businessType(businessInit.getBusiessType()).businessJnlNo(businessJnlNo).build();
        BusinessToken businessToken = BusinessToken.builder().businessInfo(businessInfo).build();
        tokenService.issue(businessToken);
        return BusinessInitResult.builder().businessTokenId(businessToken.getTokenId()).businessJnlNo(businessJnlNo).businessType(businessInit.getBusiessType()).build();
    }

    @PostMapping("/clientCertificate/idCard")
    public void idCard(@RequestHeader("BusinessTokenId") String businessTokenId, @RequestBody IdCard idCard) {
        BusinessToken businessToken = tokenService.get(businessTokenId, BusinessToken.class);
        if (businessToken.isAuthenticated()) {
            return;
        }
        if (businessToken.getClientAcct() != null) {
            throw new AuthenticationException("已完成账号识别，无法重复识别证件");
        }
        businessToken.setClientCertificate(BusinessToken.ClientCertificate.builder().idType("1").idNo(idCard.getIdNo()).name(idCard.getName()).build());
        businessToken.setClientInfo(BusinessToken.ClientInfo.builder().build());
        tokenService.refresh(businessToken);
    }

    @PostMapping("/clientAcct/swiped")
    public void swipe(@RequestHeader("BusinessTokenId") String businessTokenId, @RequestBody SwipedAcct swipedAcct) {
        BusinessToken businessToken = tokenService.get(businessTokenId, BusinessToken.class);
        if (businessToken.isAuthenticated()) {
            return;
        }
        //get client no of acct
        String clientNoOfAcct = "";

        if (businessToken.getClientCertificate() != null) {
            if (businessToken.getClientInfo() == null) {
                throw new AuthenticationException("证件信息与账号信息不匹配");
            }
            if (!clientNoOfAcct.equals(businessToken.getClientInfo().getClientNo())) {
                throw new AuthenticationException("证件信息与账号信息不一致");
            }
        } else {
            businessToken.setClientInfo(BusinessToken.ClientInfo.builder().build());
        }
        businessToken.setClientAcct(BusinessToken.ClientAcct.builder().cardOrAcctNo(swipedAcct.getCardOrAcctNo()).build());
        tokenService.refresh(businessToken);
    }

    @PostMapping("finish")
    public void finish(@RequestHeader("BusinessTokenId") String businessTokenId) {
        BusinessToken businessToken = tokenService.get(businessTokenId, BusinessToken.class);
        businessToken.setAuthenticated(true);
        tokenService.refresh(businessToken);
    }

}
