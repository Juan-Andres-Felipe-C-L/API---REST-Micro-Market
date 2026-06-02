package com.theDevelopers.MicroMarket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theDevelopers.MicroMarket.dto.HttpGlobalResponse;
import com.theDevelopers.MicroMarket.dto.MessageResponseDTO;
import com.theDevelopers.MicroMarket.dto.Categoria.CategoriasDTO;
import com.theDevelopers.MicroMarket.dto.Categoria.CategoriasRequest;
import com.theDevelopers.MicroMarket.entity.Categorias;
import com.theDevelopers.MicroMarket.repository.CategoriasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriasService {
    
    private final CategoriasRepository categoriasRepository;

    public MessageResponseDTO createCategoria(CategoriasRequest request) {
        MessageResponseDTO response = new MessageResponseDTO();
        Optional<Categorias> categoriaFound = categoriasRepository.findByNombreCategoria(request.getNombreCategoria());

        if(categoriaFound.isPresent()) {
            response.setMessage("Ya existe una categoría con ese nombre.");
            return response;
        }

        response.setMessage("Registro de categoría exitoso.");
        Categorias categoria = new Categorias();
        categoria.setNombreCategoria(request.getNombreCategoria());
        categoriasRepository.save(categoria);

        return response;
    }

    public List<CategoriasDTO> getCategorias() {
        List<CategoriasDTO> listCategorias = new ArrayList<>();
        List<Categorias> categoriasFound = categoriasRepository.findAll();

        for (Categorias categoria : categoriasFound) {
            CategoriasDTO categoriaNew = new CategoriasDTO();
            categoriaNew.setId(categoria.getIdCategoria());
            categoriaNew.setNombreCategoria(categoria.getNombreCategoria());
                
            listCategorias.add(categoriaNew);
        }

        return listCategorias;
    }

    public HttpGlobalResponse<CategoriasDTO> getCategoriaById(Long id) {
        HttpGlobalResponse<CategoriasDTO> response = new HttpGlobalResponse<>();
        Optional<Categorias> categoriaFound = categoriasRepository.findById(id);

        if (categoriaFound.isEmpty()) {
            response.setMessage("Categoría no encontrada.");
            return response;
        }

        Categorias categoria = categoriaFound.get();

        CategoriasDTO categoriaFinal = new CategoriasDTO();
        categoriaFinal.setId(categoria.getIdCategoria());
        categoriaFinal.setNombreCategoria(categoria.getNombreCategoria());
        
        response.setMessage("Categoría encontrada.");
        response.setData(categoriaFinal);

        return response;
    }

    public MessageResponseDTO deleteCategoria(Long id) {
        MessageResponseDTO response = new MessageResponseDTO();

        Optional<Categorias> categoriaFound = categoriasRepository.findById(id);

        if (categoriaFound.isEmpty()) {
            response.setMessage("Categoría no encontrada.");
            return response;
        }

        categoriasRepository.deleteById(id);
        response.setMessage("Categoría eliminada exitosamente.");
        return response;
    }

    public HttpGlobalResponse<CategoriasDTO> upDateProducto(Long id, CategoriasRequest request) {
        HttpGlobalResponse<CategoriasDTO> response = new HttpGlobalResponse<>();

        Optional<Categorias> categoriaFound = categoriasRepository.findById(id);

        if(categoriaFound.isEmpty()){
            response.setMessage("Categoría no encontrada.");
            return response;
        }

        Categorias categoria = categoriaFound.get();
        categoria.setNombreCategoria(request.getNombreCategoria());    

        categoriasRepository.save(categoria);

        CategoriasDTO categoriasDTO = new CategoriasDTO();
        categoriasDTO.setId(categoria.getIdCategoria());
        categoriasDTO.setNombreCategoria(categoria.getNombreCategoria());
        
        response.setMessage("Categoría actualizada correctamente.");
        response.setData(categoriasDTO);
        return response;
    }
}