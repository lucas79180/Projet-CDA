package org.enchere.backend.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.enchere.backend.api.doc.SwaggerDoc;
import org.enchere.backend.model.User;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private JwtUtils jwtUtils;

    @Value("${jwt.secret}")
    private String secret;

    @GetMapping
    public  List<User>  getFormulaireUser() {
        return userService.consulterUser();
    }

    @PostMapping
    public void postUser(@RequestBody User user) {
        userService.createUser(user);
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

    @GetMapping("/profil")
    @ResponseStatus(HttpStatus.OK)
    public User getCurrentUser(HttpServletRequest request) {
        // Récupérer le token JWT à partir de la requête HTTP
        String token = request.getHeader("Authorization").replace("Bearer ", "");

        // Extraire le pseudo de l'utilisateur à partir du token JWT
        String pseudo = jwtUtils.extractUsername(token, secret);

        // Utiliser UserService pour récupérer les informations de l'utilisateur à partir du pseudo
        return userService.recupererUser(pseudo);
    }
}
