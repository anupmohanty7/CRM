package com.crm.dto;

import com.crm.enums.UserStatus;
import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {

    @NotNull
    private UserStatus status;

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}