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
import com.jpa.models.Historico;
import com.jpa.repository.IDocenteRepository;
import com.jpa.repository.IEvaluacionRepository;
import com.jpa.repository.IFormatoRepository;
import com.jpa.repository.IHistoricoRepository;
import com.jpa.repository.IObservacionRepository;
import com.jpa.repository.IRolRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JpaApplication implements CommandLineRunner {

	private final IFormatoRepository formatoRepository;

	private final IDocenteRepository docenteRepository;

	private final IRolRepository rolRepository;

	private final IHistoricoRepository historicoRepository;

	private final IObservacionRepository observacionRepository;

	private final IEvaluacionRepository evaluacionRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		almacenarFormularioPPA(1, 1);
	}

	@Transactional
	public void almacenarFormularioPPA(Integer idDocente, Integer idEstado) {
		FormatoA formatoPPA = new FormatoPPA("Juan Perez", "Maria Lopez");
		formatoPPA.setTitulo("Titulo PPA "+ idDocente);
		formatoPPA.setObjetivoGeneral("Objetivo General PPA");
		formatoPPA.setObjetivosEspecificos(List.of("Objetivo Especifico 1"));

		Estado estadoPPA = new Estado();
		estadoPPA.setEstadoActual("Formualdo");
		estadoPPA.setFechaRegistro(new Date());
		formatoPPA.setEstado(estadoPPA);
		
		Docente docente;
		if(docenteRepository.existsById(idDocente)) {
			docente = docenteRepository.getReferenceById(idDocente);
		} else {
			docente = new Docente();
			docente.setNombre("Juan");
			docente.setApellido("Lopez");
			docente.setCorreo("mail"+idDocente+"@mail.com");
			docente.setNombreGrupo("Grupo 1");
		
			docente = docenteRepository.save(docente); // guarda y recupera
		
			Historico historico = new Historico();
			historico.setFechaInicio(new Date());
			historico.setEstado(true);
			historico.setObjDocente(docente);
			historico.setObjRol(rolRepository.getReferenceById(1));
		
			historicoRepository.save(historico);
		}
		formatoPPA.setObjDocente(docente);
		estadoPPA.setObjFormato(formatoPPA);
		
		formatoRepository.save(formatoPPA);
	}



}
