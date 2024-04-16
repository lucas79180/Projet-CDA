package org.enchere.backend.service;

import jakarta.transaction.Transactional;
import org.enchere.backend.Repository.RetraitRepository;
import org.enchere.backend.model.Retrait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class RetraitServiceImpl implements RetraitService {

    private final RetraitRepository retraitRepository;

    @Autowired
    public RetraitServiceImpl(RetraitRepository retraitRepository) {
        this.retraitRepository = retraitRepository;
    }

    @Override
    public List<Retrait> getAllRetrait() {
        return retraitRepository.findAll();
    }

    @Override
    public Retrait saveRetrait(Retrait retrait) {
        return retraitRepository.save(retrait);
    }

    @Override
    public Retrait getRetraitById(Integer id) {
        return retraitRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRetraitById(Integer id) {
        retraitRepository.deleteById(id);
    }
}
