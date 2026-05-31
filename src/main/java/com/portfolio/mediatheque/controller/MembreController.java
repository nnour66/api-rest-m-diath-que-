package com.portfolio.mediatheque.controller;


import com.portfolio.mediatheque.model.Membre;
import com.portfolio.mediatheque.repository.MembreRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/membres")
public class MembreController {


    private final MembreRepository membreRepository;

    public MembreController(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @GetMapping
    public List<Membre> getAllMembres() {
        return membreRepository.findAll();
    }

    @PostMapping
    public Membre createMembre(@Valid @RequestBody Membre nouveauMembre) {
        return membreRepository.save(nouveauMembre);
    }
}
