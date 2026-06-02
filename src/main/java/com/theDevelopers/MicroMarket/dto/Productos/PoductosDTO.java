package com.theDevelopers.MicroMarket.dto.Productos;

import lombok.Data;

@Data
public class PoductosDTO {

    private Long idProducto;
    private String nombreProducto;  
    private int cantidad;
    private float precio;
    private Long idCategoria;
    private boolean activo;
    private String codigoBarras;  
}
