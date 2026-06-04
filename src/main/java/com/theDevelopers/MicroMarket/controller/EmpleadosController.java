package com.theDevelopers.MicroMarket.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.theDevelopers.MicroMarket.service.EmpleadosService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.theDevelopers.MicroMarket.dto.Empleados.EmpleaosRequest;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.theDevelopers.MicroMarket.dto.Empleados.EmpleadosDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor

public class EmpleadosController {

    private final EmpleadosService empleadosService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createEmpleado(@RequestBody EmpleaosRequest request) {
        MessageResponseDTO response = empleadosService.createEmpleado(request);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list-empleados")
    public ResponseEntity<List<EmpleadosDTO>> getEmpleados() {
        List<EmpleadosDTO> empleados = empleadosService.getEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/list-empleados-cargo")
    public ResponseEntity<List<EmpleadosDTO>> getEmpleadosByCargo(String cargo) {
        List<EmpleadosDTO> empleados = empleadosService.getEmpleadosByCargo(cargo);
        return ResponseEntity.ok(empleados);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponseDTO> deleteEmpleado(@PathVariable Long id) {
        MessageResponseDTO response = empleadosService.deleteEmpleado(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public HttpGlobalResponse<EmpleadosDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleaosRequest request) {
        return empleadosService.updateEmpleado(id, request);
    }
  
}
