package com.fhce.inv.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class componentePcResponseDTO {
	private Long idComponentesPC;
    private String fuente;
    private Integer memorias;
    private String capacidad;
    private String micro;
    private String microCapacidad;
    private String disco;
    private String cortapico;
    private String detalle;
    private String teclado;
    private String mouse;
    private String versionamiento;
    private Long idEquipo;
    private String codigoEquipo;
    private LocalDate fecha;
}
