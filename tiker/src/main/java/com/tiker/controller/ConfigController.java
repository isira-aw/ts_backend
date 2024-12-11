package com.tiker.controller;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    public ResponseEntity<List<ConfigDto>> getAllConfigs() {
        return ResponseEntity.ok(configService.getAllConfigs());
    }

    @PostMapping("/con")
    public ResponseEntity<ConfigDto> createConfig(@RequestBody NewConfigRequestDto dto) {
        var config = configService.createConfig(dto); // Calls createConfig method in ConfigServiceImpl
        ConfigDto response = new ConfigDto(
                config.getId(),
                config.getInitialTickets(),
                config.getTicketReleaseRate(),
                config.getCustomerRetrievalRate(),
                config.getMaxTicketCapacity()
        );
        return ResponseEntity.ok(response);
    }


}
