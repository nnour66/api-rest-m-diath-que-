package com.portfolio.mediatheque.repository;

import com.portfolio.mediatheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    
    // La méthode magique générée par Spring !
    // Dis-moi par un simple Oui ou Non (true/false) si le livre numéro X est
    // actuellement en dehors de la médiathèque.
    /**
         * SELECT EXISTS (
            SELECT 1 
            FROM emprunt 
            WHERE livre_id = 1 
            AND date_retour IS NULL
            );
     */
    boolean existsByLivreIdAndDateRetourIsNull(Long livreId);
    
}