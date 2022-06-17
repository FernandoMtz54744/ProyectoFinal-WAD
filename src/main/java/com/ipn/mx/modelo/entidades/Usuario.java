    package com.ipn.mx.modelo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
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
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Column(name = "usuario", length =50, nullable=false)
    private String usuario;
    
    @Column(name = "pass", length =50, nullable=false)
    private String pass;
    
    @Column(name = "correo", length =50, nullable=false)
    private String correo;
    
}
