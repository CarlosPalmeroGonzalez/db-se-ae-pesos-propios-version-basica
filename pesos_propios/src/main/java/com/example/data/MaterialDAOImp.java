package com.example.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.example.model.Material;

public class MaterialDAOImp implements MaterialDAO {

    @Override
    public ArrayList<Material> informacionMateriales() {
        /* Código antiguo:
        File archivo = new File ("pesos_propios\\src\\main\\resources\\data\\materiales.csv");
        */
        BufferedReader buffReader = null;
        ArrayList <Material> listaMateriales = new ArrayList <> ();
        InputStream inputStream = MaterialDAOImp.class.getResourceAsStream("/data/materiales.csv");
        if (inputStream == null) {
            System.out.println("ERROR: No se ha encontrado /data/materiales.csv");
            return listaMateriales;
        }

        try {
            /* Código antiguo:
            buffReader = new BufferedReader (new FileReader (archivo));
            */
            buffReader = new BufferedReader (new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String linea = null;
            while ( (linea = buffReader.readLine()) != null ) {
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
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                buffReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listaMateriales;
    }
}
