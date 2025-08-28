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
        mainFrame.getAgregarPanel().getBtnLimpiar().addActionListener(e -> limpiarCamposAgregar());
        mainFrame.setVisible(true);
        
        //Listeners para el panel ACTUALIZAR
        mainFrame.getActualizarPanel().getBtnBuscar().addActionListener(e -> buscarPorId());
        mainFrame.getActualizarPanel().getBtnActualizar().addActionListener(e -> actualizarDeudor());
        mainFrame.getActualizarPanel().getBtnLimpiar().addActionListener(e -> limpiarCamposActualizar());
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
        
        if(!validarNombre(nombres)){
            return;
        }
        if(!validarNombre(apellidos)){
            return;
        }
        if(!validarDeuda(deuda)){
            return;
        }
        if(!validarDescripcion(descripcion)){
            return;
        }
        
        long deuda_x = Long.parseLong(deuda);

        User user = new User();
        user.setNames(nombres);
        user.setLastnames(apellidos);
        user.setAmount(deuda_x);
        user.setDescription(descripcion);
        userService.addUser(user);
        showMessage("Deudor agregado correctamente");
        mainFrame.getAgregarPanel().limpiarFormulario();
    }
    
    //en panel AGREGAR
    public void limpiarCamposAgregar(){
        mainFrame.getAgregarPanel().limpiarFormulario();
    }
    
    public User buscarPorId(){
        String id_txt = mainFrame.getActualizarPanel().getId();
        if(!validarId(id_txt)){
            return null;
        }
        
        int id = Integer.parseInt(id_txt);
        
        User user = userService.findUserById(id);
        
        if(user == null){
            showMessage("El id no pertenece a un deudor");
            return null;
        }
        
        mainFrame.getActualizarPanel().setCamposEditable(true);
        mainFrame.getActualizarPanel().llenarFormulario(user.getNames(), user.getLastnames(), user.getAmount(), user.getDescription());
        
        return user;
    }
    
    public void actualizarDeudor(){
        String nuevosNombres = mainFrame.getActualizarPanel().getNombres();
        String nuevosApellidos = mainFrame.getActualizarPanel().getApellidos();
        String nuevaDeuda = mainFrame.getActualizarPanel().getDeuda();
        String nuevaDescripcion = mainFrame.getActualizarPanel().getDescripcion();
        
        
        
        if(!validarNombre(nuevosNombres)) return;
        if(!validarNombre(nuevosApellidos)) return;
        if(!validarDeuda(nuevaDeuda)) return;
        if(!validarDescripcion(nuevaDescripcion)) return;
        
        String id_txt = mainFrame.getActualizarPanel().getId();
        if(!validarId(id_txt)) return;
        
        int id = Integer.parseInt(id_txt);
        long deuda = Long.parseLong(nuevaDeuda);
        
        User cambios = new User();
        
        cambios.setId(id);
        cambios.setNames(nuevosNombres);
        cambios.setLastnames(nuevosApellidos);
        cambios.setAmount(deuda);
        cambios.setDescription(nuevaDescripcion);
        
        boolean ans = userService.updateUser(cambios);
        if(ans){
            showMessage("Deudor editado exitosamente");
            limpiarCamposActualizar();
        }else{
            showMessage("No se ha podido editar el deudor");
        }
    }
    
    public void limpiarCamposActualizar(){
        mainFrame.getActualizarPanel().limpiarFormulario();
    }
        
    void showMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    
    public boolean validarId(String id_txt){
        if(id_txt.isEmpty()){
            showMessage("El campo de id no puede estar vacio");
            return false;
        }
        if(!Validator.isInteger(id_txt)){
            showMessage("El id debe de ser un numero");
            return false;
        }
        Integer id = Integer.parseInt(id_txt);
        if(id <= 0){
            showMessage("El numero debe de ser positivo y mayor a cero");
            return false;
        }
        return true;
    }
    
    public boolean validarNombre(String nombres){
        if (nombres.isEmpty()) {
            showMessage("Los nombres no pueden estar vacios");
            return false;
        }
        if(!Validator.isOnlyLetters(nombres)){
            showMessage("Los nombres solo deben ser letras");
            return false;
        }
        return true;
    }
    
    public boolean validarDeuda(String deuda){
        if(deuda.isEmpty()){
            showMessage("La deuda no puede estar vacia");
            return false;
        }
        
        if(!Validator.isInteger(deuda)){
            showMessage("La deuda debe de ser un numero");
            return false;
        }
        Long deuda_x = Long.parseLong(deuda);
        if(deuda_x < 0){
            showMessage("La deuda debe de ser positiva");
            return false;
        }
        return true;
    }
    
    public boolean validarDescripcion(String descripcion){
        if(descripcion.isEmpty()){
            showMessage("La descripcion no puede estar vacia");
            return false;
        }
        return true;
    }
    
}