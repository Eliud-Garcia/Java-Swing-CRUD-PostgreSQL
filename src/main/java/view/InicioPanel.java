package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class InicioPanel extends JPanel{
    private JTable tablaDeudores;
    private DefaultTableModel tableModel;
    private JLabel lblEstadisticas;
    private JLabel lblDeudaTotal;
    private JButton btnActualizar;
    private JButton btnExportar;
    private JScrollPane scrollPane;

    public InicioPanel() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        // Modelo de tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombres", "Apellidos", "Deuda", "Descripción"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3) return Integer.class;
                return String.class;
            }
        };
        
        tablaDeudores = new JTable(tableModel);
        tablaDeudores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaDeudores.setAutoCreateRowSorter(true);
        tablaDeudores.getTableHeader().setReorderingAllowed(false);
        
        scrollPane = new JScrollPane(tablaDeudores);
        
        // Labels de estadísticas
        lblEstadisticas = new JLabel("Resumen de Deudores", SwingConstants.CENTER);
        lblEstadisticas.setFont(new Font("Arial", Font.BOLD, 16));
        
        lblDeudaTotal = new JLabel("Deuda total: $0 | Total deudores: 0", SwingConstants.CENTER);
        lblDeudaTotal.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Botones
        btnActualizar = new JButton("Actualizar Datos");
        btnExportar = new JButton("Exportar a CSV");
        
        // Tooltips
        btnActualizar.setToolTipText("Actualizar la lista de deudores");
        btnExportar.setToolTipText("Exportar datos a archivo CSV");
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior con estadísticas
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.add(lblEstadisticas);
        statsPanel.add(lblDeudaTotal);
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(btnActualizar);
        buttonPanel.add(btnExportar);
        
        // Panel norte (estadísticas + botones)
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(statsPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Agregar margen
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void setDatosDeudores(Object[][] datos) {
        tableModel.setRowCount(0);
        for (Object[] fila : datos) {
            tableModel.addRow(fila);
        }
    }

    public void setEstadisticas(int totalDeudores, long deudaTotal) {
        lblDeudaTotal.setText("Deuda total: $" + deudaTotal + " | Total deudores: " + totalDeudores);
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }

    public int getSelectedDeudorId() {
        int selectedRow = tablaDeudores.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = tablaDeudores.convertRowIndexToModel(selectedRow);
            return (Integer) tableModel.getValueAt(modelRow, 0);
        }
        return -1;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void ajustarAnchoColumnas() {
        // Ajustar automáticamente el ancho de las columnas
        tablaDeudores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        int[] anchos = {50, 150, 150, 80, 200};
        for (int i = 0; i < anchos.length; i++) {
            tablaDeudores.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
}
