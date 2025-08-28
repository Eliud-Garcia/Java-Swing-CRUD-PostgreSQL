package model.dao;

import model.entities.User;
import util.DataBaseConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    TABLA DEUDOR
    id                 ->Integer
    nombres            ->String
    apellidos          ->String
    cantidad_deuda     ->Long
    descripcion        ->String
 */

//aqui es para acceso directo a la base de datos
public class UserDAO {

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select * from DEUDOR;";
        try {
            Connection db_connection = DataBaseConnection.getConnection();
            Statement st = db_connection.createStatement();
            ResultSet res = st.executeQuery(sql);

            while (res.next()) {
                User user = new User();
                user.setId(res.getInt(1));
                user.setNames(res.getString(2));
                user.setLastnames(res.getString(3));
                user.setAmount(res.getLong(4));
                user.setDescription(res.getString(5));
                userList.add(user);
            }

        } catch (Exception e) {
            System.out.printf("[ERROR] listado deudores:\n %s \n", e.toString());
        }
        return userList;
    }

    public boolean addUser(User user){
        //insert into DEUDOR (nombres, apellidos, cantidad_deuda, descripcion) values ('Eliud', 'Garcia', 10000, 'ropa');
        String sql = "insert into DEUDOR (nombres, apellidos, cantidad_deuda, descripcion) values (?, ?, ?, ?);";
        try{
            Connection db_connection = DataBaseConnection.getConnection();
            PreparedStatement st = db_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getNames());
            st.setString(2, user.getLastnames());
            st.setLong(3, user.getAmount());
            st.setString(4, user.getDescription());
            st.executeUpdate();

            //obtener el ID generado
            try{
                ResultSet keys = st.getGeneratedKeys();
                if(keys.next()){
                    user.setId(keys.getInt(1));
                }
            }catch (SQLException e){
                System.out.printf("[ERROR] obtener id \n + %s \n", e.toString());
            }
        }catch(Exception e){
            System.out.printf("[ERROR] ingresar deudor\n + %s \n", e.toString());
            return false;
        }
        return true;
    }

    public boolean deleteUserById(int id_user){
        //delete from DEUDOR where id = 2;
        String sql = "delete from DEUDOR where id = ?";
        try{
            Connection db_Connection = DataBaseConnection.getConnection();
            CallableStatement cs = db_Connection.prepareCall(sql);
            cs.setInt(1, id_user);
            
            int affected_rows = cs.executeUpdate();

            if(affected_rows == 0){
                return false;
            }

        }catch(SQLException e){
            System.out.printf("[ERROR] eliminar deudor\n%s\n", e.toString());
            return false;
        }
        return true;
    }

    public boolean updateUser(User user){
        String sql = "update DEUDOR set nombres = ?, apellidos = ?, cantidad_deuda = ?, descripcion = ? where id = ?";
        try {
            Connection db_Connection = DataBaseConnection.getConnection();
            PreparedStatement pst = db_Connection.prepareStatement(sql);
            pst.setString(1, user.getNames());
            pst.setString(2, user.getLastnames());
            pst.setLong(3, user.getAmount());
            pst.setString(4, user.getDescription());
            pst.setInt(5, user.getId());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.printf("[ERROR] actualizar deudor\n %s", e.toString());
            return false;
        }
    }

    public User findUserById(int id_user){
        //select * from DEUDOR where id = 15;
        String sql = "select * from DEUDOR where id = ?";
        User user = null;

        try {
            Connection db_connection = DataBaseConnection.getConnection();
            PreparedStatement pst = db_connection.prepareStatement(sql);

            pst.setInt(1, id_user);
            
            try {
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    user = resultToUser(rs);
                }

            } catch (Exception e) {
                System.out.printf("[ERROR] obtener deudor:\n %s \n", e.toString());
            }

        } catch (Exception e) {
            System.out.printf("[ERROR] buscar deudor:\n %s \n", e.toString());
        }
        return user;
    }
    
    private User resultToUser(ResultSet rs){
        User user = new User();
        try{
            user.setId(rs.getInt("id"));
            user.setNames(rs.getString("nombres"));
            user.setLastnames(rs.getString("apellidos"));
            user.setAmount(rs.getLong("cantidad_deuda"));
            user.setDescription(rs.getString("descripcion"));
            
        }catch(SQLException e){
            System.out.printf("[ERROR] convertir ResultSet a Deudor:\n %s \n", e.toString());
        }
        return user;
    }
}
