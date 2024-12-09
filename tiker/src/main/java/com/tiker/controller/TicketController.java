package com.tiker.controller;

import com.tiker.dto.StartRequestDto;
import com.tiker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for starting/stopping the simulation using a chosen config ID.
 */
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    public TicketService getTicketService() {
        return ticketService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestBody StartRequestDto startRequest) {
        ticketService.startSimulation(startRequest.getConfigId());
        return ResponseEntity.ok("Simulation started for config ID " + startRequest.getConfigId());
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        ticketService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped");
    }
}
