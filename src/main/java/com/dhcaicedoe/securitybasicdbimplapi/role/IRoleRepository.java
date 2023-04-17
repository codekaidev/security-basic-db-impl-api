package com.dhcaicedoe.securitybasicdbimplapi.role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface based on the role model that defines the methods used for interaction with databases
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
