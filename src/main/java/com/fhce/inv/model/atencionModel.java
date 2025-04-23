package com.fhce.inv.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "atencion")
public class atencionModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idAtencion;
	
	@ManyToOne
    @JoinColumn(name = "_01idequipo", nullable = false)
    private equipoModel equipo;
	
	@Column(name = "_02fechasolicitud", nullable = false)
	private LocalDate fechaSolicitud;
	
	@Column(name = "_03horasolicitud", nullable = false)
	private String horaSolicitud;
	
	@Column(name = "_04especificacion", nullable = false)
	private String especificacion;
	
	@Column(name = "_05error", nullable = false)
	private String error;
	
	@Column(name = "_06solucion", nullable = false)
	private String solucion;
	
	@Column(name = "_07fechaatencion", nullable = false)
	private LocalDate fechaAtencion;
	
	@Column(name = "_08horaatencion", nullable = false)
	private String horaAtencion;
	
	@Column(name = "_09estado", nullable = false)
	private int estado;
	
	@ManyToOne
    @JoinColumn(name = "_10idpertenece", nullable = false)
    private perteneceModel pertenece;
}
