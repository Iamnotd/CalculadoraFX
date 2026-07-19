package org.dereksilvestre.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.dereksilvestre.controller.CalculadoraController;

public class CalculadoraView {

    private VBox view;
    private Label pantalla;
    private GridPane cuadroBotones;
    private CalculadoraController controlador;

    private static final String ESTILO_NUMERO =
            "-fx-background-color: #C60C62;"
            + "-fx-text-fill: white;"
            + "-fx-background-radius: 12;"
            + "-fx-cursor: hand;";

    private static final String ESTILO_OPERADOR =
            "-fx-background-color: #8E0B49;"
            + "-fx-text-fill: white;"
            + "-fx-background-radius: 12;"
            + "-fx-cursor: hand;";

    private static final String ESTILO_ESPECIAL =
            "-fx-background-color: #F7B2D1;"
            + "-fx-text-fill: #650733;"
            + "-fx-background-radius: 12;"
            + "-fx-cursor: hand;";

    private static final String ESTILO_PRESIONADO =
            "-fx-background-color: #650733;"
            + "-fx-text-fill: white;"
            + "-fx-background-radius: 12;"
            + "-fx-cursor: hand;";

    public CalculadoraView() {

        controlador = new CalculadoraController();

        configurarContenedor();
        configurarPantalla();
        configurarBotones();

        view.getChildren().addAll(
                pantalla,
                cuadroBotones
        );
    }

    private void configurarContenedor() {

        view = new VBox(15);

        view.setPadding(new Insets(20));
        view.setAlignment(Pos.CENTER);

        view.setStyle(
                "-fx-background-color: "
                + "linear-gradient(to bottom, #F990C1, #F7B2D1);"
                + "-fx-background-radius: 20;"
        );
    }

    private void configurarPantalla() {

        pantalla = new Label("0");

        pantalla.setAlignment(Pos.CENTER_RIGHT);
        pantalla.setPrefSize(250, 65);
        pantalla.setMaxWidth(Double.MAX_VALUE);
        pantalla.setPadding(
                new Insets(5, 15, 5, 15)
        );

        pantalla.setFont(
                Font.font(
                        "Consolas",
                        FontWeight.BOLD,
                        35
                )
        );

        pantalla.setStyle(
                "-fx-background-color: #FFF4F9;"
                + "-fx-text-fill: #650733;"
                + "-fx-background-radius: 12;"
                + "-fx-border-color: #C60C62;"
                + "-fx-border-radius: 12;"
                + "-fx-border-width: 2;"
        );
    }

    private void configurarBotones() {

        cuadroBotones = new GridPane();

        cuadroBotones.setHgap(10);
        cuadroBotones.setVgap(10);
        cuadroBotones.setAlignment(Pos.CENTER);

        // Primera fila
        Button btnBorrar = crearBoton(
                "C",
                TipoBoton.ESPECIAL
        );

        Button btnRaiz = crearBoton(
                "√",
                TipoBoton.ESPECIAL
        );

        Button btnPorcentaje = crearBoton(
                "%",
                TipoBoton.OPERADOR
        );

        Button btnDividir = crearBoton(
                "÷",
                TipoBoton.OPERADOR
        );

        // Segunda fila
        Button btnSiete = crearBoton(
                "7",
                TipoBoton.NUMERO
        );

        Button btnOcho = crearBoton(
                "8",
                TipoBoton.NUMERO
        );

        Button btnNueve = crearBoton(
                "9",
                TipoBoton.NUMERO
        );

        Button btnMultiplicar = crearBoton(
                "×",
                TipoBoton.OPERADOR
        );

        // Tercera fila
        Button btnCuatro = crearBoton(
                "4",
                TipoBoton.NUMERO
        );

        Button btnCinco = crearBoton(
                "5",
                TipoBoton.NUMERO
        );

        Button btnSeis = crearBoton(
                "6",
                TipoBoton.NUMERO
        );

        Button btnMenos = crearBoton(
                "-",
                TipoBoton.OPERADOR
        );

        // Cuarta fila
        Button btnUno = crearBoton(
                "1",
                TipoBoton.NUMERO
        );

        Button btnDos = crearBoton(
                "2",
                TipoBoton.NUMERO
        );

        Button btnTres = crearBoton(
                "3",
                TipoBoton.NUMERO
        );

        Button btnMas = crearBoton(
                "+",
                TipoBoton.OPERADOR
        );

        // Quinta fila
        Button btnCero = crearBoton(
                "0",
                TipoBoton.NUMERO
        );

        Button btnPunto = crearBoton(
                ".",
                TipoBoton.NUMERO
        );

        Button btnIgual = crearBoton(
                "=",
                TipoBoton.OPERADOR
        );

        // Agregar primera fila
        cuadroBotones.add(btnBorrar, 0, 0);
        cuadroBotones.add(btnRaiz, 1, 0);
        cuadroBotones.add(btnPorcentaje, 2, 0);
        cuadroBotones.add(btnDividir, 3, 0);

        // Agregar segunda fila
        cuadroBotones.add(btnSiete, 0, 1);
        cuadroBotones.add(btnOcho, 1, 1);
        cuadroBotones.add(btnNueve, 2, 1);
        cuadroBotones.add(btnMultiplicar, 3, 1);

        // Agregar tercera fila
        cuadroBotones.add(btnCuatro, 0, 2);
        cuadroBotones.add(btnCinco, 1, 2);
        cuadroBotones.add(btnSeis, 2, 2);
        cuadroBotones.add(btnMenos, 3, 2);

        // Agregar cuarta fila
        cuadroBotones.add(btnUno, 0, 3);
        cuadroBotones.add(btnDos, 1, 3);
        cuadroBotones.add(btnTres, 2, 3);
        cuadroBotones.add(btnMas, 3, 3);

        // Agregar quinta fila
        cuadroBotones.add(
                btnCero,
                0,
                4,
                2,
                1
        );

        cuadroBotones.add(btnPunto, 2, 4);
        cuadroBotones.add(btnIgual, 3, 4);

        btnCero.setPrefWidth(120);
    }

    private Button crearBoton(
            String texto,
            TipoBoton tipo
    ) {

        Button boton = new Button(texto);

        boton.setPrefSize(55, 55);

        boton.setFont(
                Font.font(
                        "Consolas",
                        FontWeight.BOLD,
                        20
                )
        );

        String estiloNormal = obtenerEstilo(tipo);

        boton.setStyle(estiloNormal);

        boton.setOnMousePressed(evento -> {
            boton.setStyle(ESTILO_PRESIONADO);
            boton.setTranslateY(2);
        });

        boton.setOnMouseReleased(evento -> {
            boton.setStyle(estiloNormal);
            boton.setTranslateY(0);
        });

        boton.setOnAction(evento ->
                controlador.procesoDeEntrada(
                        boton.getText(),
                        pantalla
                )
        );

        return boton;
    }

    private String obtenerEstilo(TipoBoton tipo) {

        switch (tipo) {

            case NUMERO:
                return ESTILO_NUMERO;

            case OPERADOR:
                return ESTILO_OPERADOR;

            case ESPECIAL:
                return ESTILO_ESPECIAL;

            default:
                return ESTILO_NUMERO;
        }
    }

    private enum TipoBoton {
        NUMERO,
        OPERADOR,
        ESPECIAL
    }

    public VBox getView() {
        return view;
    }

    public Label getPantalla() {
        return pantalla;
    }
}