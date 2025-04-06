package com.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa.models.Historico;

public interface IHistoricoRepository extends JpaRepository<Historico, Integer> {

}
