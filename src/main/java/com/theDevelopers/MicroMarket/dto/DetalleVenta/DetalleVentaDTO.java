package com.theDevelopers.MicroMarket.dto.DetalleVenta;
import lombok.Data;
import java.util.Date;



@Data
public class DetalleVentaDTO {


    private Long id;
    private String nombreProducto;
    private Date fechaVenta;
    private Long cantidadProductos;
    private float subtotal;
    private float iva;
    private float total;
}
