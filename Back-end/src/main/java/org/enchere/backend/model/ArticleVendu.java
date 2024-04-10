package org.enchere.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ARTICLES_VENDUS")
public class ArticleVendu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_article")
    private int noArticle;
    @Column(name = "nom_article")
    private String nomArticle;
    private String description;
    @Column(name = "date_debut_encheres")
    private LocalDateTime dateDebutEncheres;
    @Column(name = "date_fin_encheres")
    private LocalDateTime dateFinEncheres;
    @Column(name = "prix_initial")
    private String miseAPrix;
    @Column(name = "prix_vente")
    private int prixVente;
    // etatVente t'est présente que dans l'objet et pas dans la bdd
    private boolean etatVente;

    @ManyToOne
    private Categorie categorie;

    @OneToOne(mappedBy = "articleVendu")
    private Retrait retrait;

    @OneToMany
    private List<Enchere> encheres;

    public ArticleVendu() {
    }

    public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, String miseAPrix, int prixVente) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
    }

}
