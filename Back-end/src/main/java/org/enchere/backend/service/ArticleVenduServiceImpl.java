package org.enchere.backend.service;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.model.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private final ArticleVenduRepository articleVenduRepository;

    @Autowired
    public ArticleVenduServiceImpl(ArticleVenduRepository articleVenduRepository) {
        this.articleVenduRepository = articleVenduRepository;
    }

    @Override
    public List<ArticleVendu> getAllArticles() {
        return articleVenduRepository.findAll();
    }

    @Override
    public ArticleVendu getArticleById(int id) {
        return articleVenduRepository.findById(id).orElse(null);
    }

    @Override
    public ArticleVendu saveArticle(ArticleVendu article) {
        return articleVenduRepository.save(article);
    }

    @Override
    public void deleteArticle(int id) {
        articleVenduRepository.deleteById(id);
    }
}
