package com.theDevelopers.MicroMarket.dto.Empleados;

import lombok.Data;
import java.util.Date;

@Data
public class EmpleadosDTO {

    private Long id;
    private String cedula;
    private String nombreEmpleado;
    private String cargo;
    private Date fechaIngreso;
    private float salario; 
}
