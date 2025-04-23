package com.fhce.inv.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class atencionRequestDTO {
	private Long idEquipo;
    private LocalDate fechaSolicitud;
    private String horaSolicitud;
    private String especificacion;
    private String error;
    private String solucion;
    private LocalDate fechaAtencion;
    private String horaAtencion;
    private Integer estado;
    private Long idPertenece;
}
