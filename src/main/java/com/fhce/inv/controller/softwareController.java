package com.fhce.inv.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhce.inv.obj.softwareRequestDTO;
import com.fhce.inv.obj.softwareResponseDTO;
import com.fhce.inv.service.softwareService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/software")
@RequiredArgsConstructor
public class softwareController {

    private final softwareService softwareService;
    
    @PostMapping("/addSoftware")
    public ResponseEntity<?> addSoftware(@RequestBody softwareRequestDTO softwareRequestDTO) {
        try {
            softwareResponseDTO response = softwareService.addSoftware(softwareRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/updateSoftware/{id}")
    public ResponseEntity<?> updateSoftware(
            @PathVariable Long id,
            @RequestBody softwareRequestDTO softwareRequestDTO) {
        try {
            softwareResponseDTO response = softwareService.updateSoftware(id, softwareRequestDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getSoftware/{id}")
    public ResponseEntity<?> getSoftware(@PathVariable Long id) {
        try {
            softwareResponseDTO response = softwareService.getSoftware(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getSoftwarePorEquipo/{idEquipo}")
    public ResponseEntity<?> getSoftwarePorEquipo(@PathVariable Long idEquipo) {
        try {
            List<softwareResponseDTO> response = softwareService.getSoftwarePorEquipo(idEquipo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/cambiarEstadoSoftware/{id}/{estado}")
    public ResponseEntity<?> cambiarEstadoSoftware(
            @PathVariable Long id,
            @PathVariable int estado) {
        try {
            softwareResponseDTO response = softwareService.cambiarEstadoSoftware(id, estado);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}