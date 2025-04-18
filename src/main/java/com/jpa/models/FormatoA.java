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
@Table(name = "formatosA")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FormatoA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String objetivoGeneral;

    @Column(nullable = false, unique = true)
    private String titulo;
    
    @Column(nullable = false)
    private String objetivosEspecificos;

    @OneToOne(mappedBy = "objFormato", cascade = {CascadeType.PERSIST}, optional = false)
    private Estado estado;

    @OneToMany(mappedBy = "objFormato", fetch = FetchType.LAZY)
    private List<Evaluacion> evaluacion;

    @ManyToOne
    @JoinColumn(name = "idDocente", nullable = false)
    private Docente objDocente;

}
