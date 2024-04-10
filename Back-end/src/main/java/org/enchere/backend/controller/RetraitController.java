package org.enchere.backend.controller;

import org.enchere.backend.model.Retrait;
import org.enchere.backend.service.RetraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retrait")
public class RetraitController {

    private final RetraitService retraitService;

    @Autowired
    public RetraitController(RetraitService retraitService) {
        this.retraitService = retraitService;
    }

    @PostMapping
    public Retrait saveRetrait(@RequestBody Retrait retrait) {
        return retraitService.saveRetrait(retrait);
    }

    @GetMapping("/{id}")
    public Retrait getRetraitById(@PathVariable int id) {
        return retraitService.getRetraitById(id);
    }

}
