package com.portfolio.mediatheque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * @Entity c'est une annotation magique qui dit à SPRING/HIBERNATE
 * que cette classe correspond à une table de données.
 * @Id indique que id est la clé primaire de notre table.
 * @GeneratedValue est l'équivalent de l'AUTO INCREMENT pour les id.
 * @JPA (Java Presistence API) transforme une classe java en une base
 * données.
 */

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;

    // 1. Un constructeur vide est OBLIGATOIRE pour qu'Hibernate puisse fonctionner
    public Livre() {}

    // 2. Un constructeur avec paramètres pour toi, quand tu voudras créer des livres dans le code
    public Livre(String titre, String auteur, String isbn){
        this.titre=titre;
        this.auteur=auteur;
        this.isbn=isbn;
    }

    // 3. Les Getters et Setters (pour accéder aux attributs "private" depuis l'extérieur)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getTitre(){
        return titre;
    }

    public void setTitre(String titre){
        this.titre=titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
