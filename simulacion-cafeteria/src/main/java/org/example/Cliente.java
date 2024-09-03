package org.example;

import java.util.concurrent.BlockingQueue;

/**
 * La clase Cliente representa a un cliente en la cafetería.
 * Cada cliente es un hilo que realiza un pedido y lo coloca en la BlockingQueue.
 */
public class Cliente implements Runnable {
    private final int idCliente;
    private final BlockingQueue<String> pedidos;

    /**
     * Constructor de la clase Cliente.
     *
     * @param idCliente Identificador único del cliente.
     * @param pedidos   La BlockingQueue donde el cliente colocará su pedido.
     */
    public Cliente(int idCliente, BlockingQueue<String> pedidos) {
        this.idCliente = idCliente;
        this.pedidos = pedidos;
    }

    @Override
    public void run() {
        try {
            // Simulamos el tiempo aleatorio de llegada del cliente a la cafetería
            Thread.sleep((long) (Math.random() * 2000 + 500));
            String pedido = "Café del Cliente " + idCliente;
            System.out.println("Cliente " + idCliente + " llega y pide un " + pedido);

            // El cliente coloca su pedido en la cola
            pedidos.put(pedido);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Cliente " + idCliente + " fue interrumpido.");
        }
    }
}
