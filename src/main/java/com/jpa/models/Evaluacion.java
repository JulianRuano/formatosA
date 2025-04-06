package com.jpa.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String concepto;

    @Column(nullable = true)
    private Date fechaRegistroConcepto;

    @Column(nullable = true)
    private String nombreCoornador;

    @OneToMany(mappedBy = "objEvaluacion")
    private List<Observacion> observacion;

    @ManyToOne
    @JoinColumn(name = "idFormato")
    private FormatoA objFormato;

}
