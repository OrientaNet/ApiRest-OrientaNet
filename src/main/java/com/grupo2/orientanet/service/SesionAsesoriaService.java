package com.grupo2.orientanet.service;

import com.grupo2.orientanet.dto.SesionAsesoriaDTO;

import java.util.List;

public interface SesionAsesoriaService {


    List<SesionAsesoriaDTO> findAll();


    SesionAsesoriaDTO findById(Long id);


    SesionAsesoriaDTO save(SesionAsesoriaDTO sesionAsesoriaDTO);


    SesionAsesoriaDTO update(Long id, SesionAsesoriaDTO sesionAsesoriaDetails) throws Exception;


    void delete(Long id);
}
