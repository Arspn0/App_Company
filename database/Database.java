package database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Database {
    public static Connection getConnection(){
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/company_app", "root", "");
            
            return connect;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        return null;
    }
}