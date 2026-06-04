package com.theDevelopers.MicroMarket.dto.DetalleVenta;
import lombok.Data;
import java.util.Date;


@Data
public class DetalleVentaDTO {

    private Long idDetalleVenta;
    private Long idVenta;
    private Long idProducto;

    private String nombreProducto;

    private Date fechaVenta;

    private Integer cantidadProductos;

    private float subtotal;
}
