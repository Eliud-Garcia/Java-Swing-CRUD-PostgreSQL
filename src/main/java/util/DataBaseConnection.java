package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String USER = "postgres";
    private static final String PASSWORD = "eliud";
    private static final String DB = "db_debtors";
    private static final String IP = "localhost";
    private static final String PORT = "5432";
    private static final String PATH = "jdbc:postgresql://" + IP + ":" + PORT + "/" + DB;

    public DataBaseConnection(){}

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(PATH, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException e){
            throw new SQLException("Driver de BD no encontrado", e);
        }

    }
}
