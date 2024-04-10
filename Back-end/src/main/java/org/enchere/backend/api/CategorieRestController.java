package org.enchere.backend.api;

import org.enchere.backend.model.Categorie;
import org.enchere.backend.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategorieRestController {

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

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody Categorie categorieDetails) {
        Categorie categorie = categorieService.getCategoryById(id);
        if (categorie != null) {
            categorie.setLibelle(categorieDetails.getLibelle());
            categorieService.saveCategory(categorie);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        categorieService.deleteCategory(id);
    }
}
