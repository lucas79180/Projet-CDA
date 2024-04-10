package org.enchere.backend.service;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.model.ArticleVendu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public class ArticleVenduServiceImpl implements ArticleVenduService {

    private final ArticleVenduRepository articleVenduRepository;

    @Autowired
    public ArticleVenduServiceImpl(ArticleVenduRepository articleVenduRepository) {
        this.articleVenduRepository = articleVenduRepository;
    }

    @Override
    public List<ArticleVendu> getAllArticleVendus() {
        return articleVenduRepository.findAll();
    }

    @Override
    public ArticleVendu getArticleVenduById(int id) {
        Optional<ArticleVendu> optionalArticleVendu = articleVenduRepository.findById((long) id);
        return optionalArticleVendu.orElse(null);
    }

    @Override
    public void createArticleVendu(ArticleVendu articleVendu) {
        articleVenduRepository.save(articleVendu);
    }
}
