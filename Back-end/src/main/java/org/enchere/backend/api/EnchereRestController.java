package org.enchere.backend.api;

import org.enchere.backend.model.Enchere;
import org.enchere.backend.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encheres")
public class EnchereRestController {

    @Autowired
    private EnchereService enchereService;


    // Endpoint pour récupérer toutes les enchères
    @GetMapping
    public List<Enchere> getAllEncheres() {
        return enchereService.getAllEncheres();
    }

    @GetMapping("/{articleId}")
    public List<Enchere> getEnchereByArticleId(@PathVariable Integer articleId) {
        return enchereService.getEncheresByArticleID(articleId);
    }

    @PostMapping
    public Enchere placeBid(@RequestBody Enchere enchere) {
        System.out.println("--LOG--Post-Enchere" + enchere);
        try {
            // Enregistrer l'enchère dans la base de données
            return enchereService.saveEnchere(enchere);
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement de l'enchère: " + e.getMessage());
            throw e;
        }
    }


}
