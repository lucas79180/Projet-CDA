package org.enchere.backend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RETRAITS")
public class Retrait {

    // Attributs
    @Id
    @Column(name = "no_retrait")
    private Integer noRetrait;
    private String rue;
    private String code_postal;
    private String ville;

    @OneToOne
    @JoinColumn(name = "no_article")
    private ArticleVendu articleVendu;

    // Constructeur
    public Retrait() {
    }

    public Retrait(Integer noRetrait, String rue, String code_postal, String ville, ArticleVendu article, ArticleVendu articleVendu) {
        this.noRetrait = noRetrait;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.articleVendu = articleVendu;
    }

    public Retrait(String rue, String code_postal, String ville, ArticleVendu article, ArticleVendu articleVendu) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.articleVendu = articleVendu;
    }
}
