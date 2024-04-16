package org.enchere.backend.controller;

import org.enchere.backend.model.Enchere;
import org.enchere.backend.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/encheres")
public class EnchereController {

    @Autowired
    private EnchereService enchereService;

    @GetMapping
    public List<Enchere> getAllEncheres() {
        return enchereService.getAllEncheres();
    }
}  