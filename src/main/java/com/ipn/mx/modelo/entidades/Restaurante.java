package com.ipn.mx.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idRestaurante")
    private int idRestaurante;
    
    @Column(name = "nombre", length =50, nullable=false)
    private String nombre;
    
    @Column(name = "correo", length =50, nullable=false)
    private String correo;
    
    @Column(name = "pass", length =50, nullable=false)
    private String pass;
    
    @Column(name = "descripcion", length =100, nullable=false)
    private String descripcion;
    
    @Column(name = "web", length =50, nullable=false)
    private String web; 

    @Column(name = "horario", length =50, nullable=false)
    private String horario;
    
    @Column(name = "telefono", length =50, nullable=false)
    private String telefono;   
}
