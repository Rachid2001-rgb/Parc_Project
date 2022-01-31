package application;

import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MySqlConnect {


/**
 *
 * @author amir
 */
public static class mysqlconnect {
    
    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/parc1","root","");
          
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    
    }
    
  public static ObservableList<Article> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<Article> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from commerce c,article a where c.id_commerce = a.id_commerce");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Article(rs.getString("id_article"), rs.getString("nom"),rs.getString("quantite"),rs.getString("categorie"),rs.getString("prix"),rs.getString("id_commerce")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
}
