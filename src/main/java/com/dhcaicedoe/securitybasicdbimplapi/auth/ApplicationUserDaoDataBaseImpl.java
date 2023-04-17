package com.dhcaicedoe.securitybasicdbimplapi.auth;

import com.dhcaicedoe.securitybasicdbimplapi.user.IUserRepository;
import com.dhcaicedoe.securitybasicdbimplapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository("applicationUserDaoImplDataBaseRepo")
public class ApplicationUserDaoDataBaseImpl implements ApplicationUserDao{

    private final IUserRepository iUserRepository;

    @Override public Optional<ApplicationUser> findByUsername(String username) {
        return Optional.empty();
    }

    @Override public Optional<User> findByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }


}
