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
    private final ConfigRepository repo;

    public ConfigServiceImpl(ConfigRepository repo) {
        this.repo = repo;
    }

    @Override
    public ConfigEntity createConfig(NewConfigRequestDto dto) {
        ConfigEntity entity = new ConfigEntity(
                dto.getInitialTickets(),
                dto.getTicketReleaseRate(),
                dto.getCustomerRetrievalRate(),
                dto.getMaxTicketCapacity()
        );
        return repo.save(entity);
    }


    @Override
    public ConfigEntity createConfigFromStart(int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        ConfigEntity entity = new ConfigEntity(initialTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        return repo.save(entity);
    }

    @Override
    public List<ConfigDto> getAllConfigs() {
        return repo.findAll().stream().map(e -> new ConfigDto(
                e.getId(),
                e.getInitialTickets(),
                e.getTicketReleaseRate(),
                e.getCustomerRetrievalRate(),
                e.getMaxTicketCapacity()
        )).collect(Collectors.toList());
    }

    @Override
    public ConfigEntity getConfigById(int id) {
        return repo.findById(id).orElse(null);
    }
}
