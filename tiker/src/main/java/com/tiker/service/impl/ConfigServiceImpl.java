
package com.tiker.service.impl;

import com.tiker.dto.RequestConfigDto;
import com.tiker.dto.ResponseConfigDto;
import com.tiker.entity.ConfigEntity;
import com.tiker.repo.ConfigRepository;
import com.tiker.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Override
    public ResponseConfigDto saveConfig(RequestConfigDto dto) {
        ConfigEntity entity = new ConfigEntity();
        entity.setInitialTickets(dto.getInitialTickets());
        entity.setTicketReleaseRate(dto.getTicketReleaseRate());
        entity.setCustomerRetrievalRate(dto.getCustomerRetrievalRate());
        entity.setMaxTicketCapacity(dto.getMaxTicketCapacity());


        ConfigEntity saved = configRepository.save(entity);
        return new ResponseConfigDto(saved.getId(), "Configuration Saved");

    }
}
