package org.enchere.backend.service;

import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Retrait;
import org.enchere.backend.model.User;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVendu> getAllArticles();
    ArticleRetrait getArticleById(Integer id);
    ArticleVendu saveArticle(ArticleRetrait article);
    ArticleVendu modifierArticle(ArticleVendu article);
    ArticleVendu consulterArticleParId(Long id);
    void deleteArticle(Integer id);

    List<Retrait> getAllRetrait();
    Retrait saveRetrait(Retrait retrait);
    Retrait getRetraitById(Integer id);
    void deleteRetraitById(Integer id);
}
