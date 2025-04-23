package com.fhce.inv.obj;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class redRequestDTO {
	private String ip;
    private String segmento;
    private String dns;
    private String vlan;
    private String switchRed;
    private String puerto;
    private Long idEquipo;
    private LocalDate fechaRegistro;
    private int estado;
}
