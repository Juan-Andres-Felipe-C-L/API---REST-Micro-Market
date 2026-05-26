package com.theDevelopers.MicroMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theDevelopers.MicroMarket.entity.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    
}
