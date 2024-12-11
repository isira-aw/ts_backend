package com.tiker.controller;

import com.tiker.dto.StartRequestDto;
import com.tiker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestBody StartRequestDto startRequest) {
        try {
            ticketService.startSimulation(startRequest);
            return ResponseEntity.ok("Simulation started successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        try {
            ticketService.stopSimulation();
            return ResponseEntity.ok("Simulation stopped successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}
