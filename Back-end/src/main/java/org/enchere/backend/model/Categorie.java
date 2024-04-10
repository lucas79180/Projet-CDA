package org.enchere.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CATEGORIES")
public class Categorie {

    @Id
    @Column(name = "no_categorie")
    private long noCategorie;
    private String libelle;

    @OneToMany(mappedBy = "categorie")
    private List<ArticleVendu> ArticleVendu;

    public Categorie(long noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie() {

    }
}
