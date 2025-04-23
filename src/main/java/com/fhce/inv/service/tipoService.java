package com.fhce.inv.service;

import java.util.List;

import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.obj.tipoRequestDTO;
import com.fhce.inv.obj.tipoResponseDTO;

public interface tipoService {
    tipoResponseDTO addTipo(tipoRequestDTO tipoRequestDTO);
    tipoResponseDTO getTipo(Long id);
    List<equipoResponseDTO> getEquiposPorTipo(Long idTipo);
}
