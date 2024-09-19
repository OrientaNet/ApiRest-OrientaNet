package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.repository.CarreraRepository;
import com.grupo2.orientanet.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public Carrera crearCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    public Carrera obtenerCarreraPorId(Long id) {
        return carreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
    }

    @Override
    public List<Carrera> obtenerTodasLasCarreras() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera actualizarCarrera(Long id, Carrera carrera) {
        Carrera carreraExistente = obtenerCarreraPorId(id);
        carreraExistente.setNombre(carrera.getNombre());
        return carreraRepository.save(carreraExistente);
    }

    @Override
    public void eliminarCarrera(Long id) {
        carreraRepository.deleteById(id);
    }
}
