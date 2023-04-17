package com.dhcaicedoe.securitybasicdbimplapi.auth;


import com.dhcaicedoe.securitybasicdbimplapi.user.User;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
