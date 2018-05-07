package com.pafacebank.facebank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AcctCloseSubmit {
    private AcctCloseTransferForm transferForm;
}
