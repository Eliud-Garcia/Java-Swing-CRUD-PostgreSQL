package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    // Paneles
    private InicioPanel inicioPanel;
    private AgregarDeudorPanel agregarPanel;
    private ActualizarDeudorPanel actualizarPanel;
    private EliminarDeudorPanel eliminarPanel;
    private BuscarDeudorPanel buscarPanel;
    
    // Barra de navegación
    private JPanel navigationPanel;

    public MainFrame() {
        initComponents();
        setupLayout();
        setupNavigation();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setTitle("Sistema de Gestión de Deudores");
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Inicializar paneles
        inicioPanel = new InicioPanel();
        agregarPanel = new AgregarDeudorPanel();
        actualizarPanel = new ActualizarDeudorPanel();
        eliminarPanel = new EliminarDeudorPanel();
        buscarPanel = new BuscarDeudorPanel();
        
        // Agregar paneles al CardLayout
        mainPanel.add(inicioPanel, "INICIO");
        mainPanel.add(agregarPanel, "AGREGAR");
        mainPanel.add(actualizarPanel, "ACTUALIZAR");
        mainPanel.add(eliminarPanel, "ELIMINAR");
        mainPanel.add(buscarPanel, "BUSCAR");
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }

    private void setupNavigation() {
        navigationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton btnInicio = createNavButton("Inicio", "INICIO");
        JButton btnAgregar = createNavButton("Agregar Deudor", "AGREGAR");
        JButton btnActualizar = createNavButton("Actualizar Deudor", "ACTUALIZAR");
        JButton btnEliminar = createNavButton("Eliminar Deudor", "ELIMINAR");
        JButton btnBuscar = createNavButton("Buscar Deudor", "BUSCAR");
        
        navigationPanel.add(btnInicio);
        navigationPanel.add(btnAgregar);
        navigationPanel.add(btnActualizar);
        navigationPanel.add(btnEliminar);
        navigationPanel.add(btnBuscar);
        
        add(navigationPanel, BorderLayout.NORTH);
    }

    private JButton createNavButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.addActionListener(e -> showCard(cardName));
        return button;
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }

    // Getters para los paneles
    public InicioPanel getInicioPanel() { return inicioPanel; }
    public AgregarDeudorPanel getAgregarPanel() { return agregarPanel; }
    public ActualizarDeudorPanel getActualizarPanel() { return actualizarPanel; }
    public EliminarDeudorPanel getEliminarPanel() { return eliminarPanel; }
    public BuscarDeudorPanel getBuscarPanel() { return buscarPanel; }    
}
