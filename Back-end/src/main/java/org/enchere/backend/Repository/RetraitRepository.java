package org.enchere.backend.Repository;

import org.enchere.backend.model.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RetraitRepository extends JpaRepository<Retrait, Integer> {

    @Query(value = "SELECT RETRAITS.no_retrait, RETRAITS.no_article, RETRAITS.rue, RETRAITS.code_postal, RETRAITS.ville FROM RETRAITS WHERE no_article = :articleId", nativeQuery = true)
    Retrait findByIdArticle(@Param("articleId") Integer articleId);

}
