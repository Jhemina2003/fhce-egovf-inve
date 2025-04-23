package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.componentePcRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;

public interface equipoService {
    /*equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentePcRequestDTO);
    List<equipoResponseDTO> getEquipoTipo(Long idTipo);*/
	equipoResponseDTO addCpu(equipoRequestDTO equipoRequestDTO, componentePcRequestDTO componentePcRequestDTO);
    equipoResponseDTO addEquipo(equipoRequestDTO equipoRequestDTO); // para otros tipos
    List<equipoResponseDTO> getEquipoTipo(Long idTipo);
}
