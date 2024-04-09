package org.enchere.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "RETRAITS")
public class Retrait {

    @Id
    @ManyToOne
    private ArticleVendu article;

    private String rue;
    private String code_postal;
    private String ville;


}
