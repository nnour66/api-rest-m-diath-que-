package com.portfolio.mediatheque.repository;

import com.portfolio.mediatheque.model.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//Maintenant, on donne à Spring le moyen de faire des requêtes SQL sur cette nouvelle table.
@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
}