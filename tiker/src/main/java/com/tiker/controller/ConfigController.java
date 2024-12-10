package com.tiker.controller;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.service.ConfigService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    private final Logger logger = LoggerFactory.getLogger(ConfigController.class);
    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }


    @PostMapping("/con")
    public ResponseEntity<ConfigDto> createConfig(@RequestBody NewConfigRequestDto dto) {
        logger.info("Received request to create config: {}", dto);
        try {
            var config = configService.createConfig(dto);
            ConfigDto response = new ConfigDto(
                    config.getId(),
                    config.getInitialTickets(),
                    config.getTicketReleaseRate(),
                    config.getCustomerRetrievalRate(),
                    config.getMaxTicketCapacity()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error creating config", e);
            return ResponseEntity.status(500).body(null);
        }
    }

}
