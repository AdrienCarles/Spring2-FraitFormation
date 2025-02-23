package fr.projet.declarationfrais.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.webflow.execution.RequestContext;

import fr.projet.declarationfrais.model.Declaration;
import fr.projet.declarationfrais.repository.DeclarationRepository;

@Service
public class DeclarationService {

    private final DeclarationRepository declarationRepository;

    @Autowired
    public DeclarationService(DeclarationRepository declarationRepository) {
        this.declarationRepository = declarationRepository;
    }

    public List<Declaration> getAllDeclarations() {
        return declarationRepository.findAll();
    }

    public List<Declaration> getDeclarationsByStatut(String statut) {
        List<Declaration> allDeclarations = declarationRepository.findAll();

        List<Declaration> declarationsByStatut = new ArrayList<>();
        for (Declaration declaration : allDeclarations) {
            if (declaration.getStatut().equalsIgnoreCase(statut)) {
                declarationsByStatut.add(declaration);
            }
        }

        return declarationsByStatut;
    }

    public Declaration getDeclarationsById(Long id) {
        return declarationRepository.findById(id).orElse(null);
    }

    public List<Declaration> getAllDeclarationsByConnectedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String connectedUserId = userDetails.getUsername();
        return declarationRepository.findByUser(connectedUserId);
    }

    public List<Declaration> getDeclarationsByStatutAndUserId(String statut, String userId) {
        List<Declaration> allDeclarations = declarationRepository.findByUser(userId);
    
        List<Declaration> declarationsByStatut = new ArrayList<>();
        for (Declaration declaration : allDeclarations) {
            if (declaration.getStatut().equalsIgnoreCase(statut)) {
                declarationsByStatut.add(declaration);
            }
        }
    
        return declarationsByStatut;
    }
    
}
