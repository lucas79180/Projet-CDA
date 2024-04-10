package org.enchere.backend.service;

import org.enchere.backend.model.ArticleVendu;

import java.util.List;

public interface ArticleVenduService {

    List<ArticleVendu> getAllArticleVendus();

    ArticleVendu getArticleVenduById(int id);

    void createArticleVendu(ArticleVendu articleVendu);
}
