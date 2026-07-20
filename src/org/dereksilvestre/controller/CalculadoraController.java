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

        // Limpiar toda la calculadora
        if (entrada.equals("C")) {
            limpiarCalculadora();
            actualizarPantalla(pantalla);
            return;
        }

        // Números y punto decimal
        if (entrada.matches("[0-9]") || entrada.equals(".")) {

            if (calculoTerminado) {
                limpiarCalculadora();
            }

            if (operador.isEmpty()) {
                opcion1 = agregarNumero(opcion1, entrada);
            } else {
                opcion2 = agregarNumero(opcion2, entrada);
            }

            actualizarPantalla(pantalla);
            return;
        }

        // Operadores básicos
        if (esOperador(entrada)) {

            if (opcion1.isEmpty() || opcion1.equals("Error")) {
                return;
            }

            operador = normalizarOperador(entrada);
            calculoTerminado = false;

            actualizarPantalla(pantalla);
            return;
        }

        // Raíz cuadrada
        if (entrada.equals("√")) {

            if (!opcion2.isEmpty()) {
                opcion2 = calcularRaizCuadrada(opcion2);

            } else if (!opcion1.isEmpty()
                    && !opcion1.equals("Error")) {

                opcion1 = calcularRaizCuadrada(opcion1);
            }

            calculoTerminado = operador.isEmpty();
            actualizarPantalla(pantalla);
            return;
        }

        // Porcentaje
        if (entrada.equals("%")) {

            if (!opcion2.isEmpty()) {
                opcion2 = calcularPorcentaje(opcion2);

            } else if (!opcion1.isEmpty()
                    && !opcion1.equals("Error")) {

                opcion1 = calcularPorcentaje(opcion1);
            }

            calculoTerminado = operador.isEmpty();
            actualizarPantalla(pantalla);
            return;
        }

        // Resultado
        if (entrada.equals("=")) {

            if (!opcion1.isEmpty()
                    && !opcion2.isEmpty()
                    && !operador.isEmpty()) {

                opcion1 = ejecutarOperacion(
                        opcion1,
                        opcion2,
                        operador
                );

                operador = "";
                opcion2 = "";
                calculoTerminado = true;
            }

            actualizarPantalla(pantalla);
        }
    }

    private String agregarNumero(
            String numeroActual,
            String entrada
    ) {

        // Evita más de un punto decimal
        if (entrada.equals(".")
                && numeroActual.contains(".")) {

            return numeroActual;
        }

        // Si el usuario comienza con punto, se convierte en 0.
        if (entrada.equals(".")
                && numeroActual.isEmpty()) {

            return "0.";
        }

        // Evita números como 0005
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

    private String calcularRaizCuadrada(String numero) {

        double valor = Double.parseDouble(numero);

        if (valor < 0) {
            return "Error";
        }

        double resultado = Math.sqrt(valor);

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