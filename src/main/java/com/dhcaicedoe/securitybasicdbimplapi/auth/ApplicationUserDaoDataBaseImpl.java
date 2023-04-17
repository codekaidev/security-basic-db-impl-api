package com.dhcaicedoe.securitybasicdbimplapi.auth;

import com.dhcaicedoe.securitybasicdbimplapi.user.IUserRepository;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation of the methods defined in the ApplicationUserDao interface
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Repository("applicationUserDaoImplDataBaseRepo")
public class ApplicationUserDaoDataBaseImpl implements ApplicationUserDao{

    private final IUserRepository iUserRepository;

    @Override public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    /**
     * Search user by email
     * @param email user information for your search
     * @return
     */
    @Override public Optional<User> findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }


}
