package org.enchere.backend.service;

import org.enchere.backend.model.Retrait;

import java.util.List;


public interface RetraitService {

    List<Retrait> getAllRetrait();
    Retrait saveRetrait(Retrait retrait);
    Retrait getRetraitById(Integer id);
    void deleteRetraitById(Integer id);
}
