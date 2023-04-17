package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service layer for the business logic in relation to the privilege model
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Transactional
@Service
public class PrivilegeService {

    private final IPrivilegeRepository iPrivilegeRepository;

    /**
     * create privileges
     *
     * @param privilege privilege to create
     * @return privilege created
     */
    public Privilege createPrivilege(Privilege privilege) {
        Optional<Privilege> optPrivilege = iPrivilegeRepository.findByName(privilege.getName());
        if(optPrivilege.isPresent())
            throw new RuntimeException("Privilege already exist");
        return iPrivilegeRepository.save(privilege);
    }

}
