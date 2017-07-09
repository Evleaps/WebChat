package net.webChat.registration.service;

import net.webChat.registration.model.User;

/**
 * Service class for {@link net.webChat.registration.model.User}
 */

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
