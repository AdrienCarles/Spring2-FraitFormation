package fr.projet.declarationfrais.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "frais_restauration")
public class Restauration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant", nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "type_repas", nullable = false, length = 50)
    private String typeRepas;

    @Column(name = "justificatif", nullable = false)
    private String fichierJustificatif;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declaration_id", nullable = false)
    @JsonIgnore
    private Declaration declaration;
}
