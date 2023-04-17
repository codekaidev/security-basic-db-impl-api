package com.dhcaicedoe.securitybasicdbimplapi.auth;


import com.dhcaicedoe.securitybasicdbimplapi.user.User;

import java.util.Optional;

/**
 * Interface that defines the lookup methods for authentication
 * @author Daniel Caicedo
 * @since 1.0.0
 */
public interface ApplicationUserDao {


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
