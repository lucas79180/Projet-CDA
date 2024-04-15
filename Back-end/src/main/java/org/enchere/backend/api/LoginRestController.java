package org.enchere.backend.api;

import com.microsoft.sqlserver.jdbc.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.enchere.backend.model.User;
import org.enchere.backend.security.JwtUtils;
import org.enchere.backend.security.MyUserDetailsService;
import org.enchere.backend.security.UtilisateurSpringSecurity;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginRestController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String login(@RequestBody User user) throws Exception {
        // Créer une chaîne d'identification qui peut être soit le pseudo, soit l'email
        String identifier = user.getPseudo();
        if (identifier == null || identifier.isEmpty()) {
            identifier = user.getEmail();
        }

        // Créer une authentification basée sur l'identifiant (pseudo ou email) et le mot de passe
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(identifier, user.getMot_de_passe());

        // Authentifier l'utilisateur
        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Générer le token JWT
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Retourner le token JWT
        return jwt;
    }

    @GetMapping
    public User getConnectedUser(HttpServletRequest request) throws Exception {
        // 1 - je recupère le nom d'utilisateur correspondant au token JWT de la requête
        String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.parseJwt(request));
        // 2 - je recupère l'utilisateur Spring Security correspondant à ce nom d'utilisateur

        UtilisateurSpringSecurity user = (UtilisateurSpringSecurity) userDetailsService.loadUserByUsername(username);

        // 3 - je renvoie le membre correspondant
        return user.getUser();
    }
    @PutMapping("/{email}")
    public User modifierUtilisateur(@PathVariable String email, @RequestBody User utilisateurModifie) throws ChangeSetPersister.NotFoundException {
        // Retrieve the existing user by their ID
        User utilisateurExistant = userService.consulterUserParEmail(email);

        // Update the user's fields with the values of the modified user
        if (!StringUtils.isEmpty(utilisateurModifie.getMot_de_passe())) {
            utilisateurExistant.setMot_de_passe(passwordEncoder.encode(utilisateurModifie.getMot_de_passe()));
        }

        // Save the changes to the database via the service
        return userService.modifierUser(utilisateurExistant);
    }
}