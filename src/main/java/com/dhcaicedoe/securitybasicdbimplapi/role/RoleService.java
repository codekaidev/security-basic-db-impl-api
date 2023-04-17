package com.dhcaicedoe.securitybasicdbimplapi.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service layer for the business logic in relation to the role model
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Transactional
@Service
public class RoleService {

    private final IRoleRepository iRoleRepository;

    /**
     * create roles
     *
     * @param role role to create
     * @return role created
     */
    public Role createRole(Role role) {
        Optional<Role> optRole = iRoleRepository.findByName(role.getName());
        if (optRole.isPresent())
            throw new RuntimeException("Role already exist");
        return iRoleRepository.save(role);
    }

}
