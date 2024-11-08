package com.example.TP3_SpringBoot.controllers;

import com.example.TP3_SpringBoot.Service.UtilisateurService;
import com.example.TP3_SpringBoot.Utilisateur;
import com.example.TP3_SpringBoot.UtilisateurImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @PostMapping
    public Utilisateur creerUtilisateur(@RequestBody Utilisateur utilisateur, @RequestParam Long roleId) {
        return utilisateurService.creerUtilisateur(utilisateur, roleId);
    }

    @GetMapping("/{id}")
    public Optional<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.findById(id);
    }

    @PutMapping("/{utilisateurId}/role/{roleId}")
    public Utilisateur assignerRole(@PathVariable Long utilisateurId, @PathVariable Long roleId) {
        return utilisateurService.creerUtilisateur(utilisateurService.findById(utilisateurId).orElse(null), roleId);
    }

    @PostMapping("/{utilisateurId}/image")
    public Utilisateur ajouterImage(@PathVariable Long utilisateurId, @RequestBody UtilisateurImage utilisateurImage) {
        return utilisateurService.ajouterImageAUtilisateur(utilisateurId, utilisateurImage);
    }

    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }

    @DeleteMapping("/role/{roleId}")
    public void supprimerRole(@PathVariable Long roleId) {
        utilisateurService.supprimerRole(roleId);
    }

    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public void supprimerImage(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        utilisateurService.supprimerImage(utilisateurId, imageId);
    }
}