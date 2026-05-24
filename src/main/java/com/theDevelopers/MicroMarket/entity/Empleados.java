package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name = "Empleados")
@Data

public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_empleado;

    @Column(name = "cedula")
    private int cedula;

    @Column(name = "nombre_empleado")
    private String nombre_empleado;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "fecha_ingreso")
    private Date fecha_ingreso;

    @Column(name = "salario")
    private float salario;
    
}