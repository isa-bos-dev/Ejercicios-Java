package org.example;

import java.util.concurrent.BlockingQueue;

/**
 * La clase Barista representa al barista en la cafetería.
 * El barista es un hilo que procesa los pedidos de la BlockingQueue en orden de llegada.
 */
public class Barista implements Runnable{
    private final BlockingQueue<String> pedidos;
    private final int numeroClientes;

    /**
     * Constructor de la clase Barista.
     *
     * @param pedidos        La BlockingQueue de donde el barista tomará los pedidos.
     * @param numeroClientes El número total de clientes que el barista debe atender.
     */
    public Barista(BlockingQueue<String> pedidos, int numeroClientes) {
        this.pedidos = pedidos;
        this.numeroClientes = numeroClientes;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numeroClientes; i++) {
                // El barista toma un pedido de la cola (bloquea si no hay pedidos)
                String pedido = pedidos.take();
                System.out.println("Barista está preparando el " + pedido);

                // Simulamos el tiempo de preparación del café
                Thread.sleep(1000);

                // El barista entrega el pedido al cliente
                System.out.println("Barista entrega el " + pedido);
            }
            System.out.println("Todos los pedidos han sido servidos. La cafetería cierra.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El barista fue interrumpido.");
        }
    }
}
