package org.enchere.backend.api;

import org.enchere.backend.model.User;
import org.enchere.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginRestController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @PostMapping
    public String login(@RequestBody User user) throws Exception {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getPseudo(), user.getMot_de_passe());
        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // j'appèle la méthode qui génère un JWT à partir du contexte Spring d'autentification
        String jwt = jwtUtils.generateJwtToken(authentication);

        // je retourne ce token
        return jwt;
    }
}