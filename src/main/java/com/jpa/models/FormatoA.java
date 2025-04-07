package com.jpa.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FormatoA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String objetivoGeneral;

    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false)
    private List<String> objetivosEspecificos;

    @OneToOne(mappedBy = "objFormato", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Estado estado;

    @OneToMany(mappedBy = "objFormato")
    private List<Evaluacion> evaluacion;

    @ManyToOne
    @JoinColumn(name = "idDocente", nullable = false)
    private Docente objDocente;

}
