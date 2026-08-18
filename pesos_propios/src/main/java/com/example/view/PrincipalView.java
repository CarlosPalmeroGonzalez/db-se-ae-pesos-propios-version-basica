package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PrincipalView extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(PrincipalView.class.getResource("/fxml/principal.fxml"));
        Scene scene = new Scene (fxml.load());

        primaryStage.setTitle("Calculadora de Pesos Específicos - DB-SE-AE (Anejo C)");
        primaryStage.setScene (scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(
            new Image(getClass().getResourceAsStream("/image/icon_principal.png"))
        );
        primaryStage.show();
    }
}
