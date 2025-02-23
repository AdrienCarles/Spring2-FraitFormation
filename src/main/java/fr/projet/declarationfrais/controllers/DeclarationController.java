package fr.projet.declarationfrais.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.webflow.execution.RequestContext;

import fr.projet.declarationfrais.model.Declaration;
import fr.projet.declarationfrais.repository.DeclarationRepository;
import fr.projet.declarationfrais.services.DeclarationService;

@Controller
public class DeclarationController {

    @Autowired
    private DeclarationRepository DeclarationRepository;

    private final DeclarationService declarationService;

    @Autowired
    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    public Declaration sauvegarderDeclaration(RequestContext flowRequestContext) {
        String date = (String) flowRequestContext.getFlowScope().get("date");
        String lieu = (String) flowRequestContext.getFlowScope().get("lieu");
        String intitule = (String) flowRequestContext.getFlowScope().get("intitule");
        String type_transport = (String) flowRequestContext.getFlowScope().get("type_transport");
        String lieu_depart = (String) flowRequestContext.getFlowScope().get("lieu_depart");
        String facture_transport = (String) flowRequestContext.getFlowScope().get("facture_transport");
        String montant_transport = (String) flowRequestContext.getFlowScope().get("montant_transport");
        String type_hebergement = (String) flowRequestContext.getFlowScope().get("type_hebergement");
        String facture_hebergement = (String) flowRequestContext.getFlowScope().get("facture_hebergement");
        String montant_hebergement = (String) flowRequestContext.getFlowScope().get("montant_hebergement");
        String coordonneesbancaires = (String) flowRequestContext.getFlowScope().get("coordonneesbancaires");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String connectedUserId = userDetails.getUsername();

        Declaration declaration = new Declaration();
        declaration.setUser(connectedUserId);
        declaration.setStatut("en attente");
        declaration.setOldStatut("en attente");
        declaration.setDate(date);
        declaration.setLieu(lieu);
        declaration.setIntitule(intitule);
        declaration.setTypeTransport(type_transport);
        declaration.setLieuDepart(lieu_depart);
        declaration.setNom_fichier_transport(facture_transport);
        declaration.setMontant_transport(montant_transport);
        declaration.setType_hebergement(type_hebergement);
        declaration.setNom_fichier_hebergement(facture_hebergement);
        declaration.setMontant_hebergement(montant_hebergement);
        declaration.setCoordonneesbancaires(coordonneesbancaires);

        return  DeclarationRepository.save(declaration);
    }

    @GetMapping("/ListeDeclarations")
    public String getListeDeclarations(@RequestParam(value = "statut", required = false) String statut, Model model) {

        List<Declaration> declarations = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_1"))) {
            if (statut != null && !statut.isEmpty()) {
                declarations = declarationService.getDeclarationsByStatut(statut);
            } else {
                declarations = declarationService.getAllDeclarations();
            }
        }

        if (auth.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_0"))) {
            if (statut != null && !statut.isEmpty()) {
                UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                String connectedUserId = userDetails.getUsername();
                declarations = declarationService.getDeclarationsByStatutAndUserId(statut, connectedUserId);
            } else {
                declarations = declarationService.getAllDeclarationsByConnectedUser();
            }
        }

        model.addAttribute("listeDeclarations", declarations);
        model.addAttribute("selectedStatut", statut);
        return "ListeDeclarations";
    }

    @PostMapping("/updateStatut/{id}")
    public String updateStatut(@PathVariable Long id, @RequestParam("statut") String statut) {
        Declaration declaration = declarationService.getDeclarationsById(id);
        declaration.setStatut(statut);
        DeclarationRepository.save(declaration);

        String redirectURL = "/ListeDeclarations";

        if (statut.equalsIgnoreCase("Valide")) {
            redirectURL += "?statut=Valide";
        } else if (statut.equalsIgnoreCase("Invalide")) {
            redirectURL += "?statut=Invalide";
        } else if (statut.equalsIgnoreCase("En attente")) {
            redirectURL += "?statut=En%20attente";
        }

        return "redirect:" + redirectURL;
    }

}
