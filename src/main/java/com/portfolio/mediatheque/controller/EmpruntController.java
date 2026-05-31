package com.portfolio.mediatheque.controller;

import com.portfolio.mediatheque.model.Emprunt;
import com.portfolio.mediatheque.repository.EmpruntRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {
    
    private final EmpruntRepository empruntRepository;

    public EmpruntController(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }
    

    // 1. Lister tous les emprunts (Historique)
    @GetMapping
    public List<Emprunt> getAllEmprunts(){
        return empruntRepository.findAll();
    }

    // 2. Créer un nouvel Emprunt
    @PostMapping
    public Emprunt createEmprunt(@RequestBody Emprunt nouvelEmprunt) {
        
        // Étape A : On vérifie si le livre est déjà emprunté (dateRetour = null)
        boolean estDejaEmprunte = empruntRepository.existsByLivreIdAndDateRetourIsNull(nouvelEmprunt.getLivre().getId());
        
        if (estDejaEmprunte) {
            // Étape B : Si oui, on bloque tout et on renvoie une erreur 409 (Conflict)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Action impossible : Ce livre est déjà emprunté et n'a pas été rendu.");
        }

        // Étape C : Sinon, on continue normalement
        nouvelEmprunt.setDateEmprunt(LocalDate.now());
        return empruntRepository.save(nouvelEmprunt);
    }

    // 3. Signaler le retour d'un livre (Mise à jour de l'emprunt)
    @PutMapping("/{id}/retour")
    public Emprunt retournerEmprunt(@PathVariable Long id){
        // On cherche l'Emprunt dans la base de données
        Emprunt empruntExistant=empruntRepository.findById(id).orElseThrow();

        // On lui ajoute la date de retour d'aujourd'hui
        empruntExistant.setDateRetour(LocalDate.now());

        // Sauvgarde
        return empruntRepository.save(empruntExistant);
    }

}
