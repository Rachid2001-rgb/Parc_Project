 package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnexionMysql {
	private static Connection cn ;
	public static Connection connexionDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 cn= DriverManager.getConnection("jdbc:mysql://localhost:3306/parc1", "root", "");
			
			

			
			
		} catch (ClassNotFoundException | SQLException e) {


			Logger.getLogger(ConnexionMysql.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return cn;
	
	}

}
