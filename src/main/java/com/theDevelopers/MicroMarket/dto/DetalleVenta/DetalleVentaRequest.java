package com.theDevelopers.MicroMarket.dto.DetalleVenta;

import lombok.Data;
import java.util.Date;

@Data
public class DetalleVentaRequest {
    private String nombreProducto;
    private Date fechaVenta;
    private Long cantidadProductos;
}
