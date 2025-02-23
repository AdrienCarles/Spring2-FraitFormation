package fr.projet.declarationfrais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;
import fr.projet.declarationfrais.model.Restauration;
import fr.projet.declarationfrais.model.Declaration;
import fr.projet.declarationfrais.repository.RestaurationRepository;
import fr.projet.declarationfrais.repository.DeclarationRepository;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class RestaurationController {

    @Autowired
    private RestaurationRepository restaurationRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    public Restauration sauvegarderRestauration(RequestContext flowRequestContext, Long declarationId, String typeRepas, String montantKey, String factureKey) {
        try {
            String montantStr = (String) flowRequestContext.getFlowScope().get(montantKey);
            String fichierJustificatif = (String) flowRequestContext.getFlowScope().get(factureKey);

            if (montantStr != null && !montantStr.isEmpty()) {
                BigDecimal montant = new BigDecimal(montantStr);


                Optional<Declaration> declarationOpt = declarationRepository.findById(declarationId);
                if (declarationOpt.isPresent()) {
                    Restauration restauration = new Restauration();
                    restauration.setMontant(montant);
                    restauration.setTypeRepas(typeRepas);
                    restauration.setFichierJustificatif(fichierJustificatif);
                    restauration.setDeclaration(declarationOpt.get());

                    return restaurationRepository.save(restauration);
                } else {
                    System.out.println("Erreur : Déclaration introuvable avec ID " + declarationId);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la sauvegarde de la restauration : " + e.getMessage());
        }
        return null;
    }

    public Restauration sauvegarderRestaurationPetitDejeuner(RequestContext flowRequestContext, Long declarationId) {
        return sauvegarderRestauration(flowRequestContext, declarationId, "Petit-déjeuner", "montant_petit_dejeuner", "facture_petit_dejeuner");
    }

    public Restauration sauvegarderRestaurationDejeuner(RequestContext flowRequestContext, Long declarationId) {
        return sauvegarderRestauration(flowRequestContext, declarationId, "Déjeuner", "montant_dejeuner", "facture_dejeuner");
    }

    public Restauration sauvegarderRestaurationDiner(RequestContext flowRequestContext, Long declarationId) {
        return sauvegarderRestauration(flowRequestContext, declarationId, "Dîner", "montant_diner", "facture_diner");
    }
}
