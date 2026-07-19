package org.dereksilvestre.system;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dereksilvestre.view.CalculadoraView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {

        CalculadoraView calculadoraView =
                new CalculadoraView();

        Scene escena = new Scene(
                calculadoraView.getView(),
                300,
                450
        );

        escenarioPrincipal.setTitle(
                "Calculadora de Derek"
        );

        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.setResizable(false);
        escenarioPrincipal.show();
    }
}