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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "API UTILISATEURS", description = SwaggerDoc.DESC_GENERIQUE + "utilisateurs")
@RestController// obligatoire afin que le controller soit dans le contexte Spring
@CrossOrigin
@RequestMapping("/api/utilisateurs") // tous les @Getmapping/@Postmapping de mon controller auront le chemin de base /films
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping
    public  List<User>  getFormulaireUser() {
        return userService.consulterUser();
    }

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

    @DeleteMapping("/{idUser}")
    public void supprimerUser(@PathVariable Long idUser) {
        userService.supprimerUserParId(idUser);
    }

    @PutMapping("/{idUser}")
    public void modifierUtilisateur(@PathVariable Long idUser, @Valid @RequestBody User userModifie) {
        // Assurez-vous que l'ID de l'utilisateur à modifier correspond à celui fourni dans l'URL
        if (!idUser.equals(userModifie.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID de l'utilisateur dans l'URL ne correspond pas à celui dans le corps de la requête.");
        }

        // Utilisez le service pour mettre à jour l'utilisateur
        userService.modifierUser(userModifie);
    }
}
