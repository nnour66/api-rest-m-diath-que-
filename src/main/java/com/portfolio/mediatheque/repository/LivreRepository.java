package com.portfolio.mediatheque.repository;

import com.portfolio.mediatheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @Interface en java c'est comme un contract, et ici 
 * on doit mm pas besoin d'écrire le code.
 * @extendsJpaRepository<Livre, Long> en héritant de ça Spring
 * va générer tout le code SQL. On aura automatiquement accés à des méthodes comme 
 * save() (pour insérer), findAll() (pour récupérer tous les livres).
 * @param Livre pour savoir qu'elle entité à gérer.
 * @param Long le ID(clé primaire)
 * @Repository indique àSPRING que ce composant sert à gérer
 * la base de données.
 */

@Repository
public interface 
LivreRepository extends JpaRepository<Livre, Long> {}
