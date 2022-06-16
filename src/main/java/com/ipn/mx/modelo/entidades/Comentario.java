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
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idComentario")
    private int idComentario;
    
    @Column(name = "comentario", length =100, nullable=false)
    private String comentario;
    
    @Column(name = "calificacion", nullable=false)
    private int calificacion;
    
    @Column(name = "idUsuario", nullable=false)
    private int idUsuario;
 
    @Column(name = "idPlatillo", nullable=false)
    private int idPlatillo;
}
