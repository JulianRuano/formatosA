package com.jpa.models;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(nullable = false, length = 50)
    private String estadoActual;

    @Column(nullable = false)
    private Date fechaRegistro;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private FormatoA formatoA;
    
}
