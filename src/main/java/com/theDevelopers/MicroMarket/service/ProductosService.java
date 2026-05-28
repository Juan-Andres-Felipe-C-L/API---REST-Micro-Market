package com.theDevelopers.MicroMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.entity.Productos;
import com.theDevelopers.MicroMarket.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductosService {
    
    private final ProductosRepository productosRepository;

    public MessageResponseDTO createProducto(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Productos> productoFound = productosRepository.findByCodigo_barras(request.getCodigo_barras());

        if(productoFound.isPresent()) {
            response.setMessage("Ya existe un producto con ese código de barras.");
            return response;
        }

        response.setMessage("Registro de producto exitoso.");
        Productos producto = new Productos();
        producto.setNombre_producto(request.getNombre_producto());
        producto.setCodigo_barras(request.getCodigo_barras());
        producto.setCantidad(request.getCantidad());
        producto.setPrecio(request.getPrecio());
        producto.setId_categoria(request.getId_categoria());
        producto.setActivo(request.isActivo());
        productosRepository.save(producto);

        return response;
    }

    public List<ProductosResponseDTO> getProductos() {
        List<ProductosResponseDTO> listProductos = new ArrayList<>();
        List<Productos> productosFound = productosRepository.findAll();

        for (Productos producto : productosFound) {
            ProductosResponseDTO productoNew = new ProductosResponseDTO();
            productoNew.setId_producto(producto.getId_producto());
            productoNew.setNombre_producto(producto.getNombre_producto());
            productoNew.setCodigo_barras(producto.getCodigo_barras());
            productoNew.setCantidad(producto.getCantidad());
            productoNew.setPrecio(producto.getPrecio());
            productoNew.setId_categoria(producto.getId_categoria());
            productoNew.setActivo(producto.isActivo());
                
            listProductos.add(productoNew);
        }

        return listProductos;
    }

    public GlobalResponse<ProductosResponseDTO> getProductoById(Long id) {
        GlobalResponse<ProductosResponseDTO> response = new GlobalResponse<>();
        Optional<Productos> productoFound = productosRepository.findById(id);

        if (productoFound.isEmpty()) {
            response.setMessage("Producto no encontrado.");
            return response;
        }

        Productos producto = productoFound.get();

        ProductosResponseDTO productoFinal = new ProductosResponseDTO;
        productoFinal.setId_producto(producto.getId_producto());
        productoFinal.setNombre_producto(producto.getNombre_producto());
        productoFinal.setCodigo_barras(producto.getCodigo_barras());
        productoFinal.setCantidad(producto.getCantidad());
        productoFinal.setPrecio(producto.getPrecio());
        productoFinal.setId_categoria(producto.getId_categoria());
        productoFinal.setActivo(producto.isActivo());
    
        response.setMessage("Producto encontrado.");
        response.setData(productoFinal);

        return response;
    }

    public MessageResponseDTO deleteProducto(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Productos> productoFound = productosRepository.findById(id);

        if (productoFound.isEmpty()) {
            response.setMessage("Producto no encontrado.");
            return response;
        }

        productosRepository.deleteById(id);
        response.setMessage("Producto eliminado exitosamente.");
        return response;
    }

    public GlobalResponse<ProductosResponseDTO> upDateProducto(Long id, RegisterRequestDTO request) {
        GlobalResponse<ProductosResponseDTO> response = new GlobalResponse<>();

        Optional<Productos> productoFound = productosRepository.findById(id);

        if(productoFound.isEmpty()){
            response.setMessage("Producto no encontrado.");
            return response;
        }

        Productos producto = productoFound.get();
        producto.setNombre_producto(request.getNombre_producto());
        producto.setCodigo_barras(request.getCodigo_barras());
        producto.setCantidad(request.getCantidad());
        producto.setPrecio(request.getPrecio());
        producto.setId_categoria(request.getId_categoria());
        producto.setActivo(request.isActivo());

        productosRepository.save(producto);

        ProductosResponseDTO productoResponseDTO = new ProductosResponseDTO();
        productoResponseDTO.setId_producto(producto.getId_producto());
        productoResponseDTO.setNombre_producto(producto.getNombre_producto());
        productoResponseDTO.setCodigo_barras(producto.getCodigo_barras());
        productoResponseDTO.setCantidad(producto.getCantidad());
        productoResponseDTO.setPrecio(producto.getPrecio());
        productoResponseDTO.setId_categoria(producto.getId_categoria());
        productoResponseDTO.setActivo(producto.isActivo());

        response.setMessage("Producto actualizado correctamente.");
        response.setData(productoResponseDTO);
        return response;
    }
}
