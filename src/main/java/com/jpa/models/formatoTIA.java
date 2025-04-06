package com.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class formatoTIA extends FormatoA{
    @Column(nullable = false, length = 100)
    private String nombreAsesor;
    @Column(nullable = false, length = 100)
    private String nombreEstudiante1;
    @Column(nullable = false, length = 100)
    private String rutaCA;

    public formatoTIA() {
        super();
    }

    public formatoTIA(String nombreAsesor, String nombreEstudiante1, String rutaCA) {
        this.nombreAsesor = nombreAsesor;
        this.nombreEstudiante1 = nombreEstudiante1;
        this.rutaCA = rutaCA;
    }
}