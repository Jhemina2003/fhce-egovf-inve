package com.fhce.inv.obj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class equipoResponseDTO {
	private Long idEquipo;
    private String codigo;
    private String macSerie;
    private String marca;
    private Long idTipo;
    private String tipoNombre;
    private String modelo;
    private String detalle;
}
