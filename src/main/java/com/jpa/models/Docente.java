package com.jpa.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 100, unique = true)
    private String correo;

    @Column(nullable = false, length = 50, unique = true)
    private String nombreGrupo;

    @ManyToMany(mappedBy = "objDocente", fetch = FetchType.EAGER)
    private List<Observacion> objObservacion;

    @OneToMany( mappedBy = "objDocente", fetch = FetchType.EAGER)
    private List<FormatoA> objFormatoA;

    @OneToMany( mappedBy = "objDocente")
    private List<Historico> objHistorico;

}
