package com.pafacebank.facebank.test.controller;


import com.pafacebank.facebank.test.annotation.BusinessInit;
import com.pafacebank.facebank.test.annotation.BusinessProcess;
import com.pafacebank.facebank.test.datadict.BusinessTypeEnum;

@BusinessProcess(businessType = BusinessTypeEnum.AcctClose, path = "/api/v1/acct_close")
public class AcctCloseController {

    @BusinessInit
    public String init() {
        return "hello, world~";
    }

}
