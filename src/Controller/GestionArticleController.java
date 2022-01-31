package Controller;

import java.net.URL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import application.MySqlConnect.mysqlconnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;


public class GestionArticleController implements Initializable{
	  @FXML
	    private TextField txt_id;

	    @FXML
	    private TextField txt_nom;

	    @FXML
	    private TextField txt_quantite;

	    @FXML
	    private TextField txt_categorie;

	    @FXML
	    private TextField txt_prix;
	    @FXML
	    private TextField txt_commerce;	   
	    
		@FXML
	    private Button ok_btn;
	    
	    private boolean update;

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	 
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst1 = null;
    
    PreparedStatement pst2 = null;
     

	 
	    @FXML
	    private void save(MouseEvent event) {
	    	   conn = mysqlconnect.ConnectDb();
	         String id_art = String.valueOf(txt_id.getText());
	         String nom = txt_nom.getText();
	         String quantite = String.valueOf(txt_quantite.getText());
	         String categorie = txt_categorie.getText();
	         String prix = String.valueOf(txt_prix.getText());
	         String id_com = String.valueOf(txt_commerce.getText());

	  
	    
	    	  if (id_art.isEmpty() || nom.isEmpty() || quantite.isEmpty() || categorie.isEmpty() || prix.isEmpty() || id_com.isEmpty()) {
	              Alert alert = new Alert(Alert.AlertType.ERROR);
	              alert.setHeaderText(null);
	              alert.setContentText("Please Fill All DATA");
	              alert.showAndWait();

	          } else {

	            insert();
	            clean();

	        }

	    }

	    @FXML
	    private void clean() {
	        txt_id.setText(null);
	        txt_nom.setText(null);
	       txt_quantite.setText(null);
	        txt_categorie.setText(null);
	        txt_prix.setText(null);
	        txt_commerce.setText(null);
	        
	    }
	    @FXML
	    private void close(MouseEvent event) {
	        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
	    }
   

		  public void Edit (){   
		       
		            conn = mysqlconnect.ConnectDb();
		            
		           String value1 = txt_id.getText();
		            String value2 = txt_nom.getText();
		            String value3 = txt_prix.getText();
		            String value4 = txt_categorie.getText();
		           String value5 = txt_quantite.getText();
		            String value6 = txt_commerce.getText();
		            if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty() || value4.isEmpty() || value5.isEmpty() || value6.isEmpty()) {
			              Alert alert = new Alert(Alert.AlertType.ERROR);
			              alert.setHeaderText(null);
			              alert.setContentText("Please Fill All DATA");
			              alert.showAndWait();
		            }else {
		            	  try {
		            String sql = "update commerce set id_commerce= '"+value6+"',nom= '"+value2+"',prix= '"+
		                    value3+"',categorie= '"+value4+"' where id_commerce = '"+value6+"'";
		            String sql2 = "update article set id_article= '"+value1+"',quantite= '"+value5+"' where id_article = '+"+value1+"'";
		      
		            pst1= conn.prepareStatement(sql);
		            pst1.execute();
		            pst2= conn.prepareStatement(sql2);
		            pst2.execute();
		            JOptionPane.showMessageDialog(null, "Update Success");
		           
		           
		            //search_user();
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null, e);
		        }}
		         
		    }
      private void insert() {

  	   
  	     
  	        String sql1 = "insert into commerce (id_commerce,nom,prix,categorie)values(?,?,?,? )";
  	        String sql2 = "insert into article(id_article,quantite,id_commerce) values(?,?,?)";
  	        try {
  	            pst1 = conn.prepareStatement(sql1);
  	            pst2 = conn.prepareStatement(sql2);
  	            pst1.setString(1, txt_commerce.getText());
  	            pst1.setString(2, txt_nom.getText());
  	            pst1.setString(3, txt_prix.getText());
  	            pst1.setString(4, txt_categorie.getText());
  	          pst1.execute();
  	            pst2.setString(1, txt_id.getText());
  	            pst2.setString(2, txt_quantite.getText());
  	            pst2.setString(3, txt_commerce.getText());
  	            
  	            pst2.execute();
  	            
  	            JOptionPane.showMessageDialog(null, "article Add succes");
  	        
  	           // search_user();
  	        } catch (Exception e) {
  	            JOptionPane.showMessageDialog(null, e);
  	        }
  	    }
      
      void setTextField(String id, String nom, String quantite, String categorie, String prix, String id_com) {

          txt_id.setText(id);
          txt_nom.setText(nom);
          txt_quantite.setText(quantite);
          txt_categorie.setText(categorie);
          txt_prix.setText(prix);
          txt_commerce.setText(id_com);


      }

      void setUpdate(boolean b) {
          this.update = b;

      }
  	 

	


}
