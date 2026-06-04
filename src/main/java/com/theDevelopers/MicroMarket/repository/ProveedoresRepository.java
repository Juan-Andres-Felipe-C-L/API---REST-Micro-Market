package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.theDevelopers.MicroMarket.entity.Proveedores;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Long>{
    Optional<Proveedores> findByNit(String nit);
    
}
    

