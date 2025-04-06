package com.jpa.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FormatoA {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String nombreDirector;
    @Column(nullable = false)
    private String objetivoGeneral;
    @Column(nullable = false)
    private List<String> objetivosEspecificos;


    private Estado estado;

}
