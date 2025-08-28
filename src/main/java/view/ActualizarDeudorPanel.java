package view;

import javax.swing.*;
import java.awt.*;

public class ActualizarDeudorPanel extends JPanel{
    private JTextField txtId;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDeuda;
    private JTextField txtDescripcion;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnLimpiar;

    public ActualizarDeudorPanel() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        txtId = new JTextField(10);
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtDeuda = new JTextField(10);
        txtDescripcion = new JTextField(20);
        
        btnBuscar = new JButton("Buscar");
        btnActualizar = new JButton("Actualizar Deudor");
        btnLimpiar = new JButton("Limpiar");
        
        // Deshabilitar campos hasta que se busque un deudor
        setCamposEditable(false);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("ID del Deudor:"));
        searchPanel.add(txtId);
        searchPanel.add(btnBuscar);
        
        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Nombres:"));
        formPanel.add(txtNombres);
        formPanel.add(new JLabel("Apellidos:"));
        formPanel.add(txtApellidos);
        formPanel.add(new JLabel("Deuda:"));
        formPanel.add(txtDeuda);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(txtDescripcion);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnLimpiar);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Centrar todo
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(mainPanel);
        
        add(centerPanel, BorderLayout.CENTER);
    }

    public String getId(){
        return txtId.getText().trim();
    }

    public String getNombres() {
        return txtNombres.getText().trim();
    }

    public String getApellidos() {
        return txtApellidos.getText().trim();
    }

    public String getDeuda(){
        return txtDeuda.getText().trim();
    }

    public String getDescripcion() {
        return txtDescripcion.getText().trim();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
    
    

    public void setCamposEditable(boolean editable) {
        txtNombres.setEditable(editable);
        txtApellidos.setEditable(editable);
        txtDeuda.setEditable(editable);
        txtDescripcion.setEditable(editable);
        btnActualizar.setEnabled(editable);
    }

    public void llenarFormulario(String nombres, String apellidos, long deuda, String descripcion) {
        txtNombres.setText(nombres);
        txtApellidos.setText(apellidos);
        txtDeuda.setText(String.valueOf(deuda));
        txtDescripcion.setText(descripcion);
        setCamposEditable(true);
    }

    public void limpiarFormulario() {
        txtId.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDeuda.setText("");
        txtDescripcion.setText("");
        setCamposEditable(false);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
