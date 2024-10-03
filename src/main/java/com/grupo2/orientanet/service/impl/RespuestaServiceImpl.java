package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.RespuestaDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.RespuestaMapper;
import com.grupo2.orientanet.model.entity.Respuesta;
import com.grupo2.orientanet.repository.RespuestaRepository;
import com.grupo2.orientanet.service.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RespuestaServiceImpl implements RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private RespuestaMapper respuestaMapper;


    @Transactional(readOnly = true)
    @Override
    public List<RespuestaDTO> listarRespuestas() {
        List<Respuesta> respuestaDTO = respuestaRepository.findAll();
        return respuestaDTO.stream().map(respuestaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public RespuestaDTO obtenerRespuestaPorId(Long id) {

        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Respuesta no encontrada"));
        return respuestaMapper.toDTO(respuesta);
    }

    @Transactional
    @Override
    public RespuestaDTO guardarRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = respuestaMapper.toEntity(respuestaDTO);
        respuesta = respuestaRepository.save(respuesta);
        return respuestaMapper.toDTO(respuesta);
    }

    @Transactional
    @Override
    public void eliminarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("El id de la respuesta no fue encontrada"));
        respuestaRepository.deleteById(id);
    }
}
