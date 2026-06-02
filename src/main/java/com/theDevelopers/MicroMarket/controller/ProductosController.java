package com.theDevelopers.MicroMarket.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theDevelopers.MicroMarket.service.ProductosService;

import lombok.RequiredArgsConstructor;

@RestController // Controlador de tipo Rest
@RequestMapping("/productos") // Mapeo de solicitud para esta clase // Constructor
@RequiredArgsConstructor
public class ProductosController {

    // Inyección de dependencias
    private final ProductosService productosService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody RegisterRequestDTO request) {
        try {
            MessageResponseDTO response = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/get-users")
    public List<UserResponseDTO> getUsers() {
        List<UserResponseDTO> response = userService.getUsers();
        return response;
    }

    @GetMapping("/get-user/{id}")
    public HttpGlobalResponse<UserResponseDTO> getUser(@PathVariable Integer id) {
        HttpGlobalResponse<UserResponseDTO> response = userService.getUser(id);
        return response;
    }

    @DeleteMapping("/delete-user/{id}")
    public MessageResponseDTO deleteUser(@PathVariable Long id) {
        MessageResponseDTO response = userService.deleteUser(id);
        return response;
    }

    @PutMapping("/update-user/{id}")
    public HttpGlobalResponse<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody RegisterRequestDTO request) {
        HttpGlobalResponse<UserResponseDTO> response = userService.updateUser(id, request);
        return response;
    }
}
