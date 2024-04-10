package org.enchere.backend.service;

import org.enchere.backend.Repository.RetraitRepository;
import org.enchere.backend.model.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetraitServiceImpl implements RetraitService {

    private final RetraitRepository retraitRepository;

    @Autowired
    public RetraitServiceImpl(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }

    @Override
    public Retrait saveRetrait(Retrait retrait) {
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait getRetraitById(int id) {
        return retraitRepository.findById(id).orElse(null);
    }
}
