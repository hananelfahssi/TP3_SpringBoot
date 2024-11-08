package com.example.TP3_SpringBoot;


import jakarta.persistence.*;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private UtilisateurImage utilisateurImage;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UtilisateurImage getUtilisateurImage() {
        return utilisateurImage;
    }

    public void setUtilisateurImage(UtilisateurImage utilisateurImage) {
        this.utilisateurImage = utilisateurImage;
        if (utilisateurImage != null) {
            utilisateurImage.setUtilisateur(this);
        }
    }
}