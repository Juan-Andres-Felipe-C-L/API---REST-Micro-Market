package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Productos")
@Data

public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(name = "nombre_producto" )
    private String nombre_producto;  
    
    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private float precio;

    @Column(name = "id_categoria")
    private Long id_categoria;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "codigo_barras")
    private String codigo_barras;


}
