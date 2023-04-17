package com.dhcaicedoe.securitybasicdbimplapi.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final IUserRepository iUserRepository;

    public User createUser(User user){
        return iUserRepository.save(user);
    }

}
