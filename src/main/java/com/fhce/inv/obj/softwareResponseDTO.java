package com.fhce.inv.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class softwareResponseDTO {
	private Long idSoftware;
    private String nombre;
    private String version;
    private String estadoLicencia;
    private String tipo;
    private Long idEquipo;
    private String codigoEquipo;
    private LocalDate fecha;
    private int estado;
}
