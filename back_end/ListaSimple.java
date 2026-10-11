public class ListaSimple {

    private Nodo primero;

    public ListaSimple() {
        this.primero = null;
    }

    public void insertarAlInicio(Persona persona) {
        Nodo nuevo = new Nodo(persona);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    public void insertarAlFinal(Persona persona) {
        Nodo nuevo = new Nodo(persona);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void insertarEnPosicion(Persona persona, int posicion) {
        if (posicion <= 0) {
            insertarAlInicio(persona);
            return;
        }
        Nodo nuevo = new Nodo(persona);
        Nodo anterior = primero;
        int indice = 0;
        while (anterior != null && indice < posicion - 1) {
            anterior = anterior.getSiguiente();
            indice++;
        }
        if (anterior == null) {
            insertarAlFinal(persona);
        } else {
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente(nuevo);
        }
    }

    public Persona removerAlInicio() {
        if (primero == null) {
            return null;
        }
        Persona persona = primero.getDato();
        primero = primero.getSiguiente();
        return persona;
    }

    public Persona removerAlFinal() {
        if (primero == null) {
            return null;
        }
        if (primero.getSiguiente() == null) {
            Persona persona = primero.getDato();
            primero = null;
            return persona;
        }
        Nodo anterior = primero;
        while (anterior.getSiguiente().getSiguiente() != null) {
            anterior = anterior.getSiguiente();
        }
        Persona persona = anterior.getSiguiente().getDato();
        anterior.setSiguiente(null);
        return persona;
    }

    public Persona removerPorPosicion(int posicion) {
        if (primero == null) {
            return null;
        }
        if (posicion <= 0) {
            return removerAlInicio();
        }
        Nodo anterior = primero;
        int indice = 0;
        while (anterior.getSiguiente() != null && indice < posicion - 1) {
            anterior = anterior.getSiguiente();
            indice++;
        }
        if (anterior.getSiguiente() == null) {
            return null;
        }
        Persona persona = anterior.getSiguiente().getDato();
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        return persona;
    }

    public Persona mostrarAlInicio() {
        if (primero == null) {
            return null;
        }
        return primero.getDato();
    }

    public Persona mostrarAlFinal() {
        if (primero == null) {
            return null;
        }
        Nodo actual = primero;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    public Persona mostrarPorPosicion(int posicion) {
        if (primero == null || posicion < 0) {
            return null;
        }
        Nodo actual = primero;
        int indice = 0;
        while (actual != null && indice < posicion) {
            actual = actual.getSiguiente();
            indice++;
        }
        if (actual == null) {
            return null;
        }
        return actual.getDato();
    }

    public Persona buscarPorId(int id) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.getDato().getId() == id) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int tamano() {
        int contador = 0;
        Nodo actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public void vaciar() {
        primero = null;
    }
}
