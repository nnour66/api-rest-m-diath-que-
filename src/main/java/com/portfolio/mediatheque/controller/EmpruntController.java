package com.portfolio.mediatheque.controller;

import com.portfolio.mediatheque.model.Emprunt;
import com.portfolio.mediatheque.repository.EmpruntRepository;
import org.springframework.web.bind.annotation.*;

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
    public Emprunt createEmprunt(@RequestBody Emprunt nouvEmprunt){
        // l'API ajoute auto la date à jour
        nouvEmprunt.setDateEmprunt(LocalDate.now());
        return empruntRepository.save(nouvEmprunt);
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
