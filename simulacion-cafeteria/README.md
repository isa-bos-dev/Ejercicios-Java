# ☕ Ejercicio de Simulación de una Cafetería Concurrente en Java

Este ejercicio forma parte de una colección de prácticas de programación en Java. En esta simulación, crearás una cafetería donde varios clientes y un barista interactúan concurrentemente. Es una excelente oportunidad para repasar y fortalecer tus conocimientos sobre programación concurrente en Java.

## 📋 Descripción del Ejercicio

El programa simula una pequeña cafetería en la que varios **clientes** (hilos) llegan y realizan pedidos de café. Un **barista** (otro hilo) prepara los pedidos en orden de llegada y los entrega a los clientes. La simulación se detiene cuando todos los clientes han sido atendidos.

### 🧵 Hilos:

1. **Clientes:**
    - Cada cliente es un hilo que representa a una persona llegando a la cafetería.
    - Los clientes llegan en intervalos de tiempo aleatorios (por ejemplo, entre 500 ms y 2000 ms).
    - Cada cliente realiza un pedido y espera a que el barista le entregue su café.

2. **Barista:**
    - El barista es un hilo que procesa los pedidos en la cafetería.
    - Solo puede preparar un pedido a la vez, tomándose un tiempo fijo para cada café (por ejemplo, 1000 ms).
    - Una vez que el café está listo, lo entrega al cliente correspondiente.

### ⚙️ Sincronización:

- Se utiliza una **`BlockingQueue`** para manejar los pedidos de los clientes. Esta estructura permite que los clientes añadan sus pedidos a la cola y que el barista los procese en orden de llegada.

### 🚪 Parada del Sistema:

- La simulación se detiene después de que un número específico de clientes ha sido atendido. Todos los hilos se cerrarán correctamente al finalizar.

## 🎯 Objetivo del Ejercicio

Este ejercicio te permitirá practicar:

- **Manejo de hilos en Java** usando `ExecutorService`.
- **Programación concurrente** con múltiples hilos que simulan la interacción en un entorno real.
- **Sincronización de hilos** utilizando `BlockingQueue` para asegurar un flujo ordenado de los pedidos.
- **Coordinación y finalización de hilos** de manera segura cuando se cumplen ciertas condiciones.

## 🛠️ Clases y Conceptos Utilizados

### 1. `ExecutorService` y `Executors`
- **`ExecutorService`** se utilizará para gestionar la ejecución de los hilos de clientes y el barista.
- **`Executors.newFixedThreadPool(int n)`** se usará para crear un grupo fijo de hilos que manejarán las tareas concurrentes.

### 2. `BlockingQueue`
- **`BlockingQueue`** es una estructura de datos que permite a múltiples hilos insertar y extraer elementos de una cola de manera segura y ordenada.
- Se utilizará para manejar los pedidos de los clientes, garantizando que el barista los procese en el orden correcto.

### 3. `Thread.sleep()`
- Se usará para simular los tiempos de espera entre la llegada de los clientes y el tiempo de preparación del café.

### 4. `Runnable`
- Tanto los clientes como el barista se implementarán como `Runnable`, representando tareas que pueden ejecutarse en hilos concurrentes.

## 🚀 Ejecución del Programa

### Requisitos
- **Java 17 o superior**
- Un IDE como IntelliJ IDEA o Eclipse (opcional)

### Cómo Ejecutar

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/isabosdev/simulacion-cafeteria.git
   ```
2. Abre el proyecto en tu IDE preferido.
3. Ejecuta la clase principal `CafeteriaSimulation`.
4. Observa cómo los clientes realizan pedidos y el barista los atiende en orden.

### Ejemplo de Salida

```plaintext
Cliente 4 llega y pide un Café del Cliente 4
Barista está preparando el Café del Cliente 4
Cliente 3 llega y pide un Café del Cliente 3
Cliente 1 llega y pide un Café del Cliente 1
Cliente 2 llega y pide un Café del Cliente 2
Cliente 5 llega y pide un Café del Cliente 5
Barista entrega el Café del Cliente 4
Barista está preparando el Café del Cliente 3
Barista entrega el Café del Cliente 3
Barista está preparando el Café del Cliente 1
Barista entrega el Café del Cliente 1
Barista está preparando el Café del Cliente 2
Barista entrega el Café del Cliente 2
Barista está preparando el Café del Cliente 5
Barista entrega el Café del Cliente 5
Todos los pedidos han sido servidos. La cafetería cierra.
```

## 📚 Lecciones Aprendidas

Este ejercicio refuerza la comprensión de cómo manejar múltiples hilos en un entorno concurrido y simulado. También ilustra cómo utilizar estructuras de datos concurrentes como `BlockingQueue` para gestionar el acceso ordenado a recursos compartidos.

