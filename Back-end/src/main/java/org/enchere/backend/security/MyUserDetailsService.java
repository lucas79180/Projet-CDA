package org.enchere.backend.security;

import org.enchere.backend.model.User;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A partir du moment ou on a dans le contexte Spring un service qui implémente UserDetailsService
 * => Spring security va l'utiliser pour aller chercher les utilisateurs dans le processus d'authentification
 */
@Service
public class MyUserDetailsService implements UserDetailsService, CommandLineRunner {


    @Autowired
    private UserService userService;

    /**
     * Comment est-ce qu'on va chercher un utilisateur Spring Security à partir d'un pseudo?
     * => à partir du service membreService
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.recupererUser(username);

        // si le membre n'est pas trouvé : je lance une exception afin que Spring Security affiche une erreur dans le formulaire
        if (user == null){
            throw new UsernameNotFoundException(username);
        }

        // si on a trouvé un membre, je retourne un utilisateur Spring Security qui englobe le membre
        return new UtilisateurSpringSecurity(user);
    }

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userService.consulterUser();

        // si il n'y a pas de membre dans l'application, on en crée un
        if (users.isEmpty()){
            User admin = new User("Lucas", "Soares", "1234", "0624460942", "79180","lucas.soaresmoenner@gmail.com","8 rue de la jungle","chauray","1234");
            userService.createUser(admin);
        }
    }
}