package com.tiker.controller;

import com.tiker.dto.RequestConfigDto;
import com.tiker.dto.ResponseConfigDto;
import com.tiker.service.ConfigService;
import com.tiker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling configuration and simulation commands.
 */
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private TicketService ticketService;

    /**
     * Endpoint to save configuration to DB.
     */
    @PostMapping("/config")
    public ResponseEntity<ResponseConfigDto> saveConfig(@RequestBody RequestConfigDto dto) {
        ResponseConfigDto response = configService.saveConfig(dto);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to start the simulation.
     */
    @PostMapping("/start")
    public ResponseEntity<String> startSimulation(@RequestBody RequestConfigDto dto) {
        ticketService.startSimulation(dto);
        return ResponseEntity.ok("Simulation started");
    }

    /**
     * Endpoint to stop the simulation.
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        ticketService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped");
    }
}
