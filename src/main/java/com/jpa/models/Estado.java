package com.jpa.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estados")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(nullable = false, length = 50)
    private String estadoActual;

    @Column(nullable = false)
    private Date fechaRegistro;

    @OneToOne
    @JoinColumn(name = "idFormatoA", referencedColumnName = "id", unique = true)
    private FormatoA objFormato;

    
}
