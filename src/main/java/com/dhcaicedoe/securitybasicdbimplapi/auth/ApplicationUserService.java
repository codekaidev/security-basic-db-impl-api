package com.dhcaicedoe.securitybasicdbimplapi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of UserDetailsService for user search
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao applicationUserDao;

    @Autowired
    ApplicationUserService(@Qualifier("applicationUserDaoImplDataBaseRepo") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
