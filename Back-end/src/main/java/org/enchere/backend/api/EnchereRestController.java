package org.enchere.backend.api;

import org.enchere.backend.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.enchere.backend.model.Enchere;


import java.util.List;

@RestController
@RequestMapping("/api/encheres")
public class EnchereRestController {

    @Autowired
    private EnchereService enchereService;

    // Endpoint pour récupérer toutes les enchères
    @GetMapping
    public ResponseEntity<List<Enchere>> getAllEncheres() {
        List<Enchere> encheres = enchereService.getAllEncheres();
        return new ResponseEntity<>(encheres, HttpStatus.OK);
    }

}
