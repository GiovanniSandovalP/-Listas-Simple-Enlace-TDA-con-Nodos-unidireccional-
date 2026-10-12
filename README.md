# Listas Simple Enlace - TDA con Nodos Unidireccional

## Descripción

Proyecto académico de Estructuras de Datos que implementa una Lista Simple Enlazada con nodos unidireccionales en Java. Gestiona objetos Persona mediante operaciones de inserción, eliminación, búsqueda y consulta por inicio, final y posición, con una interfaz gráfica Swing tipo venta de boletos.

## Estructura del proyecto

- `back_end/`
  - `Nodo.java`: Nodo con un dato y un puntero al siguiente nodo.
  - `Persona.java`: Modelo de datos (id, nombre, apellidos, edad, CURP, teléfono).
  - `ListaSimple.java`: TDA con las operaciones de la lista enlazada.
- `front_end/`
  - `VentanaPrincipal.java`: Interfaz gráfica Swing para interactuar con la lista.

## Operaciones disponibles

- Insertar al inicio, al final y en una posición.
- Remover al inicio, al final y por posición.
- Mostrar el inicio, el final y un elemento por posición.
- Buscar por ID, verificar si está vacía, obtener tamaño y vaciar la lista.
