package com.theDevelopers.MicroMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.entity.Categorias;
import com.theDevelopers.MicroMarket.entity.Productos;
import com.theDevelopers.MicroMarket.repository.CategoriasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriasService {
    
    private final CategoriasRepository categoriasRepository;

    public MessageResponseDTO createCategoria(RegisterRequestDTO request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Categorias> categoriaFound = categoriasRepository.findByNombreCategoria(request.getNombre_categoria);

        if(categoriaFound.isPresent()) {
            response.setMessage("Ya existe una categoría con ese nombre.");
            return response;
        }

        response.setMessage("Registro de categoría exitoso.");
        Categorias categoria = new Categorias();
        categoria.setNombre_categoria(request.getNombre_categoria);
        categoriasRepository.save(categoria);

        return response;
    }

    public List<CategoriasResponseDTO> getCategorias() {
        List<CategoriasResponseDTO> listCategorias = new ArrayList<>();
        List<Categorias> categoriasFound = categoriasRepository.findAll();

        for (Categorias categoria : categoriasFound) {
            CategoriasResponseDTO categoriaNew = new CategoriasResponseDTO();
            categoriaNew.setId_categoria(categoria.getId_Categoria);
            categoriaNew.setNombre_categoria(categoria.getNombre_Categoria);
                
            listCategorias.add(categoriaNew);
        }

        return listCategorias;
    }

    public GlobalResponse<CategoriasResponseDTO> getCategoriaById(Long id) {
        GlobalResponse<CategoriasResponseDTO> response = new GlobalResponse<>();
        Optional<Categorias> categoriaFound = categoriasRepository.findById(id);

        if (categoriaFound.isEmpty()) {
            response.setMessage("Categoría no encontrada.");
            return response;
        }

        Categorias producto = categoriaFound.get();

        CategoriasResponseDTO categoriaFinal = new CategoriasResponseDTO;
        productoFinal.setId_producto(producto.getId_producto());
        
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

