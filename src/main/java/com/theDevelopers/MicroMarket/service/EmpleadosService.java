package com.theDevelopers.MicroMarket.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.theDevelopers.MicroMarket.repository.EmpleadosRepository;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.dto.Empleados.*;
import com.theDevelopers.MicroMarket.entity.*;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;




@Service
@RequiredArgsConstructor

public class EmpleadosService {

    private final EmpleadosRepository empleadosRepository;

    public MessageResponseDTO createEmpleado(EmpleaosRequest request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Empleados> empleadoFound = empleadosRepository.findByCedula(request.getCedulaEmpleado());

        if(request.getCedulaEmpleado() == null ||
           request.getCedulaEmpleado().trim().isEmpty()) {

           response.setMessage("La cédula es obligatoria.");
           return response;
}
        if(empleadoFound.isPresent()) {
            response.setMessage("Ya existe un empleado con esa cédula.");
            return response;
        }

        if(
          !request.getCargo().equalsIgnoreCase("ADMINISTRADOR") &&
          !request.getCargo().equalsIgnoreCase("CAJERO") &&
          !request.getCargo().equalsIgnoreCase("AUXILIAR")
){
        response.setMessage("Cargo no permitido.");
           return response;
}

        response.setMessage("Registro de empleado exitoso.");
        Empleados empleado = new Empleados();
        empleado.setCedulaEmpleado(request.getCedulaEmpleado());
        empleado.setNombreEmpleado(request.getNombreEmpleado());
        empleado.setCargo(request.getCargo());
        empleado.setFechaIngreso(request.getFechaIngreso());
        empleado.setSalario(request.getSalario());
        empleadosRepository.save(empleado);
        return response;
    }

    public List<EmpleadosDTO> getEmpleados() {
        List<EmpleadosDTO> listEmpleados = new ArrayList<>();
        List<Empleados> empleadosFound = empleadosRepository.findAll();

        for (Empleados empleado : empleadosFound) {
            EmpleadosDTO empleadoNew = new EmpleadosDTO();
            empleadoNew.setId(empleado.getIdEmpleado());
            empleadoNew.setCedula(empleado.getCedulaEmpleado());
            empleadoNew.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoNew.setCargo(empleado.getCargo());
            empleadoNew.setFechaIngreso(empleado.getFechaIngreso());
            empleadoNew.setSalario(empleado.getSalario());
                
            listEmpleados.add(empleadoNew);
        }

        return listEmpleados;
    }

    public HttpGlobalResponse<EmpleadosDTO> getEmpleadoById(Long id) {
        HttpGlobalResponse<EmpleadosDTO> response = new HttpGlobalResponse<>();
        Optional<Empleados> empleadoFound = empleadosRepository.findById(id);

        if (empleadoFound.isEmpty()) {
            response.setMessage("No se encontró un empleado con el ID proporcionado.");
            return response;
        }

        Empleados empleado = empleadoFound.get();
        EmpleadosDTO empleadoDTO = new EmpleadosDTO();
        empleadoDTO.setId(empleado.getIdEmpleado());
        empleadoDTO.setCedula(empleado.getCedulaEmpleado());
        empleadoDTO.setNombreEmpleado(empleado.getNombreEmpleado());
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setFechaIngreso(empleado.getFechaIngreso());
        empleadoDTO.setSalario(empleado.getSalario());

        response.setMessage("Empleado encontrado exitosamente.");
        response.setData(empleadoDTO);
        return response;
    }

    public MessageResponseDTO deleteEmpleado(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Empleados> empleadoFound = empleadosRepository.findById(id);

        if (empleadoFound.isEmpty()) {
            response.setMessage("No se encontró un empleado con el ID proporcionado.");
            return response;
        }

        empleadosRepository.deleteById(id);
        response.setMessage("Empleado eliminado exitosamente.");
        return response;
    }


    public HttpGlobalResponse<EmpleadosDTO> updateEmpleado(Long id, EmpleaosRequest request) {
        HttpGlobalResponse<EmpleadosDTO> response = new HttpGlobalResponse<>();
        Optional<Empleados> empleadoFound = empleadosRepository.findById(id);

        if(empleadoFound.isEmpty()){
            response.setMessage("No se encontró un empleado con el ID proporcionado.");
            return response;
        }

          if(
          !request.getCargo().equalsIgnoreCase("ADMINISTRADOR") &&
          !request.getCargo().equalsIgnoreCase("CAJERO") &&
          !request.getCargo().equalsIgnoreCase("AUXILIAR")
        ){
        response.setMessage("Cargo no permitido.");
           return response;
        }


        Empleados empleado = empleadoFound.get();
        empleado.setCedulaEmpleado(request.getCedulaEmpleado());
        empleado.setNombreEmpleado(request.getNombreEmpleado());
        empleado.setCargo(request.getCargo());
        empleado.setFechaIngreso(request.getFechaIngreso());
        empleado.setSalario(request.getSalario());

        empleadosRepository.save(empleado);

        EmpleadosDTO empleadoDTO = new EmpleadosDTO();
        empleadoDTO.setId(empleado.getIdEmpleado());
        empleadoDTO.setCedula(empleado.getCedulaEmpleado());
        empleadoDTO.setNombreEmpleado(empleado.getNombreEmpleado());
        empleadoDTO.setCargo(empleado.getCargo());
        empleadoDTO.setFechaIngreso(empleado.getFechaIngreso());
        empleadoDTO.setSalario(empleado.getSalario());

        response.setMessage("Empleado actualizado exitosamente.");
        response.setData(empleadoDTO);
        return response;
    }

     public List<EmpleadosDTO> getEmpleadosByCargo(String cargo){

    List<EmpleadosDTO> lista = new ArrayList<>();

    List<Empleados> empleados = empleadosRepository.findByCargo(cargo);
           
    for(Empleados empleado : empleados){

        EmpleadosDTO dto = new EmpleadosDTO();

        dto.setId(empleado.getIdEmpleado());
        dto.setCedula(empleado.getCedulaEmpleado());
        dto.setNombreEmpleado(empleado.getNombreEmpleado());
        dto.setCargo(empleado.getCargo());
        dto.setFechaIngreso(empleado.getFechaIngreso());
        dto.setSalario(empleado.getSalario());

        lista.add(dto);
    }

    return lista;
}




    
}
