package org.enchere.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "CATEGORIES")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_categorie")
    private Integer noCategorie;
    private String libelle;

   // @OneToMany(mappedBy = "categorie")
    //private List<ArticleVendu> ArticleVendu;

    public Categorie(Integer noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie() {

    }
}
