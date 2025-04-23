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
@Table(name = "software")
public class softwareModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idSoftware;
	
	@Column(name = "_01nombre", nullable = false)
	private String nombre;
	
	@Column(name = "_02version", nullable = false)
	private String version;
	
	@Column(name = "_03estado_licencia", nullable = false)
	private String estadoLicencia;
	
	@Column(name = "_04tipo", nullable = false)
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "_05idequipo", nullable = false)
	private equipoModel equipo;
	
	@Column(name = "_06fecha", nullable = false)
	private LocalDate fecha;
	
	@Column(name = "_07estado", nullable = false)
	private int estado = 1; // Por defecto activo
	
}

