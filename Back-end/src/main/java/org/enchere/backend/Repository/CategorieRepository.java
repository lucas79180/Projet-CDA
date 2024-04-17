package org.enchere.backend.Repository;

import org.enchere.backend.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}
