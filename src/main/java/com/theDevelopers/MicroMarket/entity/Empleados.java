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
    private Long idEmpleado;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "salario")
    private float salario;
    
}
