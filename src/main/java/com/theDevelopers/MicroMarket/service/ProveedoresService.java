package com.theDevelopers.MicroMarket.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.theDevelopers.MicroMarket.repository.ProveedoresRepository;
import com.theDevelopers.MicroMarket.dto.Proveedores.ProveedoresRequest;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import java.util.Optional;
import com.theDevelopers.MicroMarket.entity.Proveedores;
import java.util.List;
import java.util.ArrayList;
import com.theDevelopers.MicroMarket.dto.Proveedores.ProveedoresDTO;
import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;







@Service
@RequiredArgsConstructor

public class ProveedoresService {

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

         public HttpGlobalResponse<ProveedoresDTO> updateProveedor(String nit, ProveedoresRequest request) {
        HttpGlobalResponse<ProveedoresDTO> response = new HttpGlobalResponse<>();
        Optional<Proveedores> proveedorFound = proveedoresRepository.findByNit(nit);

        if(proveedorFound.isEmpty()){
            response.setMessage("No se encontró un proveedor con el NIT proporcionado.");
            return response;
        }

        Proveedores proveedor = proveedorFound.get();
        proveedor.setNombreProveedor(request.getNombreProveedor());

        proveedoresRepository.save(proveedor);

        ProveedoresDTO proveedorDTO = new ProveedoresDTO();
        proveedorDTO.setNit(proveedor.getNit());
        proveedorDTO.setNombreProveedor(proveedor.getNombreProveedor());

        response.setMessage("Proveedor actualizado exitosamente.");
        response.setData(proveedorDTO);
        return response;
    }
    








    
}
