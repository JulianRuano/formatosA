package com.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private boolean estado;
    
    @Column(nullable = false)
    private Date fechaInicio;


    @ManyToOne
    @JoinColumn( name =  "idDocente", referencedColumnName = "id" )
    private Docente objDocente;

    @ManyToOne
    @JoinColumn( name =  "idRol", referencedColumnName = "id" )
    private Rol objRol;

}
