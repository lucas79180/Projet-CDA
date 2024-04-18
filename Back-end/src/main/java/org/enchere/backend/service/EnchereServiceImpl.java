package org.enchere.backend.service;

import org.enchere.backend.Repository.ArticleVenduRepository;
import org.enchere.backend.Repository.EnchereRepository;
import org.enchere.backend.model.ArticleVendu;
import org.enchere.backend.model.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {

    @Autowired
    private EnchereRepository enchereRepository;
    private ArticleVenduRepository articleVenduRepository;

    @Autowired
    public EnchereServiceImpl(EnchereRepository enchereRepository, ArticleVenduRepository articleVenduRepository) {
        this.enchereRepository = enchereRepository;
        this.articleVenduRepository = articleVenduRepository;
    }

    @Override
    public List<Enchere> getAllEncheres() {
        return enchereRepository.findAll();
    }

    @Override
    public Enchere saveEnchere(Enchere enchere) {
        // Récupérer l'article vendu associé à l'enchère
        ArticleVendu articleVendu = articleVenduRepository.findById(enchere.getArticleVendu().getNoArticle())
                .orElseThrow(() -> new IllegalArgumentException("Article vendu non trouvé avec l'ID : " + enchere.getArticleVendu().getNoArticle()));

        // Définir l'article vendu de l'enchère
        enchere.setArticleVendu(articleVendu);

        // Enregistrer l'enchère dans la base de données
        return enchereRepository.save(enchere);
    }

    @Override
    public List<Enchere> getEncheresByArticleID(Integer articleID) {
        return enchereRepository.findByIdArticle(articleID);
    }
}
