package org.enchere.backend.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Enchere;
import org.enchere.backend.model.User;
import org.enchere.backend.security.JwtUtils;
import org.enchere.backend.security.UtilisateurSpringSecurity;
import org.enchere.backend.service.ArticleVenduService;
import org.enchere.backend.service.EnchereService;
import org.enchere.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleVenduRestController {

    private static final Logger log = LoggerFactory.getLogger(ArticleVenduRestController.class);
    private final ArticleVenduService articleVenduService;

    @Autowired
    private final EnchereService enchereService;


    @Autowired
    public ArticleVenduRestController(ArticleVenduService articleVenduService, EnchereService enchereService) {
        this.articleVenduService = articleVenduService;
        this.enchereService = enchereService;
    }


    // Endpoint pour récupérer tous les articles
    @GetMapping
    public List<ArticleVendu> getAllArticles() {
        return articleVenduService.getAllArticles();
    }

    // Endpoint pour récupérer un article par son ID
    @GetMapping("/{id}")
    public ArticleRetrait getArticleById(@PathVariable int id) {
        return articleVenduService.getArticleById(id);
    }

    // Endpoint pour créer un nouvel article
    @PostMapping
    public ArticleRetrait createArticle(@RequestBody ArticleRetrait article) {

        System.out.println("--LOG-- exec createArticle()");
        System.out.println("--LOG-- article: " + article);
        System.out.println("--LOG-- Save");
        articleVenduService.saveArticle(article);
        System.out.println("--LOG-- article: " + article);
        return article;
    }

    @PutMapping("/{idArticle}")
    public void modifierArticle(@PathVariable Long idArticle, @Valid @RequestBody ArticleVendu articleModifie) {
        // Assurez-vous que l'ID de l'utilisateur à modifier correspond à celui fourni dans l'URL
        if (!idArticle.equals(articleModifie.getNoArticle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID de l'utilisateur dans l'URL ne correspond pas à celui dans le corps de la requête.");
        }

        articleVenduService.modifierArticle(articleModifie);
    }


    @PutMapping("/{idArticle}/bid")
    public void placeBid(@PathVariable int idArticle, @RequestBody Map<String, Integer> requestBody) {
        try {
            int bidPrice = requestBody.get("bidPrice");
            System.out.println("ID de l'article: " + idArticle);
            System.out.println("Prix de l'enchère proposé: " + bidPrice);

            ArticleVendu article = articleVenduService.getArticleById(idArticle);
            if (article == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found");
            }
            System.out.println("Article trouvé: " + article);

            // Vérifier si le prix de l'enchère est valide
            if (bidPrice <= article.getPrixVente()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le prix de l'enchère doit être supérieur au prix de vente actuel");
            }

            // Mettre à jour le prix de vente de l'article avec le nouveau prix de l'enchère
            article.setPrixVente(bidPrice);

            // Enregistrer l'article mis à jour dans la base de données
            articleVenduService.modifierArticle(article);
            System.out.println("Prix de vente mis à jour: " + article.getPrixVente());
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement de l'enchère: " + e.getMessage());
            throw e;
        }
    }


    // Endpoint pour supprimer un article par son ID
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleVenduService.deleteArticle(id);
    }
}
