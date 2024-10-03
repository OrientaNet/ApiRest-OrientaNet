package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.CarreraDTO;
import com.grupo2.orientanet.mapper.CarreraMapper;
import com.grupo2.orientanet.model.entity.Carrera;
import com.grupo2.orientanet.model.entity.Usuario;
import com.grupo2.orientanet.repository.CarreraRepository;
import com.grupo2.orientanet.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;
    @Autowired
    private CarreraMapper carreraMapper;

    @Transactional
    @Override
    public CarreraDTO crearCarrera(CarreraDTO carreraDTO) {
        Carrera carrera = carreraMapper.toEntity(carreraDTO);
        carrera = carreraRepository.save(carrera);
        return carreraMapper.toDTO(carrera);
    }

    @Transactional(readOnly = true)
    @Override
    public CarreraDTO obtenerCarreraPorId(Long id) {
        Carrera carrera = carreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        return carreraMapper.toDTO(carrera);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CarreraDTO> obtenerTodasLasCarreras() {
        List<Carrera> carrera = carreraRepository.findAll();
        return carrera.stream().map(carreraMapper::toDTO).toList();
    }

    @Transactional
    @Override
    public CarreraDTO actualizarCarrera(Long id, CarreraDTO carreraDTO) throws Exception {

        if (!carreraRepository.existsById(id)) {
            throw new Exception("La carrera no existe");
        }

        Carrera existingCarrera = carreraRepository.findById(id)
                .orElseThrow(() -> new Exception("La carrera no existe"));

        existingCarrera.setNombre(carreraDTO.getNombre());
        existingCarrera.setDescripcion(carreraDTO.getDescripcion());

        existingCarrera = carreraRepository.save(existingCarrera);

        return carreraMapper.toDTO(existingCarrera);
    }

    @Override
    public void eliminarCarrera(Long id) {
        Carrera carrera = carreraRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El id de la carrera no fue encontrada"));
        carreraRepository.delete(carrera);
    }
}
