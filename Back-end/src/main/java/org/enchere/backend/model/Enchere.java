package org.enchere.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ENCHERES")
public class Enchere {

    @Column(name = "date_enchere")
    private LocalDateTime dateEnchere;
    @Column(name = "montant_enchere")
    private int montantEnchere;

    @ManyToOne
    private User utilisateur;

    @ManyToOne
    private ArticleVendu articleVendu;

}
