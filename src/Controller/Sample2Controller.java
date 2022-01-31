 package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import application.ConnexionMysql;
import application.DBParc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Attraction;

public class Sample2Controller implements Initializable {
	 public ObservableList<Attraction> AttractionList =FXCollections.observableArrayList();   
	 Connection cnx=null;
	    PreparedStatement st=null;
	    ResultSet result=null;
	    String query = null;
	    Attraction attraction=null;
	@FXML
    private TableView<Attraction> attractionTable;
	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;
	@FXML
	private void Sample(MouseEvent event) {
		bp.setCenter(ap);
	}
	@FXML
    private TableColumn<Attraction,String> duree_col;

   

    @FXML
    private TableColumn<Attraction, String> id_col;

    @FXML
    private TableColumn<Attraction, String> nom_col;

    @FXML
    private TableColumn<Attraction, String> prix_col;
	@FXML
	private Button button_logout;
	@FXML
	private void Sample2(MouseEvent event) {
		loadPage("/View/Sample2");
	}
	
	@FXML
	private void Sample3(MouseEvent event) {
		loadPage("/View/Sample3");
	}
	
	@FXML
	private void Sample4(MouseEvent event) {
		bp.setCenter(ap);
	}
	 @FXML
	    void close(ActionEvent event) {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
	    }
	private void loadPage(String page) {
		Parent root=null;
		
		try {
			root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		bp.setCenter(root);
	}
	private  void refresh() {
		 
		 try {
			 AttractionList.clear();
			  query="select * from attraction";
			 st=cnx.prepareStatement(query);
			 result=st.executeQuery();
			 while(result.next()) {
				 AttractionList.add(new Attraction (result.getInt("id"),result.getString("txt_nom"),result.getFloat("txt_duree"),result.getFloat("txt_prix")));
				 attractionTable.setItems(AttractionList);
				}
			
			 }
			 
			 
		 catch (SQLException e) {
	            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, e);
		}
		
		 

	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		button_logout.setOnAction(new EventHandler<ActionEvent>(){
    		@Override 
    		public void handle(ActionEvent event) {
    			DBParc.changeScene(event,"/View/LoginView.fxml", "Connecter!",null);
    			
    		}
    	});
		loadDate();
	}
	private void loadDate(){
		cnx=ConnexionMysql.connexionDB();
		refresh();
		id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<>("txt_nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<>("txt_prix"));
		duree_col.setCellValueFactory(new PropertyValueFactory<>("txt_duree"));
				
	}
	
	@FXML
    void add(ActionEvent event) {
		cnx=ConnexionMysql.connexionDB();
		 attraction = attractionTable.getSelectionModel().getSelectedItem();
		 query="insert into facture(nom,prix) values(?,?)";
	    	String nom= attraction.getTxt_nom();
	    	float prix= attraction.getTxt_prix();

	        try {

	        	st=cnx.prepareStatement(query);
				st.setString(1,nom);
				st.setDouble(2, prix);
				st.execute();
				 JOptionPane.showMessageDialog(null, "Attraction commande Success");
				

	        } catch (SQLException ex) {
	            Logger.getLogger(AttractionViewController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
            
	    }
	
	@FXML
    void facture(ActionEvent event) {
		Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/FactureView.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
    }
    
	
}
