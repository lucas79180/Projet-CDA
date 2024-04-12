package org.enchere.backend.controller;

import org.enchere.backend.model.Categorie;
import org.enchere.backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    //@GetMapping("/")
    @ModelAttribute
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategoryById(@PathVariable Integer id) {
        return categorieService.getCategoryById(id);
    }

    @PostMapping("/")
    public void addCategory(@RequestBody Categorie categorie) {
        categorieService.saveCategory(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categorieService.deleteCategory(id);
    }
}
