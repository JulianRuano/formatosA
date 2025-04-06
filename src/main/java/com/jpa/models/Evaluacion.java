package com.jpa.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

}
