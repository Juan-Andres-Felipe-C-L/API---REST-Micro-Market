package com.theDevelopers.MicroMarket.dto.Empleados;

import lombok.Data;
import java.util.Date;

@Data
public class EmpleaosRequest {

    private String cedulaEmpleado;
    private String nombreEmpleado;
    private String cargo;
    private Date fechaIngreso;
    private float salario;

}
