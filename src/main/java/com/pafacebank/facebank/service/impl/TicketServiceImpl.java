package com.pafacebank.facebank.service.impl;

import com.pafacebank.facebank.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public String issue(long expire) {
        return "ticket123456";
    }

    @Override
    public boolean verify(String ticketId) {
        return true;
    }
}
