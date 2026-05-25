package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Proveedores")
@Data

public class Proveedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proveedor;

    @Column(name ="nit")
    private String nit;

    @Column(name = "nombre_proveedor")
    private String nombre_proveedor;
    
}