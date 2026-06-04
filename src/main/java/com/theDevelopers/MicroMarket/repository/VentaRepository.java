package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theDevelopers.MicroMarket.entity.Venta;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    Optional<Venta> findById(Long id);
    
}

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
}
