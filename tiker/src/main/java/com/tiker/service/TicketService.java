package com.tiker.service;

import com.tiker.dto.StartRequestDto;

public interface TicketService {
    void startSimulation(StartRequestDto startRequestDto);
    void stopSimulation();
}
