package com.tiker.controller;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.dto.PermissionDto;
import com.tiker.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/configs")
public class ConfigController {

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    @Autowired
    private ConfigService configService;

    @GetMapping
    public ResponseEntity<List<ConfigDto>> getAllConfigs() {
        return ResponseEntity.ok(configService.getAllConfigs());
    }

    @PostMapping("/new")
    public ResponseEntity<ConfigDto> createConfig(@RequestBody NewConfigRequestDto dto) {
        // Create and return config
        var config = configService.createConfig(dto);
        ConfigDto response = new ConfigDto(
                config.getId(),
                config.getInitialTickets(),
                config.getTicketReleaseRate(),
                config.getCustomerRetrievalRate(),
                config.getMaxTicketCapacity(),
                config.isPermissionGranted()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/permission/{id}")
    public ResponseEntity<String> setPermission(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
        configService.setPermission(id, permissionDto.isGrant());
        return ResponseEntity.ok("Permission updated");
    }

    public ConfigService getConfigService() {
        return configService;
    }

    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }
}
