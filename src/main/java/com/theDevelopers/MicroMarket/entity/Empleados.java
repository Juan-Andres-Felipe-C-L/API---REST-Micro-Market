package com.theDevelopers.MicroMarket.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;
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

    @Column(name = "cedula_empleado")
    private String cedulaEmpleado;

    @Column(name = "nombre_empleado")
    private String nombreEmpleado;

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