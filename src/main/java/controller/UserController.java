package controller;

import model.services.UserService;
import model.entities.User;
import view.MainFrame;
import java.util.*;

import javax.swing.JOptionPane;
import util.Validator;

public class UserController {

    private UserService userService;
    private MainFrame mainFrame;

    public UserController(MainFrame mf){
        this.mainFrame = mf;
        this.userService = new UserService();
        initController();
    }

    private void initController(){
        //Listeners para el panel INICIO
        mainFrame.getInicioPanel().getBtnActualizar().addActionListener(e -> cargarDatos());
        mainFrame.getInicioPanel().getBtnExportar().addActionListener(e -> exportarCSV());

        //Listeners para el panel AGREGAR
        mainFrame.getAgregarPanel().getBtnAgregar().addActionListener(e -> agregarDeudor());

        mainFrame.setVisible(true);
    }

    private void cargarDatos(){
        try{
            List<User> listaUsuarios = userService.getAllUsers();
            int cantidadUsuarios = listaUsuarios.size();
            long deudaTotal = 0;
            Object data [][] = new Object[cantidadUsuarios][5];
            for(int i = 0; i < listaUsuarios.size(); i++){
                User cur = listaUsuarios.get(i);
                data[i][0] = cur.getId();
                data[i][1] = cur.getNames();
                data[i][2] = cur.getLastnames();
                data[i][3] = cur.getAmount();
                data[i][4] = cur.getDescription();
                
                deudaTotal += cur.getAmount();
            }

            mainFrame.getInicioPanel().setEstadisticas(cantidadUsuarios, deudaTotal);
            mainFrame.getInicioPanel().setDatosDeudores(data);

            showMessage("Tabla actualizada :)");
        }catch(Exception e){
            showMessage("Error al cargar deudores");
        }
    }

    public void exportarCSV(){
        try {
            //TODO: agregar funcionalidad
            showMessage("Por ahora no sirve");
        }catch (Exception e){
            System.out.printf("[ERROR]: exportar CSV\n %s\n", e.toString());
        }
    }


    public void agregarDeudor(){
        String nombres = mainFrame.getAgregarPanel().getNombres();        
        String apellidos = mainFrame.getAgregarPanel().getApellidos();
        String deuda = mainFrame.getAgregarPanel().getDeuda();
        String descripcion = mainFrame.getAgregarPanel().getDescripcion();
        
        if(nombres.isEmpty()){
            showMessage("Los nombres no pueden estar vacios");
            return;
        }
        if(!Validator.isOnlyLetters(nombres)){
            showMessage("Los nombres solo deben ser letras");
            return;
        }

        if(apellidos.isEmpty()){
            showMessage("Los nombres no pueden estar vacios");
            return;
        }
        if(!Validator.isOnlyLetters(apellidos)){
            showMessage("Los nombres solo deben ser letras");
            return;
        }

        if(deuda.isEmpty()){
            showMessage("La deuda no puede estar vacia");
            return;
        }
        
        if(!Validator.isInteger(deuda)){
            showMessage("La deuda debe de ser un numero");
            return;
        }
        Long deuda_x = Long.parseLong(deuda);
        if(deuda_x < 0){
            showMessage("La deuda debe de ser positiva");
            return;
        }

        if(descripcion.isEmpty()){
            showMessage("La descripcion no puede estar vacia");
            return;
        }

        User user = new User();
        user.setNames(nombres);
        user.setLastnames(apellidos);
        user.setAmount(deuda_x);
        user.setDescription(descripcion);
        userService.addUser(user);
        showMessage("Deudor agregado correctamente");
        mainFrame.getAgregarPanel().limpiarFormulario();
    }
        
        
        
    void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
}