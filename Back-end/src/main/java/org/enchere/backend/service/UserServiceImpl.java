package org.enchere.backend.service;

import org.enchere.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.enchere.backend.model.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> consulterUser() {
        return userRepository.findAll();
    }

    @Override
    public User consulterUserParId(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public void createUser(User user) {

        user.setMot_de_passe(passwordEncoder.encode(user.getMot_de_passe()));

        userRepository.save(user);
    }

    @Override
    public User recupererUser(String pseudo) {
        List<User> users = userRepository.findAll();
        // pour l'instant, on n'arrirve pas à recupérer un membre par pseudo
        // donc on va chercher tous les membres en base et on fait une itération
        for (User user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }
}
