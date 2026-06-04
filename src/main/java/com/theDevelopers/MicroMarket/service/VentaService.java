package com.theDevelopers.MicroMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.dto.Venta.VentaDTO;
import com.theDevelopers.MicroMarket.dto.Venta.VentaRequest;
import com.theDevelopers.MicroMarket.entity.Empleados;
import com.theDevelopers.MicroMarket.entity.Productos;
import com.theDevelopers.MicroMarket.entity.Venta;
import com.theDevelopers.MicroMarket.repository.EmpleadosRepository;
import com.theDevelopers.MicroMarket.repository.ProductosRepository;
import com.theDevelopers.MicroMarket.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ProductosRepository productosRepository;
    private final EmpleadosRepository empleadosRepository;

    public MessageResponseDTO createVenta(VentaRequest request) {

        MessageResponseDTO response = new MessageResponseDTO();

        if(request.getIdEmpleado() == null){
            response.setMessage("El empleado es obligatorio.");
            return response;
        }

        if(request.getIdProducto() == null){
            response.setMessage("El producto es obligatorio.");
            return response;
        }

        if(request.getCantidad() == null || request.getCantidad() <= 0){
            response.setMessage("La cantidad debe ser mayor a 0.");
            return response;
        }

        Optional<Empleados> empleadoFound =
                empleadosRepository.findById(
                        request.getIdEmpleado());

        if(empleadoFound.isEmpty()){
            response.setMessage("Empleado no encontrado.");
            return response;
        }

        Optional<Productos> productoFound =
                productosRepository.findById(
                        request.getIdProducto());

        if(productoFound.isEmpty()){
            response.setMessage("Producto no encontrado.");
            return response;
        }

        Productos producto = productoFound.get();

        if(producto.getCantidad() < request.getCantidad()){

            response.setMessage(
                    "Stock insuficiente para realizar la venta.");

            return response;
        }

        float subtotal = (float) (producto.getPrecio() * request.getCantidad());
               
        float iva = subtotal * 0.19f;

        float total = subtotal + iva;

        producto.setCantidad(
                producto.getCantidad() - request.getCantidad().intValue());
                       

        productosRepository.save(producto);

        Venta venta = new Venta();

        venta.setIdEmpleado(request.getIdEmpleado());
        venta.setSubtotal(subtotal);
        venta.setIva(iva);
        venta.setTotal(total);

        ventaRepository.save(venta);

        response.setMessage("Venta registrada exitosamente.");

        return response;
    }

    public List<VentaDTO> getVentas() {

        List<VentaDTO> listVentas = new ArrayList<>();

        List<Venta> ventasFound =
                ventaRepository.findAll();

        for(Venta venta : ventasFound){

            VentaDTO dto = new VentaDTO();

            dto.setId(venta.getIdVenta());
            dto.setIdEmpleado(venta.getIdEmpleado());
            dto.setSubtotal(venta.getSubtotal());
            dto.setIva(venta.getIva());
            dto.setTotal(venta.getTotal());

            listVentas.add(dto);
        }

        return listVentas;
    }

    public HttpGlobalResponse<VentaDTO> getVentaById(Long id){

        HttpGlobalResponse<VentaDTO> response = new HttpGlobalResponse<>();
        Optional<Venta> ventaFound = ventaRepository.findById(id);       

        
                

        if(ventaFound.isEmpty()){

            response.setMessage(
                    "Venta no encontrada.");

            return response;
        }

        Venta venta = ventaFound.get();

        VentaDTO dto = new VentaDTO();

        dto.setId(venta.getIdVenta());
        dto.setIdEmpleado(venta.getIdEmpleado());
        dto.setSubtotal(venta.getSubtotal());
        dto.setIva(venta.getIva());
        dto.setTotal(venta.getTotal());

        response.setMessage(
                "Venta encontrada exitosamente.");

        response.setData(dto);

        return response;
    }

    public MessageResponseDTO deleteVenta(Long id){

        MessageResponseDTO response =
                new MessageResponseDTO();

        Optional<Venta> ventaFound =
                ventaRepository.findById(id);

        if(ventaFound.isEmpty()){

            response.setMessage(
                    "Venta no encontrada.");

            return response;
        }

        ventaRepository.deleteById(id);

        response.setMessage(
                "Venta eliminada exitosamente.");

        return response;
    }

    public HttpGlobalResponse<VentaDTO> updateVenta(
            Long id,
            VentaRequest request){

        HttpGlobalResponse<VentaDTO> response =
                new HttpGlobalResponse<>();

        Optional<Venta> ventaFound =
                ventaRepository.findById(id);

        if(ventaFound.isEmpty()){

            response.setMessage(
                    "Venta no encontrada.");

            return response;
        }

        Venta venta = ventaFound.get();

        venta.setIdEmpleado(
                request.getIdEmpleado());

        ventaRepository.save(venta);

        VentaDTO dto = new VentaDTO();

        dto.setId(venta.getIdVenta());
        dto.setIdEmpleado(venta.getIdEmpleado());
        dto.setSubtotal(venta.getSubtotal());
        dto.setIva(venta.getIva());
        dto.setTotal(venta.getTotal());

        response.setMessage(
                "Venta actualizada exitosamente.");

        response.setData(dto);

        return response;
    }
}
