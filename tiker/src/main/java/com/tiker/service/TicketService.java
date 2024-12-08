package com.tiker.service;

import com.tiker.dto.RequestConfigDto;

/**
 * Service interface for managing the ticket simulation.
 */
public interface TicketService {
    void startSimulation(RequestConfigDto configDto);
    void stopSimulation();
}
