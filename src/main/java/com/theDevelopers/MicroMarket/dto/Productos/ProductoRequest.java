package com.theDevelopers.MicroMarket.dto.Productos;

import lombok.Data;

@Data
public class ProductoRequest {

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Long categoriaId;

}
