package com.theDevelopers.MicroMarket.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.theDevelopers.MicroMarket.repository.ProveedoresRepository;
import com.theDevelopers.MicroMarket.dto.Proveedores.ProveedoresRequest;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import java.util.Optional;

import com.theDevelopers.MicroMarket.entity.Empleados;
import com.theDevelopers.MicroMarket.entity.Proveedores;
import java.util.List;
import java.util.ArrayList;
import com.theDevelopers.MicroMarket.dto.Proveedores.ProveedoresDTO;
import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;
import com.theDevelopers.MicroMarket.dto.Empleados.EmpleadosDTO;
import com.theDevelopers.MicroMarket.dto.Empleados.EmpleaosRequest;
import com.theDevelopers.MicroMarket.repository.EmpleadosRepository;







@Service
@RequiredArgsConstructor

public class ProveedoresServicie {

    private final ProveedoresRepository proveedoresRepository;

    public MessageResponseDTO createProveedor(ProveedoresRequest request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Proveedores> proveedorFound = proveedoresRepository.findByNit(request.getNit());

        if(request.getNit() == null ||
           request.getNit().trim().isEmpty()) {

           response.setMessage("El NIT es obligatorio.");
           return response;
           }

        if(proveedorFound.isPresent()) {
            response.setMessage("El proveedor con NIT " + request.getNit() + " ya existe.");
            return response;

        }

        response.setMessage("Registro de proveedor exitoso.");
        Proveedores proveedor = new Proveedores();
        proveedor.setNit(request.getNit());
        proveedor.setNombreProveedor(request.getNombreProveedor());

        proveedoresRepository.save(proveedor);
        return response;
        }

        public List<ProveedoresDTO> getProveedores() {
            List<Proveedores> proveedores = proveedoresRepository.findAll();
            List<ProveedoresDTO> proveedoresDTOList = new ArrayList<>();

            for (Proveedores proveedor : proveedores) {
                ProveedoresDTO dto = new ProveedoresDTO();
                dto.setNit(proveedor.getNit());
                dto.setNombreProveedor(proveedor.getNombreProveedor());
                proveedoresDTOList.add(dto);
            }

            return proveedoresDTOList;
        }

        public HttpGlobalResponse<ProveedoresDTO> getProveedorByNit(String nit) {
            HttpGlobalResponse<ProveedoresDTO> response = new HttpGlobalResponse<>();
            Optional<Proveedores> proveedorFound = proveedoresRepository.findByNit(nit);

            if (proveedorFound.isPresent()) {
                Proveedores proveedor = proveedorFound.get();
                ProveedoresDTO dto = new ProveedoresDTO();
                dto.setNit(proveedor.getNit());
                dto.setNombreProveedor(proveedor.getNombreProveedor());
                response.setData(dto);
                response.setMessage("Proveedor encontrado.");
            } else {
                response.setMessage("Proveedor con NIT " + nit + " no encontrado.");
            }

            return response;
        }

        public MessageResponseDTO deleteProveedorByNit(String nit) {
            MessageResponseDTO response = new MessageResponseDTO();
            Optional<Proveedores> proveedorFound = proveedoresRepository.findByNit(nit);

            if (proveedorFound.isPresent()) {
                proveedoresRepository.delete(proveedorFound.get());
                response.setMessage("Proveedor con NIT " + nit + " eliminado exitosamente.");
            } else {
                response.setMessage("Proveedor con NIT " + nit + " no encontrado.");
            }

            return response;
        }

         public HttpGlobalResponse<EmpleadosDTO> updateEmpleado(Long id, EmpleaosRequest request) {
        HttpGlobalResponse<EmpleadosDTO> response = new HttpGlobalResponse<>();
        Optional<Empleados> empleadoFound = empleadosRepository.findById(id);

        if(empleadoFound.isEmpty()){
            response.setMessage("No se encontró un empleado con el ID proporcionado.");
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








    
}
