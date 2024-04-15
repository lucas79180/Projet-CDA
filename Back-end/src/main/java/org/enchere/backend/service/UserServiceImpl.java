package org.enchere.backend.service;

import org.enchere.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.enchere.backend.model.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'ID : " + id));
    }

    @Override
    public User consulterUserParEmail(String email) {
        return (User) userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'email : " + email));
    }

    @Override
    public void createUser(User user) {
        user.setMot_de_passe(passwordEncoder.encode(user.getMot_de_passe()));
        userRepository.save(user);
    }

    @Override
    public User recupererUser(String pseudo) {
        List<User> users = userRepository.findAll();
        // pour l'instant, on n'arrive pas à récupérer un membre par pseudo
        // donc on va chercher tous les membres en base et on fait une itération
        for (User user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void supprimerUserParId(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User modifierUser(User user) {
        // Récupérer l'utilisateur existant par son ID
        User utilisateurExistant = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'ID : " + user.getId()));

        // Mettre à jour les champs de l'utilisateur avec les valeurs de l'utilisateur modifié
        utilisateurExistant.setPseudo(user.getPseudo());
        utilisateurExistant.setPrenom(user.getPrenom());
        utilisateurExistant.setNom(user.getNom());
        utilisateurExistant.setMot_de_passe(user.getMot_de_passe());
        utilisateurExistant.setEmail(user.getEmail());
        utilisateurExistant.setCode_postal(user.getCode_postal());
        utilisateurExistant.setVille(user.getVille());
        utilisateurExistant.setRue(user.getRue());

        // Enregistrer les modifications dans la base de données
        userRepository.save(utilisateurExistant);

        return utilisateurExistant;
    }
}
