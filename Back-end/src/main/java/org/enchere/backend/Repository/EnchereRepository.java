package org.enchere.backend.Repository;

import org.enchere.backend.model.Enchere;
import org.enchere.backend.model.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// EnchereRepository.java
@Repository
public interface EnchereRepository extends JpaRepository<Enchere, Long> {

    @Query(value = "SELECT * FROM ENCHERES WHERE ENCHERES.no_article = :articleId ORDER BY ENCHERES.montant_enchere DESC", nativeQuery = true)
    List<Enchere> findByIdArticle(@Param("articleId") Integer articleId);

}
