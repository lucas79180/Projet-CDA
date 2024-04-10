package org.enchere.backend.service;

import org.enchere.backend.model.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVendu> getAllArticles();
    ArticleVendu getArticleById(int id);
    ArticleVendu saveArticle(ArticleVendu article);
    void deleteArticle(int id);
}
