package com.portfolio.mediatheque.controller;

import com.portfolio.mediatheque.repository.LivreRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.portfolio.mediatheque.model.Livre;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;


/**
 * @RestController cette notation dit à SPRING 
 * cette classe va recevoir des requetes webet tout 
 * ce qu'elle renvoie doit etre automatiquement tranformé
 * en texte JSON.
 * @RequestMapping ("api/livres") définit la route de base
 */


@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreRepository livreRepository;

    // C'est l'injection de dépendance !
    public LivreController(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }


    /**
     * @GetMapping Indique que si on reçoit une requete HTTP de type 
     * GET sur l'URL de base (/api/livres), c'est cette méthode qui doit s'exécuter.
     * @findAll La méthode magique 
     * @return la liste d'objets Java
     */
    @GetMapping
    public List<Livre> getAllLivres(){
        return livreRepository.findAll();
    }


    /**
     * @PostMapping Indique que cette méthode s'active sur une 
     * requete HTTP de type POST sur /api/livres.
     * @RequestBody dit à SPRING : Prends le texte JSON qui se trouve dans 
     * le corps de la requete HTTP, et convertit-le automatiquement en un 
     * objet de type Livre.
     * @param nouveaLivre 
     * @return Insertion dans la base de données.
     */
    @PostMapping
    public Livre creatLivre(@Valid @RequestBody Livre nouveaLivre){
        return livreRepository.save(nouveaLivre);
    }


    /**
     * @DeleteMapping ("/{id}") Indique à Spring Boot que cette 
     * méthode doit être appelée uniquement si on reçoit une requête
     * HTTP de type DELETE. l'URL contient une valeur supplémentaire 
     * à la fin (par exemple /api/livres/1). Les accolades {id} signifient 
     * que cette partie est dynamique.
     * @PathVariable C'est le lien direct avec le {id} du dessus. 
     * Ça dit à Spring : "Prends le chiffre qui se trouve à la fin de l'URL, 
     * et mets-le dans la variable Java id".
     * C'est le lien direct avec le {id} du dessus. Ça dit à Spring : "Prends 
     * le chiffre qui se trouve à la fin de l'URL, et mets-le dans la variable Java id".
     */
    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id){
        livreRepository.deleteById(id);
    }


    /**
     * @PutMapping Cette méthode s'applique sur une requette HTTP PUT en pointant sur l'id.
     * @param id ID spécifique.
     * @PathVariable et @RequestBody : On combine les deux, pour attraper
     * l'ID dans l'URL et on attrape le texte JSON pour le transformer en objet 
     * detailsLivre.
     * .orElseThrow just ou cas ou l'URL n'existe pas ça retourne une erreur 
     * au lieu de planter le programme.
     * @livreRepository.save c'est la mm avec la méthode POST, mais il fait juste la mise à jour. 
     */
    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @Valid @RequestBody Livre detailsLivre) {
       // Étape A : On cherche le livre existant dans la base de données
        Livre livreExistant = livreRepository.findById(id).orElseThrow();
        
        // Étape B : On met à jour ses informations avec les nouvelles données
        livreExistant.setTitre(detailsLivre.getTitre());
        livreExistant.setAuteur(detailsLivre.getAuteur());
        livreExistant.setIsbn(detailsLivre.getIsbn());

        // Étape C : On sauvegarde et on renvoie le livre mis à jour
        return livreRepository.save(livreExistant);
    }


}


