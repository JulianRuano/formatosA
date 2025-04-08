package com.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "observaciones")
public class Observacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String observacion;
    
    @Column(nullable = false)
    private Date fechaRegistro;
    
    @ManyToOne
    @JoinColumn(name = "idEvaluacion", nullable = false)
    private Evaluacion objEvaluacion;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(
        name = "Observacion_Docente", 
        joinColumns = @JoinColumn(name = "idObservacion", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "idDocente", referencedColumnName = "id")
    )
    private List<Docente> objDocente;
}
