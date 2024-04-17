package org.enchere.backend.service;

import org.enchere.backend.model.Enchere;

import java.util.List;

// EnchereService.java
public interface EnchereService {
    List<Enchere> getAllEncheres();

    Enchere saveEnchere(Enchere enchere);
}
