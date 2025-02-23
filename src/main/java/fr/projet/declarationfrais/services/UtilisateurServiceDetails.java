package fr.projet.declarationfrais.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.projet.declarationfrais.model.Utilisateur;
import fr.projet.declarationfrais.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceDetails implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found");
        }
   
        return org.springframework.security.core.userdetails.User
                .withUsername(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .roles(String.valueOf(utilisateur.getTypeUtilisateur()))
                .build();
    }
}