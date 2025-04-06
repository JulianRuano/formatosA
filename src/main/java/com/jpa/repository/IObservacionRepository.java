package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.models.Observacion;

public interface IObservacionRepository extends JpaRepository<Observacion, Integer> {
    
}
