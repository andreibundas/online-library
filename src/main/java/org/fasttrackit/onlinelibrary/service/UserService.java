package org.fasttrackit.onlinelibrary.service;

import org.fasttrackit.onlinelibrary.domain.User;
import org.fasttrackit.onlinelibrary.exception.ResourceNotFoundException;
import org.fasttrackit.onlinelibrary.persistence.UserRepository;
import org.fasttrackit.onlinelibrary.transfer.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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

    public User getUser(long id) {
        LOGGER.info("Retrieving user with id: {} ", id);

        return userRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("User " + id + " does not exist"));

    }
    public User updateUser(long id, SaveUserRequest request) {
        LOGGER.info("Updating user : {}: {} ", id, request);

        User existingUser = getUser(id);

        BeanUtils.copyProperties(request, existingUser);

        return userRepository.save(existingUser);
    }

    public void deleteUser(long id) {
        LOGGER.info(" Deleting user : {}", id);

       userRepository.deleteById(id);
    }
}
