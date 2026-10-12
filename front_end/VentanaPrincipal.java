import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private final ListaSimple lista = new ListaSimple();

    private final JTextField campoId = new JTextField();
    private final JTextField campoNombre = new JTextField();
    private final JTextField campoPaterno = new JTextField();
    private final JTextField campoMaterno = new JTextField();
    private final JTextField campoEdad = new JTextField();
    private final JTextField campoCurp = new JTextField();
    private final JTextField campoTelefono = new JTextField();
    private final JTextField campoPosicion = new JTextField();
    private final JTextField campoBuscar = new JTextField();

    private final JTextArea areaLista = new JTextArea();
    private final JTextArea etiquetaEstado = new JTextArea("Lista vacia");

    public VentanaPrincipal() {
        setTitle("Venta de Boletos - Lista Simple");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 720);
        setMinimumSize(new Dimension(660, 620));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(6, 6));

        JPanel panelSuperior = new JPanel(new BorderLayout(4, 4));
        panelSuperior.add(crearFormulario(), BorderLayout.NORTH);
        panelSuperior.add(crearBotones(), BorderLayout.SOUTH);

        areaLista.setEditable(false);

        etiquetaEstado.setEditable(false);
        etiquetaEstado.setLineWrap(true);
        etiquetaEstado.setWrapStyleWord(true);
        etiquetaEstado.setRows(3);
        JScrollPane scrollEstado = new JScrollPane(etiquetaEstado);
        scrollEstado.setPreferredSize(new Dimension(0, 90));

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(areaLista), BorderLayout.CENTER);
        add(scrollEstado, BorderLayout.SOUTH);

        refrescarVista();
    }

    private JPanel crearFormulario() {
        JPanel panelEtiquetas = new JPanel(new GridLayout(0, 1, 2, 2));
        JPanel panelCampos = new JPanel(new GridLayout(0, 1, 2, 2));
        agregarCampo(panelEtiquetas, panelCampos, "ID:", campoId);
        agregarCampo(panelEtiquetas, panelCampos, "Nombre:", campoNombre);
        agregarCampo(panelEtiquetas, panelCampos, "Apellido paterno:", campoPaterno);
        agregarCampo(panelEtiquetas, panelCampos, "Apellido materno:", campoMaterno);
        agregarCampo(panelEtiquetas, panelCampos, "Edad:", campoEdad);
        agregarCampo(panelEtiquetas, panelCampos, "CURP:", campoCurp);
        agregarCampo(panelEtiquetas, panelCampos, "Telefono:", campoTelefono);
        agregarCampo(panelEtiquetas, panelCampos, "Posicion:", campoPosicion);
        agregarCampo(panelEtiquetas, panelCampos, "Valor a buscar (ID):", campoBuscar);

        JPanel panel = new JPanel(new BorderLayout(6, 0));
        panel.add(panelEtiquetas, BorderLayout.WEST);
        panel.add(panelCampos, BorderLayout.CENTER);
        return panel;
    }

    private void agregarCampo(JPanel panelEtiquetas, JPanel panelCampos, String etiqueta, JTextField campo) {
        panelEtiquetas.add(new JLabel(etiqueta));
        panelCampos.add(campo);
    }

    private JPanel crearBotones() {
        JPanel panel = new JPanel(new GridLayout(0, 3, 2, 2));

        panel.add(crearBoton("Insertar inicio", e -> insertarAlInicio()));
        panel.add(crearBoton("Insertar final", e -> insertarAlFinal()));
        panel.add(crearBoton("Insertar posicion", e -> insertarEnPosicion()));

        panel.add(crearBoton("Remover inicio", e -> removerAlInicio()));
        panel.add(crearBoton("Remover final", e -> removerAlFinal()));
        panel.add(crearBoton("Remover posicion", e -> removerPorPosicion()));

        panel.add(crearBoton("Ver inicio", e -> verAlInicio()));
        panel.add(crearBoton("Ver final", e -> verAlFinal()));
        panel.add(crearBoton("Ver posicion", e -> verPorPosicion()));

        panel.add(crearBoton("Buscar valor", e -> buscarPorValor()));
        panel.add(crearBoton("Vaciar lista", e -> vaciarLista()));

        return panel;
    }

    private JButton crearBoton(String texto, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        return boton;
    }

    private void insertarAlInicio() {
        Persona persona = leerPersona();
        if (persona == null) {
            return;
        }
        lista.insertarAlInicio(persona);
        refrescarVista();
        etiquetaEstado.setText("Insertado al inicio: " + persona);
    }

    private void insertarAlFinal() {
        Persona persona = leerPersona();
        if (persona == null) {
            return;
        }
        lista.insertarAlFinal(persona);
        refrescarVista();
        etiquetaEstado.setText("Insertado al final: " + persona);
    }

    private void insertarEnPosicion() {
        Integer posicion = validarPosicion(true);
        if (posicion == null) {
            return;
        }
        Persona persona = leerPersona();
        if (persona == null) {
            return;
        }
        lista.insertarEnPosicion(persona, posicion);
        refrescarVista();
        etiquetaEstado.setText("Insertado en posicion " + posicion + ": " + persona);
    }

    private void removerAlInicio() {
        Persona persona = lista.removerAlInicio();
        refrescarVista();
        etiquetaEstado.setText(persona == null
                ? "Lista vacia, nada que remover"
                : "Removido del inicio: " + persona);
    }

    private void removerAlFinal() {
        Persona persona = lista.removerAlFinal();
        refrescarVista();
        etiquetaEstado.setText(persona == null
                ? "Lista vacia, nada que remover"
                : "Removido del final: " + persona);
    }

    private void removerPorPosicion() {
        Integer posicion = validarPosicion(false);
        if (posicion == null) {
            return;
        }
        Persona persona = lista.removerPorPosicion(posicion);
        refrescarVista();
        etiquetaEstado.setText("Removido en posicion " + posicion + ": " + persona);
    }

    private void verAlInicio() {
        Persona persona = lista.mostrarAlInicio();
        etiquetaEstado.setText(persona == null ? "Lista vacia" : "Inicio: " + persona);
    }

    private void verAlFinal() {
        Persona persona = lista.mostrarAlFinal();
        etiquetaEstado.setText(persona == null ? "Lista vacia" : "Final: " + persona);
    }

    private void verPorPosicion() {
        Integer posicion = validarPosicion(false);
        if (posicion == null) {
            return;
        }
        Persona persona = lista.mostrarPorPosicion(posicion);
        etiquetaEstado.setText("Posicion " + posicion + ": " + persona);
    }

    private void buscarPorValor() {
        Integer id = leerEntero(campoBuscar, "valor a buscar");
        if (id == null) {
            return;
        }
        Persona persona = lista.buscarPorId(id);
        etiquetaEstado.setText(persona == null
                ? "No encontrado ID=" + id
                : "Encontrado: " + persona);
    }

    private void vaciarLista() {
        lista.vaciar();
        refrescarVista();
        etiquetaEstado.setText("Lista vaciada");
    }

    private Persona leerPersona() {
        String id = campoId.getText().trim();
        String nombre = campoNombre.getText().trim();
        String paterno = campoPaterno.getText().trim();
        String materno = campoMaterno.getText().trim();
        String edad = campoEdad.getText().trim();
        String curp = campoCurp.getText().trim();
        String telefono = campoTelefono.getText().trim();

        if (id.isEmpty() || nombre.isEmpty() || paterno.isEmpty() || materno.isEmpty()
                || edad.isEmpty() || curp.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos de la persona");
            return null;
        }

        try {
            return new Persona(
                    Integer.parseInt(id),
                    nombre,
                    paterno,
                    materno,
                    Integer.parseInt(edad),
                    curp,
                    telefono);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID y Edad deben ser numeros enteros");
            return null;
        }
    }

    private Integer leerEntero(JTextField campo, String nombreCampo) {
        String valor = campo.getText().trim();
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Capture un valor en el campo " + nombreCampo);
            return null;
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El campo " + nombreCampo + " debe ser un numero entero");
            return null;
        }
    }

    private Integer validarPosicion(boolean esInsercion) {
        Integer posicion = leerEntero(campoPosicion, "posicion");
        if (posicion == null) {
            return null;
        }
        int tamano = lista.tamano();
        if (esInsercion) {
            if (posicion < 0 || posicion > tamano) {
                JOptionPane.showMessageDialog(this,
                        "Indice fuera de rango. Para insertar use 0 a " + tamano);
                return null;
            }
        } else {
            if (tamano == 0) {
                JOptionPane.showMessageDialog(this, "La lista esta vacia");
                return null;
            }
            if (posicion < 0 || posicion >= tamano) {
                JOptionPane.showMessageDialog(this,
                        "Indice fuera de rango. Use 0 a " + (tamano - 1));
                return null;
            }
        }
        return posicion;
    }

    private void refrescarVista() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < lista.tamano(); i++) {
            texto.append("[").append(i).append("] ")
                    .append(lista.mostrarPorPosicion(i)).append("\n");
        }
        if (texto.length() == 0) {
            texto.append("(lista vacia)");
        }
        areaLista.setText(texto.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
