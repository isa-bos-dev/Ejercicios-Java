# 🧵 Ejercicio con Hilos en Java

Este proyecto forma parte de una colección de ejercicios prácticos de programación en Java. En este ejercicio, implementamos un programa que utiliza **hilos** para ejecutar dos tareas de manera concurrente. Es ideal para repasar conceptos clave de programación concurrente y el manejo de hilos en Java.

## 📋 Descripción del Ejercicio

El programa ejecuta **dos hilos** en paralelo:

1. **🧵 Hilo 1 - Búsqueda de Letra:**
    - Solicita al usuario una letra del alfabeto.
    - Muestra en la consola las letras del alfabeto desde 'A' hasta la letra ingresada.
    - **⏲️ Temporizador:** Cada letra se muestra con un retraso de 500 ms.
    - Una vez que el hilo encuentra la letra especificada por el usuario, establece un indicador (`found`) que hace que el segundo hilo se detenga.

2. **🧵 Hilo 2 - Conteo Regresivo:**
    - Solicita al usuario un número positivo.
    - Realiza un conteo regresivo desde el número proporcionado, mostrando cada número en la consola.
    - **⏲️ Temporizador:** Cada número se muestra con un retraso de 600 ms.
    - El hilo continuará contando hasta que el primer hilo encuentre la letra especificada.

3. **⚙️ Condición de Parada:**
    - El **Hilo 2** se detendrá cuando el **Hilo 1** encuentre la letra especificada por el usuario.
    - Al finalizar su tarea, cada hilo imprimirá un mensaje en la consola indicando que su trabajo ha terminado.

## 🎯 Objetivo del Ejercicio

Este ejercicio te ayudará a repasar:

- **Manejo de hilos en Java** usando `ExecutorService`.
- **Programación concurrente** y la coordinación entre hilos.
- Uso de **temporizadores** (`Thread.sleep`) para controlar la ejecución en intervalos específicos.
- **Sincronización entre hilos** utilizando variables atómicas como `AtomicBoolean` y `AtomicInteger`.

## 🛠️ Clases y Conceptos Utilizados

### 1. `ExecutorService` y `Executors`
- **`ExecutorService`** es una interfaz que proporciona métodos para gestionar la ejecución de tareas asincrónicas en hilos. Se utiliza para controlar el ciclo de vida de los hilos y evitar la creación manual de hilos.
- **`Executors.newVirtualThreadPerTaskExecutor()`** crea un ejecutor que administra **hilos virtuales** (parte de `Project Loom` en Java). Los hilos virtuales son ligeros y más eficientes que los hilos tradicionales, permitiendo manejar miles de tareas concurrentes sin sobrecargar el sistema.

### 2. `Thread.sleep()`
- Este método se utiliza para pausar la ejecución del hilo actual durante un período de tiempo específico (en milisegundos). En este ejercicio, usamos `Thread.sleep(500)` y `Thread.sleep(600)` para simular el temporizador en cada hilo.

### 3. **Variables Atómicas**
- **`AtomicBoolean`** y **`AtomicInteger`** son clases utilizadas para gestionar variables que pueden ser modificadas de manera segura por múltiples hilos sin necesidad de sincronización explícita.
- **`AtomicBoolean found`**: Indica si la letra ha sido encontrada por el hilo que busca la letra.
- **`AtomicInteger atomicInteger`**: Almacena el número desde el cual se realiza el conteo regresivo.

### 4. `Runnable`
- Un `Runnable` es una interfaz funcional que representa una tarea que puede ser ejecutada por un hilo. En este ejercicio, cada hilo (`thread1` y `thread2`) se implementa como un `Runnable`.

## 🚀 Ejecución del Programa

### Requisitos
- **Java 17 o superior** (Recomendado: Java 21 o superior para soporte completo de hilos virtuales)
- Un IDE como IntelliJ IDEA o Eclipse (opcional)

### Cómo Ejecutar

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/isabosdev/ejercicio-hilos.git
   ```
2. Abre el proyecto en tu IDE preferido.
3. Ejecuta la clase `VirtualThreadMain`.
4. Sigue las instrucciones en la consola para ingresar la letra y el número.

### Ejemplo de Salida

```plaintext
Introduce una letra: D
Introduce un número positivo: 5
A5
B4
C3
D - Letra D encontrada
 - Trabajo del hilo  Thread-character terminado
2
 - Trabajo del hilo  Thread-number terminado
```

## 📚 Lecciones Aprendidas

Este ejercicio refuerza la importancia de la **concurrencia controlada** en aplicaciones Java, mostrando cómo gestionar tareas paralelas de manera eficiente usando `ExecutorService`, temporizadores, y variables atómicas. También ilustra cómo coordinar la terminación de hilos en respuesta a condiciones específicas dentro del programa.

## ✨ Créditos

- Ejercicio inspirado por el video de [YouTube](https://youtu.be/ec8PetXpKT0?si=l9X9oNOk7iQHLx-3) de **UnProgramadorNace**.

---