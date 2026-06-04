package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


import com.theDevelopers.MicroMarket.entity.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
    Optional<Empleados> findByCedula(String cedula);
    List<Empleados> findByCargo(String cargo);
    
}
    
}
