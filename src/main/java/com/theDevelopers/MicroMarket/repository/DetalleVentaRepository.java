package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theDevelopers.MicroMarket.entity.DetalleVenta;
import java.util.Optional;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
        Optional<DetalleVenta> findById(Long id);
    
}
