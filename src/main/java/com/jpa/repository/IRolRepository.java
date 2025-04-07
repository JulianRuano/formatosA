package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.models.Rol;

public interface IRolRepository extends JpaRepository<Rol, Integer> {
    
}
