package fr.projet.declarationfrais.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "prenom", nullable = false, length = 20)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 20)
    private String nom;

    @Column(nullable = false)
    private int typeUtilisateur;
}
