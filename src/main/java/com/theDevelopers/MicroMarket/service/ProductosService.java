package com.theDevelopers.MicroMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.dto.Productos.ProductoRequest;
import com.theDevelopers.MicroMarket.dto.Productos.ProductosDTO;
import com.theDevelopers.MicroMarket.entity.Productos;
import com.theDevelopers.MicroMarket.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductosService {
    
    private final ProductosRepository productosRepository;

    public MessageResponseDTO createProducto(ProductoRequest request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Productos> productoFound = productosRepository.findByCodigoBarras(request.getCodigoBarras());

        if(productoFound.isPresent()) {
            response.setMessage("Ya existe un producto con ese código de barras.");
            return response;
        }

        response.setMessage("Registro de producto exitoso.");
        Productos producto = new Productos();
        producto.setNombreProducto(request.getNombre());
        producto.setCodigoBarras(request.getCodigoBarras());
        producto.setCantidad(request.getCantidad());
        producto.setPrecio(request.getPrecio());
        producto.setIdCategoria(request.getIdCategoria());
        producto.setActivo(request.isActivo());
        productosRepository.save(producto);

        return response;
    }

    public List<ProductosDTO> getProductos() {
        List<ProductosDTO> listProductos = new ArrayList<>();
        List<Productos> productosFound = productosRepository.findAll();

        for (Productos producto : productosFound) {
            ProductosDTO productoNew = new ProductosDTO();
            productoNew.setIdProducto(producto.getIdProducto());
            productoNew.setNombreProducto(producto.getNombreProducto());
            productoNew.setCodigoBarras(producto.getCodigoBarras());
            productoNew.setCantidad(producto.getCantidad());
            productoNew.setPrecio(producto.getPrecio());
            productoNew.setIdCategoria(producto.getIdCategoria());
            productoNew.setActivo(producto.isActivo());
                
            listProductos.add(productoNew);
        }

        return listProductos;
    }

    public HttpGlobalResponse<ProductosDTO> getProductoById(Long id) {
        HttpGlobalResponse<ProductosDTO> response = new HttpGlobalResponse<>();
        Optional<Productos> productoFound = productosRepository.findById(id);

        if (productoFound.isEmpty()) {
            response.setMessage("Producto no encontrado.");
            return response;
        }

        Productos producto = productoFound.get();

        ProductosDTO productoFinal = new ProductosDTO();
        productoFinal.setIdProducto(producto.getIdProducto());
        productoFinal.setNombreProducto(producto.getNombreProducto());
        productoFinal.setCodigoBarras(producto.getCodigoBarras());
        productoFinal.setCantidad(producto.getCantidad());
        productoFinal.setPrecio(producto.getPrecio());
        productoFinal.setIdCategoria(producto.getIdCategoria());
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

    public HttpGlobalResponse<ProductosDTO> upDateProducto(Long id, ProductoRequest request) {
        HttpGlobalResponse<ProductosDTO> response = new HttpGlobalResponse<>();

        Optional<Productos> productoFound = productosRepository.findById(id);

        if(productoFound.isEmpty()){
            response.setMessage("Producto no encontrado.");
            return response;
        }

        Productos producto = productoFound.get();
        producto.setNombreProducto(request.getNombre());
        producto.setCodigoBarras(request.getCodigoBarras());
        producto.setCantidad(request.getCantidad());
        producto.setPrecio(request.getPrecio());
        producto.setIdCategoria(request.getIdCategoria());
        producto.setActivo(request.isActivo());

        productosRepository.save(producto);

        ProductosDTO productoDTO = new ProductosDTO();
        productoDTO.setIdProducto(producto.getIdProducto());
        productoDTO.setNombreProducto(producto.getNombreProducto());
        productoDTO.setCodigoBarras(producto.getCodigoBarras());
        productoDTO.setCantidad(producto.getCantidad());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setIdCategoria(producto.getIdCategoria());
        productoDTO.setActivo(producto.isActivo());

        response.setMessage("Producto actualizado correctamente.");
        response.setData(productoDTO);
        return response;
    }
}