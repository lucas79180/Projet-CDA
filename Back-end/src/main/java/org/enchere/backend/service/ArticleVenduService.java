package org.enchere.backend.service;

import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Retrait;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVendu> getAllArticles();
    ArticleVendu getArticleById(Integer id);
    ArticleVendu saveArticle(ArticleVendu article);
    void deleteArticle(Integer id);

    List<Retrait> getAllRetrait();
    Retrait saveRetrait(Retrait retrait);
    Retrait getRetraitById(Integer id);
    void deleteRetraitById(Integer id);
}
