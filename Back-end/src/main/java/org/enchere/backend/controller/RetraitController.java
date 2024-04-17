package org.enchere.backend.controller;

import org.enchere.backend.model.Retrait;
import org.enchere.backend.service.ArticleVenduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retrait")
public class RetraitController {

    private final ArticleVenduService retraitService;

    @Autowired
    public RetraitController(ArticleVenduService retraitService) {
        this.retraitService = retraitService;
    }

    @PostMapping
    public Retrait saveRetrait(@RequestBody Retrait retrait) {
        return retraitService.saveRetrait(retrait);
    }

    @GetMapping("/{id}")
    public Retrait getRetraitById(@PathVariable Integer id) {
        return retraitService.getRetraitById(id);
    }

}
