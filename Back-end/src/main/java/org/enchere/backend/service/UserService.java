package org.enchere.backend.service;

import org.enchere.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(User user);

}
