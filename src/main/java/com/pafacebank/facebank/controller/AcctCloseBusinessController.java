package com.pafacebank.facebank.controller;

import com.pafacebank.facebank.BusinessController;
import com.pafacebank.facebank.dto.AcctCloseInitResult;
import com.pafacebank.facebank.dto.AcctCloseSubmit;
import com.pafacebank.facebank.dto.AcctCloseTransferForm;
import com.pafacebank.facebank.util.BusinessAuthentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@BusinessController(business = "", certificate = true, acct = true)
@RestController
@RequestMapping("/api/vi/businesses/AcctClose/")
public class AcctCloseBusinessController {

    @RequestMapping("/init")
    public AcctCloseInitResult init(BusinessAuthentication authentication) {
        String cardOrAcctNo = authentication.getCardOrAcctNo();
        return null;
    }

    @RequestMapping("/form/transfer")
    public void tranferForm(BusinessAuthentication authentication, @RequestBody AcctCloseTransferForm transferForm) {
    }

    @RequestMapping("/submit")
    public void submit(BusinessAuthentication authentication, @RequestBody AcctCloseSubmit acctCloseSubmit) {

    }
}
