package com.portfolio.mediatheque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;


/**
 * @ManyToOne ça dit à Hibernate l'historique du livre peut contenir plusieurs emprunts
 * mais aussi un membre peut emprunter plusieurs livres.
 * @localDate Pour gérer les dates.
 * @JoinColumn Hibernate va prendre ton objet Java Livre, extraire secrètement son ID, 
 * et créer une vraie colonne dans SQLite qu'il va nommer livre_id pour y ranger ce numéro. 
 * À l'inverse, quand tu liras la base de données, Hibernate prendra ce numéro et ira chercher 
 * le livre correspondant pour reconstituer l'objet Java.
 * La sécurité (nullable = false) : C'est l'équivalent du NOT NULL en SQL. Ça dit à la base de données : 
 * "Il est physiquement impossible d'enregistrer un emprunt dans cette table s'il n'y a pas de livre associé". 
 * Cela protège ta base des données incomplètes ou fantômes.
 */
@Entity
public class Emprunt {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEmprunt;

    private LocalDate dateRetour; // Sera null tant que le livre n'est pas rendu

    // Relation avec le Livre
    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;

    // Relation avec le Membre
    @ManyToOne
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;

    public Emprunt() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(LocalDate dateEmprunt) { this.dateEmprunt = dateEmprunt; }

    public LocalDate getDateRetour() { return dateRetour; }
    public void setDateRetour(LocalDate dateRetour) { this.dateRetour = dateRetour; }

    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }

    public Membre getMembre() { return membre; }
    public void setMembre(Membre membre) { this.membre = membre; }
}
