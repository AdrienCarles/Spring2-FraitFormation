package fr.projet.declarationfrais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.declarationfrais.model.Declaration;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    List<Declaration> findByUser(String userId);
}
