package com.theDevelopers.MicroMarket.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.entity.DetalleVenta;
import com.theDevelopers.MicroMarket.repository.VentaRepository;
import java.util.Optional;
import com.theDevelopers.MicroMarket.dto.DetalleVenta.DetalleVentaRequest;
import com.theDevelopers.MicroMarket.repository.DetalleVentaRepository;
import com.theDevelopers.MicroMarket.repository.ProductosRepository;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import com.theDevelopers.MicroMarket.entity.Productos;
import com.theDevelopers.MicroMarket.entity.Venta;
import com.theDevelopers.MicroMarket.dto.DetalleVenta.DetalleVentaDTO;




@Service
@RequiredArgsConstructor
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final VentaRepository ventaRepository;
    private final ProductosRepository productosRepository;  



    public MessageResponseDTO createDetalleVenta( DetalleVentaRequest request){
           
        MessageResponseDTO response = new MessageResponseDTO();   
        Optional<Venta> ventaFound = ventaRepository.findById( request.getIdVenta());
               
        if(ventaFound.isEmpty()){ response.setMessage("La venta no existe.");       
            return response;
        }

        Optional<Productos> productoFound = productosRepository.findById( request.getIdProducto());            
        if(productoFound.isEmpty()){

            response.setMessage("El producto no existe.");
            return response;
        }

        Productos producto = productoFound.get();
        float subtotal =(float)(producto.getPrecio()* request.getCantidadProductos());       
        DetalleVenta detalle =new DetalleVenta();
                
        detalle.setIdVenta(request.getIdVenta());
        detalle.setIdProducto(request.getIdProducto());
        detalle.setCantidadProductos( request.getCantidadProductos());
        detalle.setFechaVenta(
                new Date());
 detalle.setSubtotal(subtotal);
        detalleVentaRepository.save(detalle);

          response.setMessage(
                "Detalle de venta registrado.");

        return response;
          }
        public List<DetalleVentaDTO> getEmpleados() {
        List<DetalleVentaDTO> listEmpleados = new ArrayList<>();
        List<DetalleVenta> empleadosFound = detalleVentaRepository.findAll();

        for (DetalleVenta empleado : empleadosFound) {
            DetalleVentaDTO empleadoNew = new DetalleVentaDTO();
                empleadoNew.setIdDetalleVenta(empleado.getIdDetalleVenta());
                empleadoNew.setIdVenta(empleado.getIdVenta());
                empleadoNew.setIdProducto(empleado.getIdProducto());
                empleadoNew.setCantidadProductos(empleado.getCantidadProductos());
                empleadoNew.setFechaVenta(empleado.getFechaVenta());
            listEmpleados.add(empleadoNew);
        }

        return listEmpleados;

        }

        public MessageResponseDTO deleteDetalleVenta(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<DetalleVenta> detalleFound = detalleVentaRepository.findById(id);
        if (detalleFound.isEmpty()) {
            response.setMessage("No se encontró un detalle de venta con el ID proporcionado.");
            return response;
        }

        detalleVentaRepository.delete(detalleFound.get());
        response.setMessage("Detalle de venta con ID " + id + " eliminado exitosamente.");

        return response;
        }

        


    }

