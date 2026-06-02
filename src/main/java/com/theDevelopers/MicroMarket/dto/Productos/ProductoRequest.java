package com.theDevelopers.MicroMarket.dto.Productos;

import lombok.Data;

@Data
public class ProductoRequest {

    private String nombre;
    private double precio;
    private Long IdCategoria;
    private String codigoBarras;
    private int cantidad;
    private boolean activo;

}
