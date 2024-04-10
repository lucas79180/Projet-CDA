package org.enchere.backend.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.enchere.backend.api.doc.SwaggerDoc;
import org.enchere.backend.model.User;
import org.enchere.backend.security.JwtUtils;
import org.enchere.backend.security.MyUserDetailsService;
import org.enchere.backend.security.UtilisateurSpringSecurity;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "API UTILISATEURS", description = SwaggerDoc.DESC_GENERIQUE + "utilisateurs")
@RestController// obligatoire afin que le controller soit dans le contexte Spring
@CrossOrigin
@RequestMapping("/api/utilisateurs") // tous les @Getmapping/@Postmapping de mon controller auront le chemin de base /films
public class UserRestController {
    @Autowired
    UserService userService;

    @PostMapping
    public void postUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping
    public User getConnectedUser(HttpServletRequest request) throws Exception {
        // 1 - je recupère le nom d'utilisateur correspondant au token JWT de la requête
        String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.parseJwt(request));
        // 2 - je recupère l'utilisateur Spring Security correspondant à ce nom d'utilisateur
        UtilisateurSpringSecurity user = (UtilisateurSpringSecurity) userDetailsService.loadUserByUsername(username);

        // 3 - je renvoie le membre correspondant
        return user.getUser();
    }

}
