package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CalculoPesoPropio {
    private String elemento;
    private String descripcion;
    private String dimensiones;
    private Double total;
    private Double pesoEspecifico;
    private String unidad;
    private Double pesoPropio;

    
}
