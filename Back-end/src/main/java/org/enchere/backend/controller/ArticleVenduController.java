package org.enchere.backend.controller;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.service.ArticleVenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleVenduController {

    private final ArticleVenduService articleVenduService;

    @Autowired
    public ArticleVenduController(ArticleVenduService articleVenduService) {
        this.articleVenduService = articleVenduService;
    }

    @GetMapping
    public List<ArticleVendu> getAllArticles() {
        return articleVenduService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleVendu getArticleById(@PathVariable int id) {
        return articleVenduService.getArticleById(id);
    }

    @PostMapping
    public ArticleVendu saveArticle(@RequestBody ArticleRetrait article) {
        System.out.println("--LOG-- exec ArticleVendu saveArticle()");
        System.out.println("--LOG-- article: " + article);
        return articleVenduService.saveArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleVenduService.deleteArticle(id);
    }
}
