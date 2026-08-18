package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Material {
    private String tabla;
    private String material;
    private String elemento;
    private double valorMin;
    private double valorMax;
    private String unidad;
    private double anguloMin;
    private double anguloMax;
    
    public Material (String material, String elemento, double valorMin, double valorMax, String unidad) {
        this.material = material;
        this.elemento = elemento;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.unidad = unidad;
    }

    public void mostrarDatos () {
        System.out.println("-------------------------------------");
        System.out.println("| Tabla: " + this.tabla);
        System.out.println("| Material: " + this.material);
        System.out.println("| Elemento: " + this.elemento);
        System.out.println("| Valor Minimo: " + this.valorMin);
        System.out.println("| Valor Máximo: " + this.valorMax);
        System.out.println("| Unidad: " + this.unidad);
        System.out.println("| Angulo Mínimo: " + this.anguloMin);
        System.out.println("| Angulo Máximo: " + this.anguloMax);
        System.out.println("-------------------------------------");
    }
}
