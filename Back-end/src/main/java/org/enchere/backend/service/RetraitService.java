package org.enchere.backend.service;

import org.enchere.backend.model.Retrait;


public interface RetraitService {

    Retrait saveRetrait(Retrait retrait);
    Retrait getRetraitById(int id);

}
