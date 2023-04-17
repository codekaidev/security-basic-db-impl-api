package com.dhcaicedoe.securitybasicdbimplapi.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface based on the user model that defines the methods used for interaction with databases
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
