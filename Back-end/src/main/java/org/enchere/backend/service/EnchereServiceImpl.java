package org.enchere.backend.service;

import org.enchere.backend.Repository.EnchereRepository;
import org.enchere.backend.model.Enchere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {

    @Autowired
    private EnchereRepository enchereRepository;

    @Override
    public List<Enchere> getAllEncheres() {
        return enchereRepository.findAll();
    }

    @Override
    public Enchere saveEnchere(Enchere enchere) {
        return enchereRepository.save(enchere);
    }
}
