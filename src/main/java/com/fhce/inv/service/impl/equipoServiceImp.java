package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.componentePcDao;
import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.model.componentePcModel;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.componentePcRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.service.equipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class equipoServiceImp implements equipoService {

    private final equipoDao equipoDao;
    private final tipoDao tipoDao;
    private final componentePcDao componentePcDao;
    private final ModelMapper modelMapper;
    
    @Override
    @Transactional
    public equipoResponseDTO addEquipo(equipoRequestDTO equipoRequestDTO) {
        //buscar el tipo
        tipoModel tipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
  
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        

        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }
    
    @Override
    @Transactional
    public equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentesPCRequestDTO) {
        //buscar el tipo
        tipoModel tipo = tipoDao.findById(equipoRequestDTO.getIdTipo())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        // validar que es un tipo CPU
        if (!tipo.getSigla().equals("CPU")) {
            throw new RuntimeException("El tipo debe ser CPU para agregar componentes de PC");
        }
        
        //crea el equipo
        equipoModel equipo = modelMapper.map(equipoRequestDTO, equipoModel.class);
        equipo.setTipo(tipo);
        
        // guardar el equipo
        equipoModel savedEquipo = equipoDao.save(equipo);
        
        componentePcModel componentePc = modelMapper.map(componentesPCRequestDTO, componentePcModel.class);
        componentePc.setEquipo(savedEquipo);
        
        componentePcDao.save(componentePc);
        
        equipoResponseDTO responseDTO = modelMapper.map(savedEquipo, equipoResponseDTO.class);
        
        responseDTO.setIdTipo(tipo.getIdTipo());
        responseDTO.setTipoNombre(tipo.getNombre());
        
        return responseDTO;
    }
    
    @Override
    @Transactional
    public List<equipoResponseDTO> getEquipoTipo(Long idTipo) {
        //Verif tipo existe
        tipoModel tipo = tipoDao.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        List<equipoModel> equipos = equipoDao.findByTipoIdTipo(idTipo);
        return equipos.stream()
                .map(equipo -> {
                    equipoResponseDTO dto = modelMapper.map(equipo, equipoResponseDTO.class);

                    dto.setIdTipo(tipo.getIdTipo());
                    dto.setTipoNombre(tipo.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
