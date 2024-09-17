package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.repository.RespuestaRepository;
import com.grupo2.orientanet.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Override
    public List<Respuesta> listarRespuestas() {
        return respuestaRepository.findAll();
    }

    @Override
    public Optional<Respuesta> obtenerRespuestaPorId(Long id) {
        return respuestaRepository.findById(id);
    }

    @Override
    public Respuesta guardarRespuesta(Respuesta respuesta) {
        return respuestaRepository.save(respuesta);
    }

    @Override
    public void eliminarRespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }
}