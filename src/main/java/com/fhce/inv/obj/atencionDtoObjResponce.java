package com.fhce.inv.obj;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class atencionDtoObjResponce {
    private Long idAtencion;
    private Long cif;
    private String codigo;
    private LocalDate fechaSolicitud;
    private String horaSolicitud;
    private String equipo;
    private Long idTipo;
    private String especificacion;
    private String error;
    private String detalle;
    private LocalDate fechaAtencion;
    private String horaAtencion;
    private int estado;
    private List<String> resumen;
}

