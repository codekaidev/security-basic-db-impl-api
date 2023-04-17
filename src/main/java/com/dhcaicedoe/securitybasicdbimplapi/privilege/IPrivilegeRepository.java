package com.dhcaicedoe.securitybasicdbimplapi.privilege;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface based on the privileges model that defines the methods used for interaction with databases
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Repository
public interface IPrivilegeRepository extends CrudRepository<Privilege, Long> {

    Optional<Privilege> findByName(String name);

}
