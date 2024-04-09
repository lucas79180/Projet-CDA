package org.enchere.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "UTILISATEURS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_utilisateur")
    private Long id;
    private String pseudo;
    private String nom;
    private String prenom;
    private String telephone;
    private String code_postal;
    private String email;
    private String rue;
    private String ville;
    private String mot_de_passe;
    private int credit = 0;
    private boolean administrateur = false;

    public User(String pseudo, String nom, String prenom, String telephone, String code_postal, String email, String rue, String ville, String mot_de_passe) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.code_postal = code_postal;
        this.email = email;
        this.rue = rue;
        this.ville = ville;
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return "User : " +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", email='" + email + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }
}
