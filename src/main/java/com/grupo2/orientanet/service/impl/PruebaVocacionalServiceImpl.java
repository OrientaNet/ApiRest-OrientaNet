package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PruebaVocacionalDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.PruebaVocacionalMapper;
import com.grupo2.orientanet.model.entity.*;
import com.grupo2.orientanet.repository.*;
import com.grupo2.orientanet.service.PruebaVocacionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PruebaVocacionalServiceImpl implements PruebaVocacionalService {

    @Autowired
    private PruebaVocacionaRepository pruebaVocacionalRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private ResultadoTestRepository resultadoTestRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private PruebaVocacionalMapper pruebaVocacionalMapper;


    @Transactional
    @Override
    public PruebaVocacionalDTO crearPrueba(PruebaVocacionalDTO pruebaVocacionalDTO) {

        PruebaVocacional pruebaVocacional = pruebaVocacionalMapper.toEntity(pruebaVocacionalDTO);
        pruebaVocacional = pruebaVocacionalRepository.save(pruebaVocacional);
        return pruebaVocacionalMapper.toDTO(pruebaVocacional);
    }


    @Transactional(readOnly = true)
    @Override
    public PruebaVocacionalDTO obtenerPruebaPorId(Long id) {
        PruebaVocacional pruebaVocacional = pruebaVocacionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prueba vocacional no encontrada"));
        return pruebaVocacionalMapper.toDTO(pruebaVocacional);
    }


    @Transactional(readOnly = true)
    @Override
    public List<PruebaVocacionalDTO> obtenerTodasLasPruebas() {
        List<PruebaVocacional> pruebaVocacionals = pruebaVocacionalRepository.findAll();
        return pruebaVocacionals.stream().map(pruebaVocacionalMapper::toDTO).toList();
    }



    @Transactional
    @Override
    public PruebaVocacionalDTO actualizarPrueba(Long id, PruebaVocacionalDTO pruebaVocacionalDTO) throws Exception{

        if (!pruebaVocacionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("La prueba vocacional no existe");
        }

        PruebaVocacional existingPrueba = pruebaVocacionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La pregunta con el id "+id+" no existe"));

        existingPrueba.setNombre(pruebaVocacionalDTO.getNombre());

        if (pruebaVocacionalDTO.getPreguntaIds() != null && !pruebaVocacionalDTO.getPreguntaIds().isEmpty()) {
            List<Pregunta> preguntas = new ArrayList<>();
            for (Long preguntaId : pruebaVocacionalDTO.getPreguntaIds()) {
                Pregunta pregunta = new Pregunta();
                pregunta.setId(preguntaId);
                preguntas.add(pregunta);
            }
            existingPrueba.setPreguntas(preguntas);
        }

        existingPrueba = pruebaVocacionalRepository.save(existingPrueba);
        return pruebaVocacionalMapper.toDTO(existingPrueba);
    }



    @Transactional
    @Override
    public void eliminarPrueba(Long id) {
       PruebaVocacional pruebaVocacional =  pruebaVocacionalRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("El id de la prueba vocacional no fue encontrado"));
       pruebaVocacionalRepository.delete(pruebaVocacional);
    }


    @Transactional
    @Override
    public ResultadoTest realizarPrueba(Long pruebaId,Long estudianteId, Map<Long, Long> respuestasSeleccionadas) {
        // Buscar la prueba vocacional
        PruebaVocacional prueba = pruebaVocacionalRepository.findById(pruebaId)
                .orElseThrow(() -> new ResourceNotFoundException("Prueba no encontrada"));


        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        // Mapa para contar los puntos de cada carrera
        Map<Long, Integer> contadorCarreras = new HashMap<>();

        // Obtener todas las carreras y inicializar el contador
        List<Carrera> carreras = carreraRepository.findAll();
        for (Carrera carrera : carreras) {
            contadorCarreras.put(carrera.getId(), 0); // Inicializar el contador para cada carrera
        }

        // Iterar sobre las respuestas seleccionadas
        for (Map.Entry<Long, Long> entrada : respuestasSeleccionadas.entrySet()) {
            Long respuestaId = entrada.getValue(); // ID de la respuesta seleccionada

            // Obtener la respuesta seleccionada
            Respuesta respuesta = respuestaRepository.findById(respuestaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Respuesta no encontrada"));

            // Obtener el ID de la carrera asociada a la respuesta
            Long carreraIdAsociada = respuesta.getCarrera().getId();

            // Incrementar el contador de puntos para la carrera asociada
            contadorCarreras.put(carreraIdAsociada, contadorCarreras.get(carreraIdAsociada) + 1);
        }

        // Buscar la carrera con más puntos
        Long carreraRecomendadaId = contadorCarreras.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // Obtener la carrera con más puntos
                .get()
                .getKey();

        Carrera carreraRecomendada = carreraRepository.findById(carreraRecomendadaId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));



        // Crear el resultado del test
        ResultadoTest resultado = new ResultadoTest();
        resultado.setPruebaNombre(prueba.getNombre());  // Asignar la prueba vocacional
        resultado.setEstudianteId(estudiante.getId());
        resultado.setCarrera(carreraRecomendada);
        resultado.setRecomendacion("La carrera recomendada es: " + carreraRecomendada.getNombre());
        estudiante.setResultadoTest(resultado);

        // Guardar y devolver el resultado del test
        return resultadoTestRepository.save(resultado);
    }



}

