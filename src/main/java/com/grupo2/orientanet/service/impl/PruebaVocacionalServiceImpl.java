package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.model.entity.*;
import com.grupo2.orientanet.repository.*;
import com.grupo2.orientanet.service.PruebaVocacionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequestMapping
@RequiredArgsConstructor
public class PruebaVocacionalServiceImpl implements PruebaVocacionalService {

    private final PruebaVocacionalRepository pruebaVocacionalRepository;
    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;
    private final ResultadoTestRepository resultadoTestRepository;
    private final RespuestaRepository respuestaRepository;



    @Override
    public PruebaVocacional crearPrueba(PruebaVocacional pruebaVocacional) {
        return pruebaVocacionalRepository.save(pruebaVocacional);
    }



    @Override
    public PruebaVocacional obtenerPruebaPorId(Long id) {
        return pruebaVocacionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prueba vocacional no encontrada"));
    }



    @Override
    public List<PruebaVocacional> obtenerTodasLasPruebas() {
        return pruebaVocacionalRepository.findAll();
    }



    @Override
    public PruebaVocacional actualizarPrueba(Long id, PruebaVocacional pruebaVocacional) {
        PruebaVocacional pruebaExistente = obtenerPruebaPorId(id);
        pruebaExistente.setNombre(pruebaVocacional.getNombre());
        pruebaExistente.setPreguntas(pruebaVocacional.getPreguntas());
        return pruebaVocacionalRepository.save(pruebaExistente);
    }



    @Override
    public void eliminarPrueba(Long id) {
        pruebaVocacionalRepository.deleteById(id);
    }



    @Override
    public ResultadoTest realizarPrueba(Long pruebaId,Long estudianteId, Map<Long, Long> respuestasSeleccionadas) {
        // Buscar la prueba vocacional
        PruebaVocacional prueba = pruebaVocacionalRepository.findById(pruebaId)
                .orElseThrow(() -> new RuntimeException("Prueba no encontrada"));


        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

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
                    .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));

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
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));



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

