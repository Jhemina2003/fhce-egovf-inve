package com.fhce.inv.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.softwareDao;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.softwareModel;
import com.fhce.inv.obj.softwareRequestDTO;
import com.fhce.inv.obj.softwareResponseDTO;
import com.fhce.inv.service.softwareService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class softwareServiceImp implements softwareService {

    private final softwareDao softwareDao;
    private final equipoDao equipoDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public softwareResponseDTO addSoftware(softwareRequestDTO softwareRequestDTO) {
        equipoModel equipo = equipoDao.findById(softwareRequestDTO.getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        softwareModel software = modelMapper.map(softwareRequestDTO, softwareModel.class);
        software.setEquipo(equipo);

        if (software.getFecha() == null) {
            software.setFecha(LocalDate.now());
        }

        software.setEstado(1);
        softwareModel savedSoftware = softwareDao.save(software);

        softwareResponseDTO response = modelMapper.map(savedSoftware, softwareResponseDTO.class);
        response.setIdEquipo(equipo.getIdequipo());
        response.setCodigoEquipo(equipo.getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public softwareResponseDTO updateSoftware(Long id, softwareRequestDTO softwareRequestDTO) {
        softwareModel software = softwareDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        if (softwareRequestDTO.getIdEquipo() != null) {
            equipoModel equipo = equipoDao.findById(softwareRequestDTO.getIdEquipo())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
            software.setEquipo(equipo);
        }
        if (softwareRequestDTO.getNombre() != null) {
            software.setNombre(softwareRequestDTO.getNombre());
        }
        
        if (softwareRequestDTO.getVersion() != null) {
            software.setVersion(softwareRequestDTO.getVersion());
        }
        
        if (softwareRequestDTO.getEstadoLicencia() != null) {
            software.setEstadoLicencia(softwareRequestDTO.getEstadoLicencia());
        }
        
        if (softwareRequestDTO.getTipo() != null) {
            software.setTipo(softwareRequestDTO.getTipo());
        }
        
        if (softwareRequestDTO.getFecha() != null) {
            software.setFecha(softwareRequestDTO.getFecha());
        }
        softwareModel updatedSoftware = softwareDao.save(software);
        softwareResponseDTO response = modelMapper.map(updatedSoftware, softwareResponseDTO.class);
        response.setIdEquipo(updatedSoftware.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedSoftware.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public softwareResponseDTO getSoftware(Long id) {
        softwareModel software = softwareDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        
        if (software.getEstado() != 1) {
            throw new RuntimeException("Software no disponible (inactivo)");
        }
        
        softwareResponseDTO response = modelMapper.map(software, softwareResponseDTO.class);
        response.setIdEquipo(software.getEquipo().getIdequipo());
        response.setCodigoEquipo(software.getEquipo().getCodigo());
        
        return response;
    }
    
    @Override
    @Transactional
    public List<softwareResponseDTO> getSoftwarePorEquipo(Long idEquipo) {
        equipoModel equipo = equipoDao.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        
        List<softwareModel> softwares = softwareDao.findByEquipoAndEstado(equipo, 1);
        
        return softwares.stream()
                .map(software -> {
                    softwareResponseDTO dto = modelMapper.map(software, softwareResponseDTO.class);
                    dto.setIdEquipo(equipo.getIdequipo());
                    dto.setCodigoEquipo(equipo.getCodigo());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public softwareResponseDTO cambiarEstadoSoftware(Long id, int estado) {
        if (estado != 1 && estado != 0) {
            throw new RuntimeException("Estado no válido. Use 1 para activo, 0 para inactivo");
        }
        
        softwareModel software = softwareDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        
        software.setEstado(estado);
        
        softwareModel updatedSoftware = softwareDao.save(software);
        
        softwareResponseDTO response = modelMapper.map(updatedSoftware, softwareResponseDTO.class);
        response.setIdEquipo(updatedSoftware.getEquipo().getIdequipo());
        response.setCodigoEquipo(updatedSoftware.getEquipo().getCodigo());
        
        return response;
    }
}
