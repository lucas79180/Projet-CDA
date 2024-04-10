package org.enchere.backend.service;

import org.enchere.backend.model.Categorie;

import java.util.List;


public interface CategorieService {

    List<Categorie> getAllCategories();

    Categorie getCategoryById(long id);

    void saveCategory(Categorie categorie);

    void deleteCategory(long id);

}
