package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.models.Docente;

public interface IDocenteRepository extends JpaRepository<Docente, Integer> {
    
}
