package org.enchere.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration // Nécessaire lorsqu'on définit des @Bean dans une classe
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Correspond au filtre qui va être exécuté en amont de chaque vérification Spring security
     * Ce filtre va décoder le token JWT et mettre à jour le contexte Spring Security au besoin
     */
    @Autowired
    private JwtFilter jwtFilter;

    /**
     * On définit un filtre de type SecurityFilterChain
     * qui va être ajouté au contexte Spring (car on a mis @Bean)
     *
     * Spring Security va comprendre que c'est ce filtre qu'il va devoir utiliser
     * à la place de son filtre par défaut (qui n'autorise aucune requête non authentifiée)
     *
     * dans .requestMatchers, on peut mettre :
     * - soit une url : .requestMatchers("/pageConnecte")
     * - soit une liste d'url : .requestMatchers("/pageConnecte1", "/pageConnecte2", "/pageConnecte3")
     * - soit une url avec un wildcard (*) : .requestMatchers("/prive/*")
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuration CORS pour autoriser les requêtes de différentes origines
        http.cors(customizer -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedOrigin("*"); // Autorise toutes les origines
            corsConfiguration.addAllowedMethod("*"); // Autorise toutes les méthodes HTTP (GET, POST, etc.)
            corsConfiguration.addAllowedHeader("*"); // Autorise tous les en-têtes
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            customizer.configurationSource(source);
        });

        // on ignore la vérification CSRF sur les requêtes d'API (pas de risque car pas de Cookies)
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        // on autorise la requête http entrante en fonction de ces critères :
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                        // Autoriser l'accès au profil après connexion
                        .requestMatchers("/monprofil").authenticated()
                        // Autoriser l'accès à la déconnexion après connexion
                        .requestMatchers("/logout").authenticated()
                        // Toutes les autres URL nécessitent une authentification
                        .anyRequest().permitAll()
                )
                // on utilise une authentification basique (utilisateur / mot de passe)
                .httpBasic(Customizer.withDefaults())
                // on utilise le formulaire par défaut proposé par Spring Security
                // quand on se déconnecte => on redirige vers l'accueil
                .logout(logout -> logout.logoutSuccessUrl("/"));

        /*****************************************************************
         * AVANT DE FAIRE LA VÉRIFICATION DE SÉCURITÉ, ON AJOUTE UN FILTRE
         * qui va vérifier la présence ou non d'un Json Web Token
         *
         * il va remplir le contexte Spring Security
         * à partir du token JWT
         ******************************************************************/
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session
                .maximumSessions(1) // Nombre maximum de sessions par utilisateur
                .expiredUrl("/logout")); // URL vers laquelle rediriger après expiration de la session

        return http.build();
    }

}
