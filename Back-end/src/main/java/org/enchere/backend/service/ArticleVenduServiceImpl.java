package org.enchere.backend.service;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.Repository.RetraitRepository;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ArticleVenduServiceImpl implements ArticleVenduService {

    private final ArticleVenduRepository articleVenduRepository;
    private final RetraitRepository retraitRepository;

    @Autowired
    public ArticleVenduServiceImpl(ArticleVenduRepository articleVenduRepository, RetraitRepository retraitRepository) {
        this.articleVenduRepository = articleVenduRepository;
        this.retraitRepository = retraitRepository;
    }

    @Override
    public List<ArticleVendu> getAllArticles() {
        return articleVenduRepository.findAll();
    }

    @Override
    public ArticleVendu getArticleById(Integer id) {
        return articleVenduRepository.findById(id).orElse(null);
    }

    @Override
    public ArticleVendu saveArticle(ArticleVendu article) {

        // gestion de la double dépendance
        article.getRetrait().setArticleVendu(article);

        // Sauvegarde en bdd
        articleVenduRepository.save(article);
        retraitRepository.save(article.getRetrait());

        return articleVenduRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleVenduRepository.deleteById(id);
    }



    /*
    *   | RETRAIT |
    */
    @Override
    public List<Retrait> getAllRetrait() {
        return retraitRepository.findAll();
    }

    @Override
    public Retrait saveRetrait(Retrait retrait) {
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait getRetraitById(Integer id) {
        return retraitRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRetraitById(Integer id) {
        retraitRepository.deleteById(id);
    }
}
