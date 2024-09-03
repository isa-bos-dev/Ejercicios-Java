package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * La clase CafeteriaSimulation es el punto de entrada para la simulación de una cafetería concurrente.
 * Este programa simula una cafetería donde varios clientes realizan pedidos y un barista los atiende.
 * Los clientes y el barista se implementan como hilos que interactúan a través de una BlockingQueue.
 */
public class CafeteriaSimulation  {
    public static void main(String[] args) {
        // Número de clientes que llegarán a la cafetería
        int numeroClientes = 5;

        // Creamos una BlockingQueue para manejar los pedidos de manera concurrente y segura
        BlockingQueue<String> pedidos = new LinkedBlockingQueue<>();

        // Creamos un ExecutorService para gestionar los hilos de clientes y el barista
        try (ExecutorService executor = Executors.newFixedThreadPool(numeroClientes + 1)) {

            // Creamos y lanzamos los hilos para los clientes
            for (int i = 1; i <= numeroClientes; i++) {
                executor.submit(new Cliente(i, pedidos));
            }

            // Creamos y lanzamos el hilo para el barista
            executor.submit(new Barista(pedidos, numeroClientes));

            // Cerramos el ExecutorService una vez que todos los hilos han terminado
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}