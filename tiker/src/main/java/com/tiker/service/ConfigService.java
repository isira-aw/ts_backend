package com.tiker.service;

import com.tiker.dto.RequestConfigDto;
import com.tiker.dto.ResponseConfigDto;

public interface ConfigService {
    ResponseConfigDto saveConfig(RequestConfigDto dto);
}
