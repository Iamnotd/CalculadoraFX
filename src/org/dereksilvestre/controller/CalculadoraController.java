package org.dereksilvestre.controller;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class CalculadoraController {

    private String opcion1 = "";
    private String operador = "";
    private String opcion2 = "";
    private boolean calculoTerminado = false;

    public CalculadoraController() {

    }

    public void procesoDeEntrada(String entrada, Label pantalla) {

        // Botón C: limpiar toda la calculadora
        if (entrada.equals("C")) {
            limpiarCalculadora();
            pantalla.setText("0");
            return;
        }

        // Botón para eliminar el último carácter
        if (entrada.equals("⌫")) {

            if (calculoTerminado) {
                limpiarCalculadora();
                actualizarPantalla(pantalla);
                return;
            }

            if (!opcion2.isEmpty()) {
                opcion2 = opcion2.substring(
                        0,
                        opcion2.length() - 1
                );

            } else if (!operador.isEmpty()) {
                operador = "";

            } else if (!opcion1.isEmpty()) {
                opcion1 = opcion1.substring(
                        0,
                        opcion1.length() - 1
                );
            }

            actualizarPantalla(pantalla);
            return;
        }

        // Entrada de números y punto decimal
        if (entrada.matches("[0-9]") || entrada.equals(".")) {

            // Si ya había un resultado, comienza un cálculo nuevo
            if (calculoTerminado) {
                limpiarCalculadora();
            }

            if (operador.isEmpty()) {
                opcion1 = agregarNumero(opcion1, entrada);
            } else {
                opcion2 = agregarNumero(opcion2, entrada);
            }

            actualizarPantalla(pantalla);
        }

        // Operadores
        else if (esOperador(entrada)) {

            if (opcion1.isEmpty() || opcion1.equals("Error")) {
                return;
            }

            // Permite cambiar el operador antes de escribir opcion2
            operador = normalizarOperador(entrada);
            calculoTerminado = false;

            actualizarPantalla(pantalla);
        }

        // Botón igual
        else if (entrada.equals("=")) {

            if (!opcion1.isEmpty()
                    && !opcion2.isEmpty()
                    && !operador.isEmpty()) {

                opcion1 = ejecutarOperacion(
                        opcion1,
                        opcion2,
                        operador
                );

                opcion2 = "";
                operador = "";
                calculoTerminado = true;
            }

            actualizarPantalla(pantalla);
        }
    }

    private String agregarNumero(
            String numeroActual,
            String entrada
    ) {

        // No permite colocar dos puntos
        if (entrada.equals(".")
                && numeroActual.contains(".")) {
            return numeroActual;
        }

        // Convierte "." en "0."
        if (entrada.equals(".")
                && numeroActual.isEmpty()) {
            return "0.";
        }

        // Evita números como 00005
        if (numeroActual.equals("0")
                && !entrada.equals(".")) {
            return entrada;
        }

        return numeroActual + entrada;
    }

    private boolean esOperador(String entrada) {

        return entrada.equals("+")
                || entrada.equals("-")
                || entrada.equals("X")
                || entrada.equals("x")
                || entrada.equals("×")
                || entrada.equals("*")
                || entrada.equals("/")
                || entrada.equals("÷");
    }

    private String normalizarOperador(String entrada) {

        if (entrada.equals("X")
                || entrada.equals("x")
                || entrada.equals("×")
                || entrada.equals("*")) {

            return "×";
        }

        if (entrada.equals("/")
                || entrada.equals("÷")) {

            return "÷";
        }

        return entrada;
    }

    private String calcularPorcentaje(String numero) {

        double valor = Double.parseDouble(numero);
        double resultado = valor / 100;

        return formatearResultado(resultado);
    }

    private String ejecutarOperacion(
            String numeroUno,
            String numeroDos,
            String op
    ) {

        double d1 = Double.parseDouble(numeroUno);
        double d2 = Double.parseDouble(numeroDos);
        double resultado;

        switch (op) {

            case "+":
                resultado = d1 + d2;
                break;

            case "-":
                resultado = d1 - d2;
                break;

            case "×":
                resultado = d1 * d2;
                break;

            case "÷":

                if (d2 == 0) {
                    return "Error";
                }

                resultado = d1 / d2;
                break;

            default:
                return "Error";
        }

        return formatearResultado(resultado);
    }

    private String formatearResultado(double resultado) {

        // Evita mostrar valores como 5.0
        if (resultado == Math.rint(resultado)
                && resultado <= Integer.MAX_VALUE
                && resultado >= Integer.MIN_VALUE) {

            return String.valueOf((int) resultado);
        }

        return String.valueOf(resultado);
    }

    private void actualizarPantalla(Label pantalla) {

        pantalla.setMaxWidth(Double.MAX_VALUE);
        pantalla.setAlignment(Pos.CENTER_RIGHT);

        if (opcion1.isEmpty()) {
            pantalla.setText("0");

        } else if (operador.isEmpty()) {
            pantalla.setText(opcion1);

        } else if (opcion2.isEmpty()) {
            pantalla.setText(
                    opcion1 + " " + operador
            );

        } else {
            pantalla.setText(
                    opcion1
                            + " "
                            + operador
                            + " "
                            + opcion2
            );
        }
    }

    private void limpiarCalculadora() {

        opcion1 = "";
        operador = "";
        opcion2 = "";
        calculoTerminado = false;
    }
}