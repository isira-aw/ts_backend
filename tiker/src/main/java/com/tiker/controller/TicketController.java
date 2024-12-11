package com.tiker.controller;

import com.tiker.dto.StartRequestDto;
import com.tiker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "*")
@Controller("webSocketTicketEventController")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/start")
    public void startSimulation(@RequestBody StartRequestDto request) {
        ticketService.startSimulation(request);
    }

    @PostMapping("/stop")
    public void stopSimulation() {
        ticketService.stopSimulation();
    }
}
