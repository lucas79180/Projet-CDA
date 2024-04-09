package org.enchere.backend.controller;

import org.enchere.backend.Repository.UserRepository;
import org.enchere.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {

    @Autowired
    private UserRepository utilisateurRepository;

    @PostMapping()
    public User ajouterUtilisateur(@RequestBody User utilisateur) {
        System.out.println("--- ajouterUtilisateur()");
        System.out.println(utilisateur);
        return utilisateurRepository.save(utilisateur);
    }
}
