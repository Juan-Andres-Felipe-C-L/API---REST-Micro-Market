package com.theDevelopers.MicroMarket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;
import com.theDevelopers.MicroMarket.dto.Productos.ProductosDTO;
import com.theDevelopers.MicroMarket.dto.Productos.ProductoRequest;
import java.util.List;

import com.theDevelopers.MicroMarket.service.ProductosService;

import lombok.RequiredArgsConstructor;

@RestController // Controlador de tipo Rest
@RequestMapping("/productos") // Mapeo de solicitud para esta clase // Constructor
@RequiredArgsConstructor
public class ProductosController {

    // Inyección de dependencias
    private final ProductosService productosService;



    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createProducto(@RequestBody ProductoRequest request) {
        try {
            MessageResponseDTO response = productosService.createProducto(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get-productos")
    public List<ProductosDTO> getProductos() {
        List<ProductosDTO> response = productosService.getProductos();
        return response;
    }

    @GetMapping("/get-producto/{id}")
    public HttpGlobalResponse<ProductosDTO> getProducto(@PathVariable Long id) {
        HttpGlobalResponse<ProductosDTO> response = productosService.getProductoById(id);
        return response;
    }

    @DeleteMapping("/delete-producto/{id}")
    public MessageResponseDTO deleteProducto(@PathVariable Long id) {
        MessageResponseDTO response = productosService.deleteProducto(id);
        return response;
    }

    @PutMapping("/update-producto/{id}")
    public HttpGlobalResponse<ProductosDTO> updateProducto(@PathVariable Long id, @RequestBody ProductoRequest request) {
        HttpGlobalResponse<ProductosDTO> response = productosService.upDateProducto(id, request);
        return response;
    }
}
