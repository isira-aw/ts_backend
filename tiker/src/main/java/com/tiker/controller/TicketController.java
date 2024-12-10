package com.tiker.controller;

import com.tiker.dto.StartRequestDto;
import com.tiker.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestBody StartRequestDto startRequest) {
        ticketService.startSimulation(startRequest);
        return ResponseEntity.ok("Simulation started for config ID " + startRequest.getConfigId());
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        ticketService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped");
    }
}
