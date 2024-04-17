package org.enchere.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ARTICLES_VENDUS")
public class ArticleVendu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_article")
    private Integer noArticle;
    @Column(name = "nom_article")
    private String nomArticle;
    private String description;
    @Column(name = "date_debut_encheres")
    private LocalDateTime dateDebutEncheres;
    @Column(name = "date_fin_encheres")
    private LocalDateTime dateFinEncheres;
    @Column(name = "prix_initial")
    private Integer miseAPrix;
    @Column(name = "prix_vente")
    private Integer prixVente;
    // etatVente t'est présente que dans l'objet et pas dans la bdd
    //private boolean etatVente;
    @ManyToOne
    @JoinColumn(name = "no_categorie")
    private Categorie categorie;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "no_utilisateur", nullable = false)
    private User vendeur;


    public ArticleVendu() {
    }

    public ArticleVendu(Integer noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, Integer miseAPrix, Integer prixVente, boolean etatVente, Categorie categorie, Retrait retrait, List<Enchere> encheres, User vendeur) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.categorie = categorie;
        this.vendeur = vendeur;
    }

}
