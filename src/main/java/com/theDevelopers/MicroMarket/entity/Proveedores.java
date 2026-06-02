package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Proveedores")
@Data

public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(name ="nit")
    private String nit;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    
}