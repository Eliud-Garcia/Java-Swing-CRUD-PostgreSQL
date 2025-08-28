package view;

import javax.swing.*;
import java.awt.*;

public class EliminarDeudorPanel extends JPanel{
    private JTextField txtId;
    private JTextArea txtDetalles;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public EliminarDeudorPanel() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        txtId = new JTextField(10);
        txtDetalles = new JTextArea(6, 30);
        txtDetalles.setEditable(false);
        
        btnBuscar = new JButton("Buscar por ID");
        btnEliminar = new JButton("Eliminar Deudor");
        btnLimpiar = new JButton("Limpiar");
        
        btnEliminar.setEnabled(false);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("ID del Deudor:"));
        searchPanel.add(txtId);
        searchPanel.add(btnBuscar);
        
        // Panel de detalles
        JPanel detailsPanel = new JPanel(new BorderLayout(10, 10));
        detailsPanel.add(new JLabel("Detalles del Deudor:"), BorderLayout.NORTH);
        detailsPanel.add(new JScrollPane(txtDetalles), BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnLimpiar);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Centrar todo
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(mainPanel);
        
        add(centerPanel, BorderLayout.CENTER);
    }

    public int getId() throws NumberFormatException {
        return Integer.parseInt(txtId.getText().trim());
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void mostrarDetalles(String detalles) {
        txtDetalles.setText(detalles);
        btnEliminar.setEnabled(true);
    }

    public void limpiarFormulario() {
        txtId.setText("");
        txtDetalles.setText("");
        btnEliminar.setEnabled(false);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarConfirmacion(String mensaje, Runnable onConfirm) {
        int option = JOptionPane.showConfirmDialog(this, 
            mensaje, 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (option == JOptionPane.YES_OPTION) {
            onConfirm.run();
        }
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }    
}
