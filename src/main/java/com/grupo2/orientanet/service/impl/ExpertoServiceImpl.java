package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.model.entity.Experto;
import com.grupo2.orientanet.repository.ExpertoRepository;
import com.grupo2.orientanet.service.ExpertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertoServiceImpl implements ExpertoService {

    private final ExpertoRepository expertoRepository;

    @Autowired
    public ExpertoServiceImpl(ExpertoRepository expertoRepository) {
        this.expertoRepository = expertoRepository;
    }

    @Override
    public List<Experto> findAll() {
        return expertoRepository.findAll();
    }

    @Override
    public Optional<Experto> findById(Long id) {
        return expertoRepository.findById(id);
    }

    @Override
    public Experto save(Experto experto) {
        return expertoRepository.save(experto);
    }

    @Override
    public Experto update(Long id, Experto expertoDetails) {
        return expertoRepository.findById(id)
                .map(experto -> {
                    experto.setInformacionPersonal(expertoDetails.getInformacionPersonal());
                    experto.setExperiencia(expertoDetails.getExperiencia());
                    experto.setCertificaciones(expertoDetails.getCertificaciones());
                    experto.setEspecializacion(expertoDetails.getEspecializacion());
                    experto.setUsuario(expertoDetails.getUsuario());
                    return expertoRepository.save(experto);
                })
                .orElseThrow(() -> new RuntimeException("Experto no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        expertoRepository.deleteById(id);
    }
}

