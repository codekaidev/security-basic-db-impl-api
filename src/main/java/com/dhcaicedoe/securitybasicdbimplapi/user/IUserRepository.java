package com.dhcaicedoe.securitybasicdbimplapi.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
