package org.enchere.backend.api;

import org.enchere.backend.model.Retrait;
import org.enchere.backend.service.ArticleVenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/retrait")
public class RetraitRestController {

    private final ArticleVenduService retraitService;

    @Autowired
    public RetraitRestController(ArticleVenduService retraitService) {
        this.retraitService = retraitService;
    }

    @GetMapping
    public List<Retrait> getAllRetrait() {
        return retraitService.getAllRetrait();
    }

    @GetMapping("/{id}")
    public Retrait getRetraitById(@PathVariable int id) {
        return retraitService.getRetraitById(id);
    }

    @PostMapping
    public Retrait addRetrait(@RequestBody Retrait retrait) {
        return retraitService.saveRetrait(retrait);
    }

    @DeleteMapping("{id}")
    public void deleteRetrait(@PathVariable int id) {
        retraitService.deleteRetraitById(id);
    }
}
