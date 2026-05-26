package com.theDevelopers.MicroMarket.dto;

import lombok.Data;
import java.util.Date;

@Data
public class EmpleadosDTO {
    private String cedula;
    private String nombre_empleado;
    private String cargo;
    private Date fecha_ingreso;
    private float salario; 
}
