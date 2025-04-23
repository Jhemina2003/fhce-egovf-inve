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
@Table(name = "ubicacion")
public class ubicacionModel {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long idUbicacion;
    
    @ManyToOne
    @JoinColumn(name = "_01idequipo", nullable = false)
    private equipoModel equipo;
    
    @Column(name = "_02ambiente", nullable = false)
    private String ambiente;
    
    @Column(name = "_03latitud")
    private String latitud;
    
    @Column(name = "_04longitud")
    private String longitud;
    
    @Column(name = "_05fecha", nullable = false)
    private LocalDate fecha;
    
    @Column(name = "_06estado", nullable = false)
    private int estado = 1; // Por defecto activo
}
