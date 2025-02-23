package fr.projet.declarationfrais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.declarationfrais.model.Restauration;

@Repository
public interface RestaurationRepository extends JpaRepository<Restauration, Long> {
  
}
