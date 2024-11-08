package com.example.TP3_SpringBoot.Service;

import com.example.TP3_SpringBoot.Repository.RoleRepository;
import com.example.TP3_SpringBoot.Repository.UtilisateurImageRepository;
import com.example.TP3_SpringBoot.Repository.UtilisateurRepository;
import com.example.TP3_SpringBoot.Role;
import com.example.TP3_SpringBoot.Utilisateur;
import com.example.TP3_SpringBoot.UtilisateurImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UtilisateurImageRepository utilisateurImageRepository;

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur creerUtilisateur(Utilisateur utilisateur, Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        utilisateur.setRole(role);
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur ajouterImageAUtilisateur(Long utilisateurId, UtilisateurImage utilisateurImage) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        utilisateur.setUtilisateurImage(utilisateurImage);
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> trouverUtilisateursParRole(String roleName) {
        Role role = roleRepository.findByNom(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return role.getUtilisateurs();
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public void supprimerRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    public void supprimerImage(Long utilisateurId, Long imageId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        if (utilisateur.getUtilisateurImage() != null && utilisateur.getUtilisateurImage().getId().equals(imageId)) {
            utilisateur.setUtilisateurImage(null);
            utilisateurRepository.save(utilisateur);
            utilisateurImageRepository.deleteById(imageId);
        }
    }
}