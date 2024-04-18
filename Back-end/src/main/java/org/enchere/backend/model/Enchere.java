package org.enchere.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "ENCHERES")
public class Enchere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_enchere")
    private Integer noEnchere;
    @Column(name = "date_enchere")
    private LocalDateTime dateEnchere;
    @Column(name = "montant_enchere")
    private Integer montantEnchere;


    @ManyToOne
    @JoinColumn(name = "no_utilisateur")
    private User utilisateur;


    @ManyToOne
    @JoinColumn(name = "no_article")
    private ArticleVendu articleVendu;

    public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, User utilisateur, ArticleVendu articleVendu) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.articleVendu = articleVendu;
    }
}
