package com.example.TP3_SpringBoot.Repository;

import com.example.TP3_SpringBoot.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(String nom);
}