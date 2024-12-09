package com.tiker.service.impl;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.entity.ConfigEntity;
import com.tiker.repo.ConfigRepository;
import com.tiker.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository repo;

    @Override
    public ConfigEntity createConfig(NewConfigRequestDto dto) {
        ConfigEntity entity = new ConfigEntity();
        entity.setInitialTickets(dto.getInitialTickets());
        entity.setTicketReleaseRate(dto.getTicketReleaseRate());
        entity.setCustomerRetrievalRate(dto.getCustomerRetrievalRate());
        entity.setMaxTicketCapacity(dto.getMaxTicketCapacity());
        entity.setPermissionGranted(false);
        return repo.save(entity);
    }

    public ConfigRepository getRepo() {
        return repo;
    }

    public void setRepo(ConfigRepository repo) {
        this.repo = repo;
    }

    public ConfigServiceImpl(ConfigRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ConfigDto> getAllConfigs() {
        return repo.findAll().stream().map(e -> new ConfigDto(
                e.getId(),
                e.getInitialTickets(),
                e.getTicketReleaseRate(),
                e.getCustomerRetrievalRate(),
                e.getMaxTicketCapacity(),
                e.isPermissionGranted()
        )).collect(Collectors.toList());
    }

    @Override
    public void setPermission(Long id, boolean grant) {
        ConfigEntity entity = repo.findById(id).orElseThrow(() -> new RuntimeException("Config not found"));
        entity.setPermissionGranted(grant);
        repo.save(entity);
    }

    @Override
    public ConfigEntity getConfigById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Config not found"));
    }
}
