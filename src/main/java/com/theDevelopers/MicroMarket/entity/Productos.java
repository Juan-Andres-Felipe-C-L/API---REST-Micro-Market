package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Productos")
@Data

public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "nombre_producto" )
    private String nombreProducto;  
    
    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio")
    private double precio;

    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "activo")
    private boolean activo;

    @Column(name = "codigo_barras")
    private String codigoBarras;


}
