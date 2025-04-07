package com.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.models.Docente;
import com.jpa.models.Estado;
import com.jpa.models.Evaluacion;
import com.jpa.models.FormatoA;
import com.jpa.models.FormatoPPA;
import com.jpa.models.Historico;
import com.jpa.models.Observacion;
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
		//almacenarFormulario();
		//List<Integer> docentes = List.of(1, 2);
		//crearObservacion(docentes, "Observaciones de ROA", 1);
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

	@Transactional
	public void crearObservacion(List<Integer> docentes, String observacion, Integer idFormato) {
		Optional<FormatoA> formataOptional = formatoRepository.findById(idFormato);
		if (!formataOptional.isPresent()) {
            throw new RuntimeException("FormatoA con id " + idFormato + " no encontrado.");
        }

		FormatoA formato = formataOptional.get();

		Evaluacion evaluacion = null; //Intancia para guardar la evaluacion correspondite 
		List<Evaluacion> lstEvaluaciones = formato.getEvaluacion();

		if(lstEvaluaciones.isEmpty()) {

			Evaluacion nuevaEvaluacion = new Evaluacion();
			nuevaEvaluacion.setConcepto("Por establecer");
			nuevaEvaluacion.setFechaRegistroConcepto(new Date());
			nuevaEvaluacion.setObjFormato(formato);
			evaluacion = evaluacionRepository.save(nuevaEvaluacion);

		} else{

			evaluacion = lstEvaluaciones.stream().filter(e -> "Por corregir".equals(e.getConcepto()))
					.findFirst().orElse(null);

		}

		List<Docente> lstDocentes = docentes.stream().map(docenteId -> {
			Docente d = docenteRepository.getReferenceById(docenteId);
			return d;
		}).toList();


		Observacion nuevaObservacion = new Observacion();
		nuevaObservacion.setObservacion(observacion);
		nuevaObservacion.setFechaRegistro(new Date());
		nuevaObservacion.setObjEvaluacion(evaluacion);
		nuevaObservacion.setObjDocente(lstDocentes);

		observacionRepository.save(nuevaObservacion);
	}



}
