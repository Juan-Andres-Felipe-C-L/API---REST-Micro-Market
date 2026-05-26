package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.theDevelopers.MicroMarket.entity.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    
}
