package view;

import javax.swing.*;
import java.awt.*;


public class AgregarDeudorPanel extends JPanel{
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtDeuda;
    private JTextField txtDescripcion;
    private JButton btnAgregar;
    private JButton btnLimpiar;

    public AgregarDeudorPanel() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        txtDeuda = new JTextField(10);
        txtDescripcion = new JTextField(20);
        
        btnAgregar = new JButton("Agregar Deudor");
        btnLimpiar = new JButton("Limpiar Formulario");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Nombres:"));
        formPanel.add(txtNombres);
        formPanel.add(new JLabel("Apellidos:"));
        formPanel.add(txtApellidos);
        formPanel.add(new JLabel("Deuda:"));
        formPanel.add(txtDeuda);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(txtDescripcion);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnLimpiar);
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        centerPanel.add(formPanel, gbc);
        
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void limpiarFormulario() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtDeuda.setText("");
        txtDescripcion.setText("");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
