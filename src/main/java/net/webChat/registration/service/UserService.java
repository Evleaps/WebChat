package net.webChat.registration.service;

import net.webChat.registration.model.User;

import java.util.List;

/**
 * Service class for {@link net.webChat.registration.model.User}
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<User> getAllUsers();
}
