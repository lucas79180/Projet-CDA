package org.enchere.backend.api;

import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.service.ArticleVenduService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
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
    public ArticleVendu getArticleById(@PathVariable int id) {
        return articleVenduService.getArticleById(id);
    }

    // Endpoint pour créer un nouvel article
    @PostMapping
    public void createArticle(@RequestBody ArticleRetrait article) {

        System.out.println("--LOG-- exec createArticle()");
        System.out.println("--LOG-- article: ");

        System.out.println(article.getRetrait());

        articleVenduService.saveArticle(article);
    }

    // Endpoint pour supprimer un article par son ID
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleVenduService.deleteArticle(id);
    }
}
