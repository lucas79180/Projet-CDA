package org.enchere.backend.service;

import org.enchere.backend.model.Categorie;

import java.util.List;


public interface CategorieService {

    List<Categorie> getAllCategories();

    Categorie getCategoryById(Integer id);

    void saveCategory(Categorie categorie);

    void deleteCategory(Integer id);

}
