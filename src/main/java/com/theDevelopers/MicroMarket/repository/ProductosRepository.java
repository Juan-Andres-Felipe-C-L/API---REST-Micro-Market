package com.theDevelopers.MicroMarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theDevelopers.MicroMarket.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
    Optional<Productos> findByCodigoBarras(String codigoBarras);  
}