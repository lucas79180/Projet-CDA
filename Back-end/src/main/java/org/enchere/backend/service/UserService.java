package org.enchere.backend.service;

import org.enchere.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> consulterUser();

    User consulterUserParId(Long id);

    void createUser(User user);

    public User recupererUser(String pseudo);
}
