package com.tiker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for sending back responses after saving configuration.
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor

public class ResponseConfigDto {
        private Long configId;
        private String status;

        // No-argument constructor
        public ResponseConfigDto() {
        }

        // All-argument constructor
        public ResponseConfigDto(Long configId, String status) {
            this.configId = configId;
            this.status = status;
        }

        // Getters and setters (if not using Lombok)
        public Long getConfigId() {
            return configId;
        }

        public void setConfigId(Long configId) {
            this.configId = configId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
}

