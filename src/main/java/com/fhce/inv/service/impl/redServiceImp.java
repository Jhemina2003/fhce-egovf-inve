package com.fhce.inv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.redDao;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.redModel;
import com.fhce.inv.obj.redRequestDTO;
import com.fhce.inv.obj.redResponseDTO;
import com.fhce.inv.service.redService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class redServiceImp implements redService {

    private final redDao redDao;
    private final equipoDao equipoDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public redResponseDTO addRed(redRequestDTO redRequestDTO) {
        equipoModel equipo = equipoDao.findById(redRequestDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        if (redRequestDTO.getPuerto() != null && redDao.existsByPuerto(redRequestDTO.getPuerto())) {
            throw new RuntimeException("Ya existe una configuración con este puerto");
        }

        redModel red = modelMapper.map(redRequestDTO, redModel.class);
        red.setEquipo(equipo);
        if (red.getFecharegistro() == null) {
            red.setFecharegistro(LocalDate.now());
        }
        red.setEstado(1);
        redModel savedRed = redDao.save(red);
        redResponseDTO response = modelMapper.map(savedRed, redResponseDTO.class);
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public redResponseDTO updateRed(Long id, redRequestDTO redRequestDTO) {
        redModel red = redDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuración de red no encontrada"));
        if (redRequestDTO.getPuerto() != null && 
            !redRequestDTO.getPuerto().equals(red.getPuerto()) &&
            redDao.existsByPuerto(redRequestDTO.getPuerto())) {
            throw new RuntimeException("Ya existe una configuración con este puerto");
        }
        if (redRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(redRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            red.setEquipo(equipo);
        }
        if (redRequestDTO.getIp() != null) {
            red.setIp(redRequestDTO.getIp());
        }
        
        if (redRequestDTO.getSegmento() != null) {
            red.setSegmento(redRequestDTO.getSegmento());
        }
        
        if (redRequestDTO.getDns() != null) {
            red.setDns(redRequestDTO.getDns());
        }
        
        if (redRequestDTO.getVlan() != null) {
            red.setVlan(redRequestDTO.getVlan());
        }
        
        if (redRequestDTO.getSwitchRed() != null) {
            red.setSwitchRed(redRequestDTO.getSwitchRed());
        }
        
        if (redRequestDTO.getPuerto() != null) {
            red.setPuerto(redRequestDTO.getPuerto());
        }
        
        if (redRequestDTO.getFechaRegistro() != null) {
            red.setFecharegistro(redRequestDTO.getFechaRegistro());
        }
        
        redModel updatedRed = redDao.save(red);
        
        redResponseDTO response = modelMapper.map(updatedRed, redResponseDTO.class);
        response.setIdEquipo(updatedRed.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedRed.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public redResponseDTO getRed(Long id) {
        redModel red = redDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuración de red no encontrada"));
        
        if (red.getEstado() != 1) {
            throw new RuntimeException("Configuración de red no disponible (inactiva)");
        }
        redResponseDTO response = modelMapper.map(red, redResponseDTO.class);
        response.setIdEquipo(red.getEquipo().getIdequipo());
        response.setCodigoEquipo(red.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public List<redResponseDTO> getRedPorEquipo(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        List<redModel> redes = redDao.findByEquipoAndEstado(equipo, 1);
        return redes.stream()
                .map(red -> {
                    redResponseDTO dto = modelMapper.map(red, redResponseDTO.class);
                    dto.setIdEquipo(equipo.getIdequipo());
                    dto.setCodigoEquipo(equipo.getCodigo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public redResponseDTO cambiarEstadoRed(Long id, int estado) {
        if (estado != 1 && estado != 0) {
            throw new RuntimeException("Estado no válido. Use 1 para activo, 0 para inactivo");
        }
        redModel red = redDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuración de red no encontrada"));
        red.setEstado(estado);
        
        redModel updatedRed = redDao.save(red);
        redResponseDTO response = modelMapper.map(updatedRed, redResponseDTO.class);
        response.setIdEquipo(updatedRed.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedRed.getEquipo().getCodigo());
        
        return response;
    }
}