package com.theDevelopers.MicroMarket.dto.Venta;

import lombok.Data;

@Data
public class VentaDTO {

    private Long id;
    private Long idEmpleado;

    private float subtotal;
    private float iva;
    private float total;

}
