package org.enchere.backend.controller;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.model.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleVenduController {

    @Autowired
    private ArticleVenduRepository articleVenduRepository;

    @PostMapping()
    public ArticleVendu createArticleVendu(@RequestBody ArticleVendu article) {
        System.out.println("\n -- createArticleVendu");
        System.out.println("Article: " + article);
        return articleVenduRepository.save(article);
    }
}
