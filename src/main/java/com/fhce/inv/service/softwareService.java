package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.softwareRequestDTO;
import com.fhce.inv.obj.softwareResponseDTO;

public interface softwareService {
    softwareResponseDTO addSoftware(softwareRequestDTO softwareRequestDTO);
    softwareResponseDTO updateSoftware(Long id, softwareRequestDTO softwareRequestDTO);
    softwareResponseDTO getSoftware(Long id);
    List<softwareResponseDTO> getSoftwarePorEquipo(Long idEquipo);
    softwareResponseDTO cambiarEstadoSoftware(Long id, int estado); 
}
