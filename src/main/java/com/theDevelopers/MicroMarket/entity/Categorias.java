package com.theDevelopers.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Categorias")
@Data

public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    
}
