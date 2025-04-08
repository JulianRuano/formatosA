package com.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "formatosTIA")
public class FormatoTIA extends FormatoA{
    @Column(nullable = false, length = 100)
    private String nombreAsesor;

    @Column(nullable = false, length = 100)
    private String nombreEstudiante1;
    
    @Column(nullable = false, length = 100)
    private String rutaCA;

    public FormatoTIA() {
        super();
    }

    public FormatoTIA(String nombreAsesor, String nombreEstudiante1, String rutaCA) {
        this.nombreAsesor = nombreAsesor;
        this.nombreEstudiante1 = nombreEstudiante1;
        this.rutaCA = rutaCA;
    }
}