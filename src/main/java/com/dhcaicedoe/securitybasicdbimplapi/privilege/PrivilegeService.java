package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class PrivilegeService {

    private final IPrivilegeRepository iPrivilegeRepository;

    public Privilege createPrivilege(Privilege privilege) {
        Optional<Privilege> optPrivilege = iPrivilegeRepository.findByName(privilege.getName());
        if(optPrivilege.isPresent())
            throw new RuntimeException("Privilege already exist");
        return iPrivilegeRepository.save(privilege);
    }

}
