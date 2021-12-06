package org.fasttrackit.onlinelibrary.service;

import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.persistence.UserRepository;
import org.fasttrackit.onlinelibrary.transfer.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SaveUserRequest request) {
        LOGGER.info("Creating user: {} ", request );
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return userRepository.save(user);
    }
}
