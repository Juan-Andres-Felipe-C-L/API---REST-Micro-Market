package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "Venta")
@Data
public class Venta {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(name = "id_empleado")
    private Long idEmpleado;  

    @Column(name = "subtotal")
    private float subtotal;

    @Column(name = "iva")
    private float iva;

    @Column(name = "total")
    private float total;

    
}
