package org.enchere.backend.api;

import jakarta.validation.Valid;
import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.User;
import org.enchere.backend.service.ArticleVenduService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleVenduRestController {

    private static final Logger log = LoggerFactory.getLogger(ArticleVenduRestController.class);
    private final ArticleVenduService articleVenduService;


    @Autowired
    public ArticleVenduRestController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
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

    // Endpoint pour supprimer un article par son ID
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleVenduService.deleteArticle(id);
    }
}
