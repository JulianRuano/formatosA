package com.jpa.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluaciones")
public class Evaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private String concepto;

    @Column(nullable = true)
    private Date fechaRegistroConcepto;

    @Column(nullable = true)
    private String nombreCoordinador;

    @OneToMany(mappedBy = "objEvaluacion")
    private List<Observacion> observacion;

    @ManyToOne
    @JoinColumn(name = "idFormato")
    private FormatoA objFormato;

}
