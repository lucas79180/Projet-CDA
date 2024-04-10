package org.enchere.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RETRAITS")
public class Retrait {

    @Id
    @OneToOne
    private ArticleVendu article;

    private String rue;
    private String code_postal;
    private String ville;

    @OneToOne
    private ArticleVendu articleVendu;
}
