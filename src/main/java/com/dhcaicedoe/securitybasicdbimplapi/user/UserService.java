package com.dhcaicedoe.securitybasicdbimplapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer for the business logic in relation to the user model
 *
 * @author Daniel Caicedo
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final IUserRepository iUserRepository;

    /**
     * create users
     *
     * @param user user to create
     * @return user created
     */
    public User createUser(User user){
        return iUserRepository.save(user);
    }

}
