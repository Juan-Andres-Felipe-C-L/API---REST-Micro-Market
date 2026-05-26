package com.theDevelopers.MicroMarket.dto;

import lombok.Data;

@Data
public class PoductosDTO {

    private String nombre_producto;  
    private int cantidad;
    private float precio;
    private Long id_categoria;
    private boolean activo;
    private String codigo_barras;  
}
