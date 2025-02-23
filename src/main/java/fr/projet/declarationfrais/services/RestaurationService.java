package fr.projet.declarationfrais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.declarationfrais.model.Restauration;
import fr.projet.declarationfrais.repository.RestaurationRepository;

@Service
public class RestaurationService {

    private final RestaurationRepository restaurationRepository;

    @Autowired
    public RestaurationService(RestaurationRepository restaurationRepository) {
        this.restaurationRepository = restaurationRepository;
    }

    public void sauvegarder(Restauration restauration) {
        restaurationRepository.save(restauration);
    }
}
