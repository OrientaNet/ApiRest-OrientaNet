package com.grupo2.orientanet.service.impl;

import com.grupo2.orientanet.dto.PlanDTO;
import com.grupo2.orientanet.exception.ResourceNotFoundException;
import com.grupo2.orientanet.mapper.PlanMapper;
import com.grupo2.orientanet.model.entity.Plan;
import com.grupo2.orientanet.repository.PlanRepository;
import com.grupo2.orientanet.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.planMapper = planMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PlanDTO> findAll() {
        List<Plan> planes = planRepository.findAll();
        return planes.stream().map(planMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public PlanDTO findById(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan no encontrado"));
        return planMapper.toDTO(plan);
    }

    @Transactional
    @Override
    public PlanDTO save(PlanDTO planDTO) {
        Plan plan = planMapper.toEntity(planDTO);
        plan = planRepository.save(plan);
        return planMapper.toDTO(plan);
    }

    @Transactional
    @Override
    public PlanDTO update(Long id, PlanDTO planDetails) throws Exception {
        if (!planRepository.existsById(id)) {
            throw new ResourceNotFoundException("El plan no existe");
        }

        Plan existingPlan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El plan con el id " + id + " no existe"));

        // Actualizar campos del plan
                 existingPlan.setNombre(planDetails.getNombre());
                 existingPlan.setPrecio(planDetails.getPrecio());
                 existingPlan.setBeneficios(planDetails.getBeneficios());
                 existingPlan.setDuracionDias(planDetails.getDuracionDias());

        // Guardar el plan actualizado
                 existingPlan = planRepository.save(existingPlan);
                 return planMapper.toDTO(existingPlan);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El id del plan no fue encontrado"));
        planRepository.delete(plan);
    }
}
