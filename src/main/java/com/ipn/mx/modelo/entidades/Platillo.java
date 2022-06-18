package com.ipn.mx.modelo.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cortes Lopez Jaime Alejandro
 * @author Godinez Montero Esmeralda
 * @author Martinez Martinez Fernando
 */

@Entity
@Table 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idPlatillo")
    private int idPlatillo;
    
    @Column(name = "nombre", length =50, nullable=false)
    private String nombre;
    
    @Column(name = "descripcion", length =500, nullable=false)
    private String descripcion;
    
    @Column(name = "foto")
    private byte[] foto;
    
    @Column(name = "nombreFoto", length =50, nullable=false)
    private String nombreFoto;
    
    @Column(name = "idRestaurante", nullable=false)
    private int idRestaurante;
    
    @Column(name = "idCategoria", nullable=false)
    private int idCategoria;
}
