package model.services;

import model.dao.UserDAO;
import model.entities.User;

import java.util.ArrayList;
import java.util.List;
import util.Validator;

//sirve para la logica del negocio, validaciones y reglas.
//usa los metodos del DAO

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers(){
        List<User> res = new ArrayList<>();
        try{
            res = userDAO.getAllUsers();
        }catch (Exception e){
            System.out.printf("[ERROR]:\n + %s", e.toString());
        }
        return res;
    }

    public boolean addUser(User u){
        if(validateUser(u)){
            return userDAO.addUser(u);
        }
        return false;
    }

    public boolean deleteUserById(int id_user){
        return userDAO.deleteUserById(id_user);
    }

    public boolean updateUser(User user){
        if(validateUser(user)){
            return userDAO.updateUser(user);
        }
        return false;
    }

    public User findUserById(int id_user){
        return userDAO.findUserById(id_user);
    }

    public boolean validateUser(User u){
        
        u.setNames(u.getNames().trim());
        u.setLastnames(u.getLastnames().trim());
        
        u.setDescription(u.getDescription().trim());


        
        if(u.getNames().isEmpty()){
            System.out.printf("[ERROR] Los nombres no pueden estar vacios\n");
            return false;
        }
        
        if(u.getLastnames().isEmpty()){
            System.out.printf("[ERROR] Los apellidos no pueden estar vacios\n");
            return false;
        }
        if(!Validator.isOnlyLetters(u.getNames())){
            System.out.printf("[ERROR] Los nombres solo deben contener letras\n");
            return false;
        }
        if(!Validator.isOnlyLetters(u.getLastnames())){
            System.out.printf("[ERROR] Los apellidos solo deben contener letras\n");
            return false;
        }
        if(u.getAmount() < 0){
            System.out.printf("[ERROR] la deuda debe ser positiva\n");
            return false;
        }
        
        //los nombres y apellidos simpre se guardan en miniscula

        u.setNames(u.getNames().toLowerCase());
        u.setLastnames(u.getLastnames().toLowerCase());
        u.setDescription(u.getDescription().toLowerCase());
        

        return true;
    }
}
