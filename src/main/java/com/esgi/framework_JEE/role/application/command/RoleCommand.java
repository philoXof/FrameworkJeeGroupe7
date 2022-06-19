package com.esgi.framework_JEE.role.application.command;

import com.esgi.framework_JEE.role.application.validation.RoleValidationService;
import com.esgi.framework_JEE.role.domain.entity.Role;
import com.esgi.framework_JEE.role.domain.repository.RoleRepository;
import com.esgi.framework_JEE.role.web.resquest.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleCommand {
    @Autowired
    RoleRepository roleRepository;

    RoleValidationService roleValidationService = new RoleValidationService();

    public RoleCommand(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role create(RoleRequest roleRequest){
        var role = new Role();
        role.setTitlePermission(roleRequest.name);
        if (!roleValidationService.isValid(role))
            return null;
        return roleRepository.save(role);
    }


    public void delete(int id){
        var role = roleRepository.findById(id);
        roleRepository.delete(role);
    }

    public Role changeName(RoleRequest roleRequest, int roleId) {
        Optional<Role> dbRole = Optional.ofNullable(roleRepository.findById(roleId));
        if(dbRole.isPresent()){
            dbRole.get().setTitlePermission(roleRequest.name);
            if(roleValidationService.isValid(dbRole.get()))
                return roleRepository.save(dbRole.get());
        }
        return null;
    }
}
