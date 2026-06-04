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
    private long idVenta;

    @Column(name = "id_producto")
    private long idProducto;

    @Column(name = "fecha_venta")
    private Date fechaVenta;

    @Column(name = "cantidad_productos")
    private Integer cantidadProductos;

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "iva")
    private float iva;
    
    @Column(name = "total")
    private float total;
}
