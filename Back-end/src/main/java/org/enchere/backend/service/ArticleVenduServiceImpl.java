package org.enchere.backend.service;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.Repository.RetraitRepository;
import org.enchere.backend.model.ArticleRetrait;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Retrait;
import org.enchere.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    public ArticleRetrait getArticleById(Integer id) {


        ArticleRetrait articleRechercher = new ArticleRetrait();

        articleRechercher.setArticle(articleVenduRepository.findById(id).orElse(null));
        System.out.println("--LOG-- articleRechercher = " + articleRechercher);
        articleRechercher.setRetrait(retraitRepository.findByIdArticle(articleRechercher.getArticle().getNoArticle()));
        return articleRechercher;


    }

    @Override
    public ArticleVendu saveArticle(ArticleRetrait newArticle) {

        // gestion de la double dépendance



        //newArticle.getRetrait().setArticleVendu(newArticle);

        // Sauvegarde en bdd
        System.out.println("--LOG-- newArticle : " + newArticle);
        System.out.println("--LOG-- getRetrait : " + newArticle.getRetrait());
        System.out.println("--LOG-- getArticle :" + newArticle.getArticle());

        newArticle.setArticle(articleVenduRepository.save(newArticle.getArticle()));

        newArticle.getRetrait().setArticleVendu(newArticle.getArticle());

        System.out.println("--LOG-- **SAVE ARTICLE**");
        System.out.println("--LOG-- newArticle : " + newArticle);
        System.out.println("--LOG-- getRetrait : " + newArticle.getRetrait());
        System.out.println("--LOG-- getArticle :" + newArticle.getArticle());
        retraitRepository.save(newArticle.getRetrait());

        //return newArticle;
        return newArticle.getArticle();
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

    @Override
    public ArticleVendu consulterArticleParId(Long id) {
        return articleVenduRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec l'ID : " + id));
    }

    @Override
    public ArticleVendu modifierArticle(ArticleVendu article) {
        // Récupérer l'utilisateur existant par son ID
        ArticleVendu articleExistant = articleVenduRepository.findById(article.getNoArticle())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article non trouvé avec l'ID : " + article.getNoArticle()));

        // Mettre à jour les champs de l'utilisateur avec les valeurs de l'utilisateur modifié
        articleExistant.setNomArticle(article.getNomArticle());
        articleExistant.setCategorie(article.getCategorie());
        articleExistant.setDescription(article.getDescription());
        articleExistant.setMiseAPrix(article.getMiseAPrix());
        articleExistant.setPrixVente(article.getPrixVente());
        articleExistant.setDateDebutEncheres(article.getDateDebutEncheres());
        articleExistant.setDateFinEncheres(article.getDateFinEncheres());

        // Enregistrer les modifications dans la base de données
        articleVenduRepository.save(articleExistant);

        return articleExistant;
    }
}
