package com.example.parkee.service;

import com.example.parkee.model.entity.CheckInTicket;
import com.example.parkee.model.entity.CheckOutTicket;
import com.example.parkee.model.request.CreateCheckInTicketRequest;
import com.example.parkee.model.request.CreateCheckOutTicketRequest;
import com.example.parkee.model.request.GetCheckoutTicketDetailRequest;

public interface TicketService {

  CheckInTicket createCheckInTicket(CreateCheckInTicketRequest request);

  CheckInTicket getCheckInTicketDetail(GetCheckoutTicketDetailRequest request);

  CheckOutTicket createCheckOutTicket(CreateCheckOutTicketRequest request);
}
