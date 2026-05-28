package com.theDevelopers.MicroMarket.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DetalleVentaRequest {
    private String nombre_producto;
    private Date fecha_venta;
    private Long cantidad_productos;
}
