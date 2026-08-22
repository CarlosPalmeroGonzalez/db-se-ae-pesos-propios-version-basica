package com.example.controller;

import java.util.ArrayList;

import com.example.data.MaterialDAOImp;
import com.example.model.CalculoPesoPropio;
import com.example.model.Material;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PrincipalController {
    private ArrayList <Material> listadoMateriales;
    private MaterialDAOImp materialDAO;
    private ObservableList <CalculoPesoPropio> listadoCalculos = FXCollections.observableArrayList();
    private static Double totalMetros = 0.0;
    private static Double totalMetros2 = 0.0;
    private static Double totalMetros3 = 0.0;
    private static Double totalPeso = 0.0;

    @FXML private Label sumaPesos;
    @FXML private Label sumaVolumen;
    @FXML private Label sumaSuperficie;
    @FXML private Label sumaMetro;

    @FXML private Label lbTotal;
    @FXML private Label lbPesoPropio;
    @FXML private Label lbPesoEspecifico;

    @FXML private Label lbValorMinimo;
    @FXML private Label lbValorMaximo;

    @FXML private Label lbUnidad1;
    @FXML private Label lbUnidad2;
    @FXML private Label lbUnidad3;
    @FXML private Label lbUnidad4;
    @FXML private Label lbUnidad5;

    @FXML private TextField tfDescripcion;
    @FXML private TextField tfLargo;
    @FXML private TextField tfAncho;
    @FXML private TextField tfEspesor;
    @FXML private TextField tfValor;

    @FXML private ComboBox<String> cbMaterial;
    @FXML private ComboBox<String> cbElemento;

    // Explicación => Esta es una tabla de vista que recoge el contenido de objectos "CalculoPesoPropio"
    @FXML private TableView <CalculoPesoPropio> tablaCalculo;
    //Explicación => Esta columna pertenece a una tabla de objectos "CaluloPesoPropio" y el valor que va a recoger es un String
    @FXML private TableColumn <CalculoPesoPropio, String> colElemento;
    @FXML private TableColumn <CalculoPesoPropio, String> colDescripcion;
    @FXML private TableColumn <CalculoPesoPropio, String> colDimensiones;
    @FXML private TableColumn <CalculoPesoPropio, Double> colTotal;
    @FXML private TableColumn <CalculoPesoPropio, Double> colPesoEspecifico;
    @FXML private TableColumn <CalculoPesoPropio, String> colUnidad;
    @FXML private TableColumn <CalculoPesoPropio, Double> colPesoPropio;

    @FXML private void initialize() {
        // Mediante nuestra clase MaterialDAOImp, obtenemos todos los datos de nuestro CSV
        materialDAO = new MaterialDAOImp();
        listadoMateriales = materialDAO.informacionMateriales();

        // Cargamos los datos de nuestro ArrayList en el ComboBox de Materiales
        cargarMateriales();
        cbMaterial.getSelectionModel().select(0); // Primera opción seleccionada al arrancar el programa

        // Cuando se realiza una acción en el ComboBox de Materiales, se cargan los datos en el ComboBox de Elementos
        cbMaterial.setOnAction(event -> {
            cargarElementos();
            cbElemento.getSelectionModel().select(0);// Primera opción seleccionada al arrancar el programa
        });
        
        // Cuando se realiza una acción en el ComboBox de Elementos, cargamos los valores y unidades.
        cbElemento.setOnAction(event -> cargarValores());
        
        // Establecemos los valores que se van a introducir en cada columna a través de una función lambda, donde obtenemos 
        // el valor del elemento "dato" y lo plasmamos en la columna esecífica
        colElemento.setCellValueFactory (dato -> 
            new SimpleStringProperty(dato.getValue().getElemento())
        );
        colDescripcion.setCellValueFactory(dato ->
            new SimpleStringProperty(dato.getValue().getDescripcion())
        );
        colDimensiones.setCellValueFactory(dato ->
            new SimpleStringProperty(dato.getValue().getDimensiones())
        );
        colTotal.setCellValueFactory(dato ->
            new SimpleObjectProperty<>(dato.getValue().getTotal())
        );
        colPesoEspecifico.setCellValueFactory(dato ->
            new SimpleObjectProperty<>(dato.getValue().getPesoEspecifico())
        );
        colUnidad.setCellValueFactory(dato ->
            new SimpleStringProperty(dato.getValue().getUnidad())
        );
        colPesoPropio.setCellValueFactory(dato ->
            new SimpleObjectProperty<>(dato.getValue().getPesoPropio())
        );

        // Introducimos los datos de nuestra tabla dentro de nuestra ObservableList
        tablaCalculo.setItems(listadoCalculos);

    }

    @FXML public void calculoPrevio() {
        if (comprobacionesBasicas() == true) {
            calculoPesoPropio();
        }
    }

    private void calculoPesoPropio () {
        int opcion;
        Double totalDimensiones;
        Double totalPesoPropio;

        // Recogemos en String los datos introducidos por el usuario
        String largoText = tfLargo.getText();
        String anchoText = tfAncho.getText();
        String espesorText = tfEspesor.getText();
        String valorText = tfValor.getText();   
        
        // Tranformamos nuestro String es Double para poder realizar los calculos. 
        Double largo = Double.parseDouble(largoText);
        Double ancho = Double.parseDouble(anchoText);
        Double espesor = Double.parseDouble(espesorText);
        Double valor = Double.parseDouble(valorText);

        // Según el tipo de unidad, se realizará un calculo específico
        if (lbUnidad1.getText().equals("kN/m3")) opcion = 1;
        else if (lbUnidad1.getText().equals("kN/m2")) opcion = 2;
        else opcion = 3;
        
        switch (opcion) {
            case 1:
                // Realizamos el calculo de las dimensiones. En este caso, volumen total (m3).
                totalDimensiones = largo * ancho * espesor;
                // Realizamos el cálculo del peso propio del elemento. La unidad de este apartado es en kN.
                totalPesoPropio = totalDimensiones * valor;
                // Sobreescribimos los labels para mostrar al usuario el cálculo previo.
                lbTotal.setText(totalDimensiones.toString());
                lbPesoEspecifico.setText(valor.toString());
                lbPesoPropio.setText(totalPesoPropio.toString());
                // Sobreescribimso los labels para mostrar las unidades pertinentes 
                lbUnidad4.setText("m3");
                lbUnidad5.setText("kN/m3");
                break;
            case 2:
                // Realizamos el calculo de las dimensiones. En este caso, volumen total (m3).
                totalDimensiones = largo * ancho;
                // Realizamos el cálculo del peso propio del elemento. La unidad de este apartado es en kN.
                totalPesoPropio = totalDimensiones * valor;
                // Sobreescribimos los labels para mostrar al usuario el cálculo previo.
                lbTotal.setText(totalDimensiones.toString());
                lbPesoEspecifico.setText(valor.toString());
                lbPesoPropio.setText(totalPesoPropio.toString());
                // Sobreescribimso los labels para mostrar las unidades pertinentes 
                lbUnidad4.setText("m2");
                lbUnidad5.setText("kN/m2");
                break;
            case 3:
                // Realizamos el calculo de las dimensiones. En este caso, volumen total (m3).
                totalDimensiones = largo;
                // Realizamos el cálculo del peso propio del elemento. La unidad de este apartado es en kN.
                totalPesoPropio = totalDimensiones * valor;
                // Sobreescribimos los labels para mostrar al usuario el cálculo previo.
                lbTotal.setText(totalDimensiones.toString());
                lbPesoEspecifico.setText(valor.toString());
                lbPesoPropio.setText(totalPesoPropio.toString());
                // Sobreescribimso los labels para mostrar las unidades pertinentes 
                lbUnidad4.setText("m");
                lbUnidad5.setText("kN/m");
                break;
            default:
                mensajeAlerta("ERROR EN EL APARTADO: UNIDADES", "La unidad del elemento constructivo está mal introducida");
        } 
    }

    @FXML public void addTabla () {
        if (comprobacionesBasicas()) {
            String elemento = cbElemento.getValue();
            String descripcion = tfDescripcion.getText();
            Double largo = Double.parseDouble(tfLargo.getText());
            Double ancho = Double.parseDouble(tfAncho.getText()); 
            Double espesor = Double.parseDouble(tfEspesor.getText());
            String unidad = lbUnidad1.getText();
            Double pesoEspecifico = Double.parseDouble(tfValor.getText());
            String dimensiones;
            Double total;
            if (unidad.equals("kN/m3")) {
                dimensiones = largo + " m x " + ancho + " m x " + espesor + "m";
                total = largo * ancho * espesor;
                totalMetros3 += total;
            }
            else if (unidad.equals("kN/m2")) {
                dimensiones = largo + " m x " + ancho + " m";
                total = largo * ancho;
                totalMetros2 += total;
            }
            else {
                dimensiones = largo + " m";
                total = largo;
                totalMetros += total;
            }
            Double pesoPropio = total * pesoEspecifico;
            totalPeso += pesoPropio;
            CalculoPesoPropio nuevoCalculo = new CalculoPesoPropio(elemento,descripcion,dimensiones,total,pesoEspecifico,unidad,pesoPropio);
            listadoCalculos.add(nuevoCalculo);

            sumaMetro.setText(totalMetros.toString());
            sumaSuperficie.setText(totalMetros2.toString());
            sumaVolumen.setText(totalMetros3.toString());
            sumaPesos.setText(totalPeso.toString());
        }
    }

    @FXML public void limpiar () {
        // Seleccionamos la primera opción en cada uno de los ComboBox para limpiarlos
        cbMaterial.getSelectionModel().select(0);
        cbElemento.getSelectionModel().select(0);
        // Limpiamos los TextField en los cuales pueda haber información
        tfDescripcion.clear();
        tfLargo.clear();
        tfAncho.clear();
        tfEspesor.clear();
        tfValor.clear();
        // Limpiamos y ponemos valores iniciales en los Labels
        lbValorMinimo.setText("0.00");
        lbValorMaximo.setText("0.00");
        lbUnidad1.setText("unidad");
        lbUnidad2.setText("unidad");
        lbUnidad3.setText("unidad");
        lbTotal.setText("0.00");
        lbPesoEspecifico.setText("0.00");
        lbPesoPropio.setText("0.00");
    }

    @FXML public void eliminarFila() {
        CalculoPesoPropio filaSeleccionada = tablaCalculo.getSelectionModel().getSelectedItem();
        if (filaSeleccionada != null) {
            Double totalResta = filaSeleccionada.getTotal();
            Double pesoResta = filaSeleccionada.getPesoPropio();
            if (filaSeleccionada.getUnidad().equals("kN/m3")) {
                totalMetros3 -= totalResta;
                sumaVolumen.setText(totalMetros3.toString());
            }
            else if (filaSeleccionada.getUnidad().equals("kN/m2")) {
                totalMetros2 -= totalResta;
                sumaSuperficie.setText(totalMetros2.toString());
            }
            else {
                totalMetros -= totalResta;
                sumaMetro.setText(totalMetros.toString());
            }
            totalPeso -= pesoResta;
            sumaPesos.setText(totalPeso.toString());
            
            listadoCalculos.remove(filaSeleccionada);
        }
        else {
            mensajeAlerta(
                "ERROR EN LA TABLA DE CALCULOS", 
                "Estas intentando eliminar una fila que inexistente");
        }
    }

    private void cargarMateriales() {
        cbMaterial.getItems().add("Seleccione un material");
        for (Material material : listadoMateriales) {
            if ( !(cbMaterial.getItems().contains(material.getMaterial())) ) {
                cbMaterial.getItems().add(material.getMaterial());
            }
        }
        
    }

    private void cargarElementos() {
        cbElemento.getItems().clear();
        String materialSeleccionado = cbMaterial.getValue();
        if (materialSeleccionado == null) {
            return;
        }
        cbElemento.getItems().add("Seleccione un elemento");
        for (Material elemento : listadoMateriales ) {
            if (elemento.getMaterial().equals(materialSeleccionado)) {
                cbElemento.getItems().add(elemento.getElemento());
            }
        }
        
    }

    private void cargarValores () {
        lbValorMinimo.setText("0.00");
        lbValorMaximo.setText("0.00");
        lbUnidad1.setText("unidad");
        lbUnidad2.setText("unidad");
        lbUnidad3.setText("unidad");

        String materialSeleccionado = cbMaterial.getValue();
        String elementoSeleccionado = cbElemento.getValue();

        for (Material m : listadoMateriales) {
            if (m.getMaterial().equals(materialSeleccionado) && m.getElemento().equals(elementoSeleccionado)) {
                Double valorMin = m.getValorMin();
                Double valorMax = m.getValorMax();
                String unidad = m.getUnidad();
                lbValorMinimo.setText(valorMin.toString());
                lbValorMaximo.setText(valorMax.toString());
                lbUnidad1.setText(unidad);
                lbUnidad2.setText(unidad);
                lbUnidad3.setText(unidad);
            }
        }
    }

    private void mensajeAlerta (String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText("Compruebe los datos introducidos");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private boolean comprobacionesBasicas () {
        String mensaje, titulo;
        if (cbMaterial.getSelectionModel().getSelectedIndex() == 0) {
            titulo = "ERROR EN EL APARTADO: MATERIALES";
            mensaje = "No se ha seleccionado ningún material constructivo";
            mensajeAlerta(titulo, mensaje);
            return false;
        }
        if (cbElemento.getSelectionModel().getSelectedIndex() == 0) {
            titulo = "ERROR EN EL APARTADO: MATERIALES";
            mensaje = "No se ha seleccionado ningún elemento constructivo";
            mensajeAlerta(titulo, mensaje);
            return false;
        }
        if (lbUnidad1.getText().equals("kN/m3")) {
            if (tfLargo.getText().isBlank() || tfAncho.getText().isBlank()|| tfEspesor.getText().isBlank()) {
            titulo = "ERROR EN EL APARTADO: DIMENSIONES";
            mensaje = "Todos los datos de dimensiones deben estar rellenos (Largo, Ancho y Espesor)";
            mensajeAlerta(titulo, mensaje);
            return false;
            }
        }
        if (lbUnidad1.getText().equals("kN/m2")) {
            if (tfLargo.getText().isBlank() || tfAncho.getText().isBlank()) {
            titulo = "ERROR EN EL APARTADO: DIMENSIONES";
            mensaje = "Los datos de las dimensiones para el cáculo de peso por superficie deben estar rellenos (Largo y Ancho)";
            mensajeAlerta(titulo, mensaje);
            return false;
            }
        }
        if (lbUnidad1.getText().equals("kN/m")) {
            if (tfLargo.getText().isBlank()) {
            titulo = "ERROR EN EL APARTADO: DIMENSIONES";
            mensaje = "El dato del largo para el cálculo de peso por metro lineal debe estar relleno (Largo)";
            mensajeAlerta(titulo, mensaje);
            return false;
            }
        }
        if (tfValor.getText().isBlank()) {
            titulo = "ERROR EN EL APARTADO: PESO ESPECÍFICO APARENTE";
            mensaje = "El valor del peso específico debe estar relleno (Valor Final)";
            mensajeAlerta(titulo, mensaje);
            return false;
        }
        if (!tfValor.getText().isBlank()) {
            Double valorMin = Double.parseDouble(lbValorMinimo.getText());
            Double valorMax = Double.parseDouble(lbValorMaximo.getText());
            Double valor = Double.parseDouble(tfValor.getText());

            if (valor < valorMin) {
                titulo = "ERROR EN EL APARTADO: PESO ESPECÍFICO APARENTE";
                mensaje = "El valor del peso espcífico no puede ser inferior al valor mínimo";
                mensajeAlerta(titulo, mensaje);
                return false;
            }
            if (valor > valorMax) {
                titulo = "ERROR EN EL APARTADO: PESO ESPECÍFICO APARENTE";
                mensaje = "El valor del peso específico no puede ser superior al valor máximo";
                mensajeAlerta(titulo, mensaje);
                return false;
            }
        }
        return true;
    }
}
