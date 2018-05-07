package com.pafacebank.facebank.service;

public interface TicketService {
    String issue(long expire);

    boolean verify(String ticketId);
}