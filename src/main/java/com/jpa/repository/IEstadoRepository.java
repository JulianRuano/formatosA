package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.models.Estado;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
    
}
