package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theDevelopers.MicroMarket.entity.Proveedores;

@Repository
public interface ProveedoresRepository extends JpaRepository<Proveedores, Long>{
    
}