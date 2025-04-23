package com.fhce.inv.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ubicacionResponseDTO {
	private Long idUbicacion;
    private Long idEquipo;
    private String codigoEquipo;
    private String ambiente;
    private String latitud;
    private String longitud;
    private LocalDate fecha;
    private int estado;
}
