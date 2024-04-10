package org.enchere.backend.service;

import org.enchere.backend.Repository.CategorieRepository;
import org.enchere.backend.model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategoryById(long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCategory(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public void deleteCategory(long id) {
        categorieRepository.deleteById(id);
    }

}