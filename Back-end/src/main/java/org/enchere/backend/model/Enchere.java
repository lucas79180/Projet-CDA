package org.enchere.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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

}
