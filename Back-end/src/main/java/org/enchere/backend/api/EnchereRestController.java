package org.enchere.backend.api;

import jakarta.servlet.http.HttpServletRequest;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.User;
import org.enchere.backend.security.JwtUtils;
import org.enchere.backend.security.UtilisateurSpringSecurity;
import org.enchere.backend.service.ArticleVenduService;
import org.enchere.backend.service.EnchereService;
import org.enchere.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.enchere.backend.model.Enchere;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/encheres")
public class EnchereRestController {

    @Autowired
    private EnchereService enchereService;
    @Autowired
    private ArticleVenduService articleVenduService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;


    // Endpoint pour récupérer toutes les enchères
    @GetMapping
    public ResponseEntity<List<Enchere>> getAllEncheres() {
        List<Enchere> encheres = enchereService.getAllEncheres();
        return new ResponseEntity<>(encheres, HttpStatus.OK);
    }

    @PostMapping("/{idArticle}/encheres")
    public void placeBid(@PathVariable int idArticle, @RequestBody Map<String, Integer> requestBody, HttpServletRequest request) {
        try {
            int bidPrice = requestBody.get("bidPrice");
            System.out.println("ID de l'article: " + idArticle);
            System.out.println("Prix de l'enchère proposé: " + bidPrice);

            // Récupérer l'utilisateur connecté à partir du token JWT
            String username = jwtUtils.getUserNameFromJwtToken(jwtUtils.parseJwt(request));
            UtilisateurSpringSecurity user = (UtilisateurSpringSecurity) userService.loadUserByUsername(username);
            User utilisateur = user.getUser();

            ArticleVendu article = articleVenduService.getArticleById(idArticle).getArticle();
            if (article == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
            }
            System.out.println("Article trouvé: " + article);

            // Créer une nouvelle enchère
            Enchere enchere = new Enchere();
            enchere.setDateEnchere(LocalDateTime.now());
            enchere.setMontantEnchere(bidPrice);
            enchere.setUtilisateur(utilisateur);

            // Associer l'enchère à l'article
            enchere.setArticleVendu(article);

            // Enregistrer l'enchère dans la base de données
            enchereService.saveEnchere(enchere);
            System.out.println("Enchère enregistrée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement de l'enchère: " + e.getMessage());
            throw e;
        }
    }


}
