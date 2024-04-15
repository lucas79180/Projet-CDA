package org.enchere.backend.service;

import org.enchere.backend.model.User;

import java.util.List;

public interface UserService {

    List<User> consulterUser();

    User consulterUserParId(Long id);

    User consulterUserParEmail(String email);

    void createUser(User user);

    void supprimerUserParId(long id);

    User modifierUser(User user);

    User recupererUser(String pseudo);
}
