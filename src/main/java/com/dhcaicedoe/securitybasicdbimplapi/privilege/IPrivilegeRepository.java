package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPrivilegeRepository extends CrudRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);

}
