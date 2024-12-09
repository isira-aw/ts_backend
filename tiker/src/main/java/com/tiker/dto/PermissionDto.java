package com.tiker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private boolean grant;

    public PermissionDto(boolean grant) {
        this.grant = grant;
    }

    public boolean isGrant() {
        return grant;
    }

    public void setGrant(boolean grant) {
        this.grant = grant;
    }
}
