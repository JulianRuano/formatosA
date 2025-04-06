package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.models.Evaluacion;

public interface IEvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
    
}
