package com.fhce.inv.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fhce.inv.dao.equipoDao;
import com.fhce.inv.dao.tipoDao;
import com.fhce.inv.model.equipoModel;
import com.fhce.inv.model.tipoModel;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.obj.tipoRequestDTO;
import com.fhce.inv.obj.tipoResponseDTO;
import com.fhce.inv.service.tipoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class tipoServiceImp implements tipoService {

    private final tipoDao tipoDao;
    private final ModelMapper modelMapper;
    private final equipoDao equipoDao;
    
    @Override
    @Transactional
    public tipoResponseDTO addTipo(tipoRequestDTO tipoRequestDTO) {
        tipoModel tipo = modelMapper.map(tipoRequestDTO, tipoModel.class);
        tipoModel savedTipo = tipoDao.save(tipo);
        return modelMapper.map(savedTipo, tipoResponseDTO.class);
    }
    
    @Override
    @Transactional
    public tipoResponseDTO getTipo(Long id) {
        tipoModel tipo = tipoDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        return modelMapper.map(tipo, tipoResponseDTO.class);
    }
    
    @Override
    @Transactional
    public List<equipoResponseDTO> getEquiposPorTipo(Long idTipo) {
        tipoModel tipo = tipoDao.findById(idTipo)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        
        List<equipoModel> equipos = equipoDao.findByTipoIdTipo(idTipo);
        
        return equipos.stream()
                .map(equipo -> {
                    equipoResponseDTO dto = new equipoResponseDTO();
                    dto.setIdEquipo(equipo.getIdequipo());
                    dto.setCodigo(equipo.getCodigo());
                    dto.setMacSerie(equipo.getMacSerie());
                    dto.setMarca(equipo.getMarca());
                    dto.setIdTipo(tipo.getIdTipo());
                    dto.setTipoNombre(tipo.getNombre());
                    dto.setModelo(equipo.getModelo());
                    dto.setDetalle(equipo.getDetalle());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
