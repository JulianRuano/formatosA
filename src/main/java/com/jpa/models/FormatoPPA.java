package com.jpa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FormatoPPA extends FormatoA {
    @Column(nullable = false, length = 100)
    private String nombreEstudiante1;
    
    @Column(nullable = false, length = 100)
    private String nombreEstudiante2;

    public FormatoPPA() {
        super();
    }

    public FormatoPPA(String nombreEstudiante1, String nombreEstudiante2) {
        this.nombreEstudiante1 = nombreEstudiante1;
        this.nombreEstudiante2 = nombreEstudiante2;
    }
}