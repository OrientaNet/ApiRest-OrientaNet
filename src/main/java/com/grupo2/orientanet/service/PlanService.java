package com.grupo2.orientanet.service;
import com.grupo2.orientanet.dto.PlanDTO;

import java.util.List;

public interface PlanService {
    PlanDTO save(PlanDTO planDTO);
    PlanDTO update(Long id, PlanDTO planDetails) throws Exception;
    void delete(Long id);
    PlanDTO findById(Long id);
    List<PlanDTO> findAll();
}
