package fr.projet.declarationfrais.aspect;

import java.math.BigDecimal;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import fr.projet.declarationfrais.model.Declaration;
import fr.projet.declarationfrais.model.Restauration;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* fr.projet.declarationfrais.controllers..*(..)) && !execution(* javax.persistence.EntityManager.*(..))")
    public void logPointcut() {
    }

    @Before("logPointcut()")
    public void methodeAppelee(JoinPoint joinPoint) {
        System.out.println("\u001B[33m Méthode appelée: " + joinPoint.getSignature().toShortString()
                + " \033[0m");
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void methodeReussie(JoinPoint joinPoint, Object result) {
        System.out.println("\033[32m Méthode " + joinPoint.getSignature().toShortString()
                + " terminée avec succès.\033[0m");
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void methodeEchouee(JoinPoint joinPoint, Throwable exception) {
        System.out.println("\033[31m Erreur lors de l'exécution de la méthode "
                + joinPoint.getSignature().toShortString() + " : " + exception.getMessage()
                + "\033[0m");
    }

    @After("execution(* fr.projet.declarationfrais.repository.DeclarationRepository.save(..))  && args(entity)")
    public void insertDeclaration(Object entity) {
        if (entity instanceof Declaration newEntity) {

            System.out.println();
            System.out.println(
                    "\u001B[35m BDD: Une nouvelle déclaration a été ajouté à la base de données  \u001B[0m");

            if (newEntity.getMontant_hebergement().isEmpty()) {
                System.out.println("\u001B[35m Aucun frais d'hébergement. \u001B[0m");
            } else {
                System.out.println("\u001B[35m Montant Hébergement: \u001B[0m" + newEntity.getMontant_hebergement());
            }

            if (newEntity.getMontant_transport() != null) {
                System.out.println("\u001B[35m Montant Transport: \u001B[0m" + newEntity.getMontant_transport());
            }
            
        }
    }

    @After("execution(* fr.projet.declarationfrais.repository.RestaurationRepository.save(..)) && args(entity)")
    public void insertRestauration(Object entity) {
        if (entity instanceof Restauration newEntity) {

            if (newEntity.getMontant() == null || newEntity.getMontant().compareTo(BigDecimal.ZERO) == 0) {
                System.out.println("\u001B[35m Aucun frais de restauration déclaré. \u001B[0m");
            } else {
                System.out.println("\n\u001B[35m BDD: Une nouvelle restauration a été ajoutée \u001B[0m");
                System.out.println("\u001B[35m Type de repas: \u001B[0m" + newEntity.getTypeRepas());
                System.out.println("\u001B[35m Montant: \u001B[0m" + newEntity.getMontant() + " €");
                System.out.println("\u001B[35m Justificatif: \u001B[0m" + newEntity.getFichierJustificatif());
            }
        }
    }

    public void afficherFrais(Declaration declaration) {
        System.out.println();
        System.out.println("\u001B[35m Total des frais: \u001B[0m" + declaration.getTotalFrais());
        System.out.println();
    }

    private final ThreadLocal<String> refDossier = new ThreadLocal<>();
    private final ThreadLocal<String> user = new ThreadLocal<>();

    @Before("execution(* javax.persistence.EntityManager.merge(..)) && args(entity)")
    public void beforeUpdate(Object entity) {
        if (entity instanceof Declaration updatedEntity) {
            user.set(updatedEntity.getUser());
        }
    }

    @After("execution(* javax.persistence.EntityManager.merge(..)) && args(entity)")
    public void afterUpdate(Object entity) {
        if (entity instanceof Declaration updatedEntity) {
            String newStatut = updatedEntity.getStatut();
            String oldStatut = updatedEntity.getOldStatut();

            System.out.println();
            System.out.println(
                    "\u001B[35m BDD: Un enregistrement a été modifié dans la base de données \u001B[0m");
            System.out.println("\u001B[35m Reférence de la déclaration: \u001B[0m" + refDossier.get());
            System.out.println("\u001B[35m Email de l'user concerné: \u001B[0m" + user.get());
            System.out.println("\u001B[35m Ancien statut: \u001B[0m" + oldStatut);
            System.out.println("\u001B[35m Nouveau statut: \u001B[0m" + newStatut);
            System.out.println();

            refDossier.remove();
            user.remove();
        }
    }

    @AfterThrowing(pointcut = "execution(* javax.persistence.EntityManager.persist(..)) || execution(* javax.persistence.EntityManager.remove(..)) || execution(* javax.persistence.EntityManager.merge(..))", throwing = "exception")
    public void bddEchec(JoinPoint joinPoint, Throwable exception) {
        System.out.println("\u001B[35m [Erreur lors de l'exécution de la méthode "
                + joinPoint.getSignature().toShortString() + " : " + exception.getMessage()
                + " \u001B[0m");
    }

}