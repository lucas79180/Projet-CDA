package org.enchere.backend.Repository;

import org.enchere.backend.model.Enchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// EnchereRepository.java
@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
