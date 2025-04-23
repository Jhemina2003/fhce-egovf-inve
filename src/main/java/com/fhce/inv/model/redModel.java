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
@Table(name = "red")
public class redModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idRed;
	
	@Column(name = "_01ip", nullable = false)
	private String ip;
	
	@Column(name = "_02segmento", nullable = false)
	private String segmento;
	
	@Column(name = "_03dns", nullable = false)
	private String dns;
	
	@Column(name = "_04vlan", nullable = false)
	private String vlan;
	
	@Column(name = "_05switch", nullable = false)
	private String switchRed;
	
	@Column(name = "_06puerto", unique= true, nullable = false)
	private String puerto;
	
	@Column(name = "_07fecharegistro", nullable = false)
	private LocalDate fecharegistro;
	
	@ManyToOne
    @JoinColumn(name = "_08idequipo", nullable = false)
    private equipoModel equipo;
	
	@Column(name = "_09estado", nullable = false)
	private int estado = 1; // Por defecto activo
}