package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class BuscarDeudorPanel extends JPanel{
      private JTextField txtBusqueda;
    private JTable tablaResultados;
    private DefaultTableModel tableModel;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JComboBox<String> cmbTipoBusqueda;

    public BuscarDeudorPanel() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        txtBusqueda = new JTextField(20);
        
        // Modelo de tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombres", "Apellidos", "Deuda", "Descripción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaResultados = new JTable(tableModel);
        
        // ComboBox para tipo de búsqueda
        cmbTipoBusqueda = new JComboBox<>(new String[]{"Por Nombre", "Por Apellido", "Por Deuda Mayor a"});
        
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Tipo:"));
        searchPanel.add(cmbTipoBusqueda);
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(txtBusqueda);
        searchPanel.add(btnBuscar);
        searchPanel.add(btnLimpiar);
        
        // Panel de resultados
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.add(new JLabel("Resultados de la Búsqueda:"), BorderLayout.NORTH);
        resultsPanel.add(new JScrollPane(tablaResultados), BorderLayout.CENTER);
        
        add(searchPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);
    }

    public String getTextoBusqueda() {
        return txtBusqueda.getText().trim();
    }

    public String getTipoBusqueda() {
        return (String) cmbTipoBusqueda.getSelectedItem();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void mostrarResultados(Object[][] datos) {
        tableModel.setRowCount(0);
        for (Object[] fila : datos) {
            tableModel.addRow(fila);
        }
    }

    public void limpiarBusqueda() {
        txtBusqueda.setText("");
        tableModel.setRowCount(0);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
