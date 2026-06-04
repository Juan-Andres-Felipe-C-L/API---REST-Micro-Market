package com.theDevelopers.MicroMarket.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "DetalleVenta")
@Data
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    private Long idVenta;

    private Long idProducto;

    private Date fechaVenta;

    private Integer cantidadProductos;

    private float subtotal;
}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idVenta;

    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Column(name = "cantidad_productos")
    private Long cantidadProductos;

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "iva")
    private float iva;
    
    @Column(name = "total")
    private float total;
}
