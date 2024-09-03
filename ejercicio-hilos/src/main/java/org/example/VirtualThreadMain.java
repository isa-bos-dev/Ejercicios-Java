package org.example;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * La clase VirtualThreadMain es el punto de entrada del programa que utiliza hilos virtuales para realizar dos tareas simultáneamente.
 * Un hilo cuenta hacia atrás desde un número ingresado por el usuario, y el otro muestra las letras del alfabeto desde 'A' hasta una letra específica.
 * Ambos hilos se detendrán cuando el primer hilo encuentre la letra ingresada por el usuario.
 */
public class VirtualThreadMain {
    /**
     * Inicia el programa principal y coordina la ejecución de los hilos.
     *
     */
    public static void main(String[] args) {
        // Ejecutores son objetos que administran los hilos
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // Variable atómica para indicar si la letra ha sido encontrada
            AtomicBoolean found = new AtomicBoolean(false);

            // Solicitamos la letra al usuario y y validamos la entrada
            char character = ' ';
            do {
                String input = JOptionPane.showInputDialog("Introduce una letra").toUpperCase();
                if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                    character = input.charAt(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, introduce una letra válida.");
                }
            } while (character == ' ');


            // Solicitamos un número positivo al usuario y validamos la entrada
            int number;
            do {
                try {
                    number = Integer.parseInt(JOptionPane.showInputDialog("Introduce un número positivo"));
                }catch (NumberFormatException e) {
                    number = -1;
                    JOptionPane.showMessageDialog(null, "Por favor, introduce un número válido.");
                }
            } while (number <=0);


            // Iniciamos los hilos para buscar la letra y realizar el conteo regresivo
            AtomicInteger atomicInteger = new AtomicInteger(number);


            // Levantamos los hilos
            executor.submit(thread1 (character, found));
            executor.submit(thread2 (atomicInteger, found));

            // Esperamos a que ambos hilos terminen o hasta 30 segundos como máximo
            boolean terminated = executor.awaitTermination(30, TimeUnit.SECONDS);
            if (!terminated) {
                System.out.println("Los hilos no terminaron en el tiempo esperado.");
            }

            // Finalizamos el ejecutor
            executor.shutdownNow();
        } catch (InterruptedException e) {
            // Restablecemos el estado de interrupción del hilo actual
            Thread.currentThread().interrupt();
        }
    }

    // Los hilos pueden ser de tipo runnable (no devuelve nada) y callable (devuelve algo)

    /**
     * Crea un hilo que busca una letra específica en el alfabeto, comenzando desde 'A'.
     * El hilo se detiene cuando encuentra la letra indicada por el usuario.
     *
     * @param character La letra que se va a buscar en el alfabeto.
     * @param found Un indicador atómico que marca si la letra ha sido encontrada, compartido entre hilos.
     * @return Un objeto Runnable que representa la tarea del hilo.
     */
    private static Runnable thread1(char character, AtomicBoolean found) {
        return () -> {
            try {
                //Asignamos nombre al hilo
                Thread.currentThread().setName("Thread-character");

                //Iteramos para buscar la letra
                for (char i = 'A'; i <= character; i++) {
                    // Introducimos el delay de 500 mls
                    Thread.sleep(500);

                    //Imprimimos las letras consultadas
                    System.out.print(i);

                    if (i == character) { // Si la encontramos
                        found.set(true);
                        System.out.println(" - Letra " + i + " encontrada");
                        System.out.println(" - Trabajo del hilo  " + Thread.currentThread().getName() + " terminado");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        };
    }

    /**
     * Crea un hilo que realiza un conteo regresivo desde un número dado hasta que otro hilo encuentre una letra.
     *
     * @param atomicInteger Un entero atómico que representa el número desde el cual se comienza a contar hacia atrás.
     * @param found Un indicador atómico que marca si la letra ha sido encontrada, compartido entre hilos.
     * @return Un objeto Runnable que representa la tarea del hilo.
     */
    private static Runnable thread2(AtomicInteger atomicInteger, AtomicBoolean found) {
        return () -> {
            try {
                //Asignamos nombre al hilo
                Thread.currentThread().setName("Thread-number");

                // Mientras el hilo 1 este funcionando se ejecutara este hilo
                while (!found.get()) {
                    // Introducimos el delay de 600 mls
                    Thread.sleep(600);
                    //Imprimimos los numeros decreciendo
                    System.out.println(atomicInteger.getAndDecrement());
                }

                // Este hilo acaba su trabajo
                System.out.println(" - Trabajo del hilo  " + Thread.currentThread().getName() + " terminado");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("El hilo fue interrumpido.");
            }
        };
    }
}
