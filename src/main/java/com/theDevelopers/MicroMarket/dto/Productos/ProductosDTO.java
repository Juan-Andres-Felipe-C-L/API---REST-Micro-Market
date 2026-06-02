package com.theDevelopers.MicroMarket.dto.Productos;

import lombok.Data;

@Data
public class ProductosDTO {

    private Long idProducto;
    private String nombreProducto;  
    private int cantidad;
    private double precio;
    private Long idCategoria;
    private boolean activo;
    private String codigoBarras;  
}
