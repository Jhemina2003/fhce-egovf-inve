package com.fhce.inv.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tipo")
public class tipoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long idTipo;
	
	@Column(name = "_01sigla", nullable = false)
	private String sigla;
	
	@Column(name = "_02nombre", nullable = false)
	private String nombre;
	
	@Column(name = "_03icono", nullable = false)
	private String icono;
	
	@Column(name = "_04detalle", nullable = false)
	private String detalle;
	
	@OneToMany(mappedBy = "tipo")
    private List<equipoModel> equipos;
}

