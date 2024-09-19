package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Pregunta;
import com.grupo2.orientanet.repository.PreguntaRepository;
import com.grupo2.orientanet.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PreguntaServiceImpl implements PreguntaService {
    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public List<Pregunta> listarPreguntas() {
        return preguntaRepository.findAll();
    }

    @Override
    public Optional<Pregunta> obtenerPreguntaPorId(Long id) {
        return preguntaRepository.findById(id);
    }

    @Override
    public Pregunta guardarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public void eliminarPregunta(Long id) {
        preguntaRepository.deleteById(id);
}
}