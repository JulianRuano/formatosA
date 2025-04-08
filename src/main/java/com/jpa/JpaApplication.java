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
import com.jpa.models.Rol;
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
@Transactional
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
		//almacenarFormularioPPA(1);
		//List<Integer> docentes = List.of(1, 2);
		//crearObservacion(docentes, "Observaciones de ROA", 1);
		//consultarFormatoADocente(1);
		//listarObservacionesPorFormato(1);
		listarMiembrosComite();
	}

	@Transactional
	public void almacenarFormularioPPA(Integer idDocente) {
		FormatoA formatoPPA = new FormatoPPA("Juan Perez", "Maria Lopez");
		formatoPPA.setTitulo("Titulo PPA "+ idDocente);
		formatoPPA.setObjetivoGeneral("Objetivo General PPA");
		formatoPPA.setObjetivosEspecificos("Objetivo Especifico 1, Objetivo Especifico 2");

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

		Evaluacion evaluacion = null; 

		if(formato.getEvaluacion() == null || formato.getEvaluacion().isEmpty()) {

			Evaluacion nuevaEvaluacion = new Evaluacion();
			nuevaEvaluacion.setConcepto("Por establecer");
			nuevaEvaluacion.setFechaRegistroConcepto(new Date());
			nuevaEvaluacion.setNombreCoordinador(observacion);
			nuevaEvaluacion.setObjFormato(formato);
			evaluacion = evaluacionRepository.save(nuevaEvaluacion);

		} else{
			evaluacion = formato.getEvaluacion().get(0);
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


	@Transactional(readOnly = true)
	public void consultarFormatoADocente(Integer idDocente) {
		Docente docente = docenteRepository.findById(idDocente)
				.orElseThrow(() -> new RuntimeException("Docente no encontrado con id: " + idDocente));

		List<FormatoA> formatos = docente.getObjFormatoA();
		List<Observacion> observaciones = docente.getObjObservacion();

		System.out.println("Docente: " + docente.getNombre() + " " + docente.getApellido());
		System.out.println("--------------------------------------------------");

		for (FormatoA formato : formatos) {
			System.out.println("Formato A: " + formato.getTitulo());
			System.out.println("Objetivo General: " + formato.getObjetivoGeneral());
			System.out.println("Objetivos Especificos: " + formato.getObjetivosEspecificos());
			System.out.println("Estado: " + formato.getEstado().getEstadoActual());
			System.out.println("Evaluaciones:");
			Iterable<Evaluacion> evaluaciones = formato.getEvaluacion();
			for (Evaluacion evaluacion : evaluaciones) {
				System.out.println("  Evaluacion: " + evaluacion.getConcepto());
				System.out.println("  Fecha Registro: " + evaluacion.getFechaRegistroConcepto());
				System.out.println("  Nombre Coordinador: " + evaluacion.getNombreCoordinador());
			}
			System.out.println("Observaciones:");
			for (Observacion observacion : observaciones) {
				System.out.println("  Observacion: " + observacion.getObservacion());
				System.out.println("  Fecha Registro: " + observacion.getFechaRegistro());
			}
			System.out.println("--------------------------------------------------");

		}
	}

	@Transactional
	public void listarObservacionesPorFormato(Integer idFormato) {
        Optional<FormatoA> optionalFormato = formatoRepository.findById(idFormato);
        if (!optionalFormato.isPresent()) {
            System.out.println("Formato no encontrado.");
            return;
        }

        FormatoA formato = optionalFormato.get();

        System.out.println("====== DATOS DEL FORMATO ======");
        System.out.println("ID: " + formato.getId());
        System.out.println("Título: " + formato.getTitulo());
        System.out.println("Objetivo General: " + formato.getObjetivoGeneral());

        if (formato.getEstado() != null) {
            System.out.println("Estado actual: " + formato.getEstado().getEstadoActual());
        } else {
            System.out.println("Estado: No definido");
        }

        List<Evaluacion> evaluaciones = formato.getEvaluacion();
        if (evaluaciones == null || evaluaciones.isEmpty()) {
            System.out.println("No hay evaluaciones asociadas.");
            return;
        }

        for (Evaluacion eval : evaluaciones) {
            System.out.println("\n--- EVALUACIÓN ---");
            System.out.println("Concepto: " + eval.getConcepto());
            System.out.println("Fecha Registro: " + eval.getFechaRegistroConcepto());
            System.out.println("Coordinador: " + eval.getNombreCoordinador());

            List<Observacion> observaciones = eval.getObservacion();
            if (observaciones == null ) {
                System.out.println("Sin observaciones.");
                continue;
            }

            for (Observacion obs : observaciones) {
                System.out.println("\n> Observación:");
                System.out.println("Texto: " + obs.getObservacion());
                System.out.println("Fecha: " + obs.getFechaRegistro());

                List<Docente> docentes = obs.getObjDocente();
                if (docentes == null || docentes.isEmpty()) {
                    System.out.println("Sin docentes asociados.");
                } else {
                    System.out.println("Docentes que realizaron esta observación:");
                    for (Docente d : docentes) {
                        System.out.println("- " + d.getNombre() + " " + d.getApellido() + " (" + d.getCorreo() + ")");
                    }
                }
            }
        }
    }

	public void listarMiembrosComite() {
        List<Docente> docentes = docenteRepository.findAll();

        System.out.println("================= MIEMBROS DEL COMITÉ =================");

        for (Docente docente : docentes) {
            List<Historico> historicos = docente.getObjHistorico();

            if (historicos == null || historicos.isEmpty()) {
                continue; // Este docente no tiene historial de roles
            }

            for (Historico historico : historicos) {
                Rol rol = historico.getObjRol();

                System.out.println("Docente: " + docente.getNombre() + " " + docente.getApellido());
                System.out.println("Rol: " + (rol != null ? rol.getRol_asignado() : "Sin rol"));
                System.out.println("Fecha inicio: " + historico.getFechaInicio());
                System.out.println("----------------------------------------");
            }
        }
    }
}
