package fr.projet.declarationfrais.model;

import lombok.Data;
import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "declaration")
public class Declaration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_transport", nullable = false)
    private String typeTransport;

    @Column(name = "lieu_depart", nullable = false)
    private String lieuDepart;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String lieu;

    @Column(nullable = false)
    private String intitule;

    @Column(nullable = false)
    private String type_hebergement;

    @Column(nullable = false)
    private String montant_hebergement;

    @Column(nullable = false)
    private String montant_transport;

    @Column(nullable = false)
    private String coordonneesbancaires;

    @Column(nullable = false)
    private String statut;

    @Column(name = "nom_fichier_transport", nullable = false)
    private String nom_fichier_transport;

    @Column(name = "nom_fichier_hebergement", nullable = false)
    private String nom_fichier_hebergement;

    @Column(name = "user", nullable = false)
    private String user;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL)
    private List<Restauration> fraisRestoInfos = new ArrayList<>();

    public String getTotalFrais() {
        BigDecimal total = BigDecimal.ZERO;

        if (montant_transport != null && !montant_transport.isEmpty()) {
            total = total.add(new BigDecimal(montant_transport));
        }

        if (montant_hebergement != null && !montant_hebergement.isEmpty()) {
            total = total.add(new BigDecimal(montant_hebergement));
        }

        if (fraisRestoInfos != null) {
            for (Restauration restauration : fraisRestoInfos) {
                if (restauration.getMontant() != null && restauration.getMontant().compareTo(BigDecimal.ZERO) > 0) {
                    total = total.add(restauration.getMontant());
                }
            }
        }

        return total.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
    private String oldStatut;

    @PreUpdate
    public void preUpdate() {
        oldStatut = this.statut;
    }
}
