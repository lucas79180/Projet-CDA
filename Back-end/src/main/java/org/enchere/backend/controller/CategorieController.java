package org.enchere.backend.controller;

import org.enchere.backend.model.Categorie;
import org.enchere.backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorie ")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @GetMapping("/")
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategoryById(@PathVariable long id) {
        return categorieService.getCategoryById(id);
    }

    @PostMapping("/")
    public void addCategory(@RequestBody Categorie categorie) {
        categorieService.saveCategory(categorie);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        categorieService.deleteCategory(id);
    }
}
