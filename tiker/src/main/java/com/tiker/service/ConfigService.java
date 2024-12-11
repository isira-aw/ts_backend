package com.tiker.service;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.entity.ConfigEntity;

import java.util.List;

public interface ConfigService {
    ConfigEntity createConfig(NewConfigRequestDto dto);
    List<ConfigDto> getAllConfigs();
    ConfigEntity getConfigById(int id);
}
