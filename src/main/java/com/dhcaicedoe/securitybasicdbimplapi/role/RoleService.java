package com.dhcaicedoe.securitybasicdbimplapi.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Transactional
@Service
public class RoleService {

    private final IRoleRepository iRoleRepository;

    public Role createRole(Role role){
        Optional<Role> optRole = iRoleRepository.findByName(role.getName());
        if(optRole.isPresent())
            throw new RuntimeException("Role already exist");
        return iRoleRepository.save(role);
    }

}
