package com.portfolio.mediatheque.controller;

import com.portfolio.mediatheque.repository.LivreRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.portfolio.mediatheque.model.Livre;


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
    public Livre creatLivre(@RequestBody Livre nouveaLivre){
        return livreRepository.save(nouveaLivre);
    }
}


