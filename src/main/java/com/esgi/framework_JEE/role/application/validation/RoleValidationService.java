package com.esgi.framework_JEE.role.application.validation;

import com.esgi.framework_JEE.role.domain.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class RoleValidationService {
    public RoleValidationService() {
    }

    @JsonIgnore
    public boolean isValid(Role role){
        if(role == null)
            return false;
        return !Objects.equals(role.getTitlePermission(), "") && !role.getTitlePermission().isBlank();
    }
}
