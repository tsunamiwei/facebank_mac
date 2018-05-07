package com.pafacebank.facebank.dto;

import com.pafacebank.facebank.util.Form;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcctCloseTransferForm extends Form {
    private String payeeAcctNo;
    private String payeeAcctName;
}
