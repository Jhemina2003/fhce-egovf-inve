package com.fhce.inv.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.inv.obj.equipoCpuRequestDTO;
import com.fhce.inv.obj.equipoRequestDTO;
import com.fhce.inv.obj.equipoResponseDTO;
import com.fhce.inv.service.equipoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/equipo")
@RequiredArgsConstructor
public class equipoController {

    private final equipoService equipoService;
    
    @PostMapping("/addEquipo")
    public ResponseEntity<equipoResponseDTO> addEquipo(@RequestBody equipoRequestDTO equipoRequestDTO) {
        try {
            equipoResponseDTO response = equipoService.addEquipo(equipoRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/addEquipoCpu")
    public ResponseEntity<equipoResponseDTO> addCpu(@RequestBody equipoCpuRequestDTO request) {
        try {
            equipoResponseDTO response = equipoService.addCpu(
                request.getEquipoRequestDTO(), 
                request.getComponentePcRequestDTO()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/getEquipotipo/{idTipo}")
    public ResponseEntity<List<equipoResponseDTO>> getEquipoTipo(@PathVariable Long idTipo) {
        try {
            List<equipoResponseDTO> response = equipoService.getEquipoTipo(idTipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

