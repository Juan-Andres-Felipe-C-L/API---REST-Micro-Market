package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Categoria")
@Data

public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    @Column(name = "nombre_categoria")
    private String nombre_categoria;
    
}