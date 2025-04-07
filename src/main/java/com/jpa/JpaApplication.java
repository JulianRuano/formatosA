package com.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.models.Docente;
import com.jpa.models.Estado;
import com.jpa.models.FormatoA;
import com.jpa.models.FormatoPPA;
import com.jpa.models.FormatoTIA;
import com.jpa.repository.IDocenteRepository;
import com.jpa.repository.IFormatoRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaApplication implements CommandLineRunner {

	private final IFormatoRepository formatoRepository;

	private final IDocenteRepository docenteRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//almacenarFormulario();
	}

	@Transactional
	public void almacenarFormulario(){
		FormatoA formatoPPA = new FormatoPPA("Juan Perez", "Maria Lopez");
		formatoPPA.setTitulo("Titulo PPA");
		formatoPPA.setObjetivoGeneral("Objetivo General PPA");
		formatoPPA.setObjetivosEspecificos(List.of("Objetivo Especifico 1"));

		Estado estadoPPA = new Estado();
		estadoPPA.setEstadoActual("Formualdo");
		estadoPPA.setFechaRegistro(new Date());
		formatoPPA.setEstado(estadoPPA);

		Docente docente = docenteRepository.getReferenceById(1);
		formatoPPA.setObjDocente(docente);

		formatoRepository.save(formatoPPA);
		
	}

}
