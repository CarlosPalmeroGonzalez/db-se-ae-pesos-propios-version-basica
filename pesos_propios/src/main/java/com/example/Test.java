package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.example.data.MaterialDAOImp;
import com.example.model.Material;

public class Test {
    public static void main(String[] args) {
        
        /* Prueba 01 - Traer los datos de un CSV */
        /*
        File archivo = new File ("src\\main\\resources\\data\\materiales.csv");
        BufferedReader buffReader = null;
        ArrayList <Material> listaMateriales = new ArrayList<>();
        try {
            buffReader = new BufferedReader(new FileReader(archivo));
            String linea = null;
            while ((linea = buffReader.readLine()) != null) {
                String datos [] = linea.split(",");
                String tabla = datos [0];
                String material = datos [1];
                String elemento = datos [2];
                double valorMin = Double.parseDouble(datos[3]);
                double valorMax = Double.parseDouble(datos[4]);
                String unidad = datos [5];
                double anguloMin = Double.parseDouble(datos[6]);
                double anguloMax = Double.parseDouble(datos[7]);
                Material nuevoMaterial = new Material(tabla, material, elemento, valorMin, valorMax, unidad, anguloMin, anguloMax);
                listaMateriales.add(nuevoMaterial);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                buffReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Material m : listaMateriales) {
            m.mostrarDatos();
        }
        */

        /* Prueba 02 - Utilizando nuestra clase Material y MaterialDAOImp, crear el ArrayList con su méttodo */
        MaterialDAOImp materialDAO = new MaterialDAOImp();
        ArrayList <Material> listadoMateriales = materialDAO.informacionMateriales();

        for (Material m : listadoMateriales) {
            m.mostrarDatos();
        }
    }
}
