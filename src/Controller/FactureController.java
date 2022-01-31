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

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.FactureAttraction;

public class FactureController implements Initializable {
	 public ObservableList<FactureAttraction> FactureAttractionList =FXCollections.observableArrayList();   

	 Connection cnx=null;
	    PreparedStatement st=null;
	    ResultSet result=null;
	    FactureAttraction factureAttraction=null;
	   String query = null;
    @FXML
    
    private TableView<FactureAttraction> attractionTable2;



    @FXML
    private TextField sommeId;


@FXML
private TableColumn<FactureAttraction, String> nom_col;

@FXML
private TableColumn<FactureAttraction, String> prix_col;
@FXML
void close(ActionEvent event) {
	
	query = "DELETE FROM `facture` ";
		cnx=ConnexionMysql.connexionDB();
     try {
		st = cnx.prepareStatement(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     try {
		st.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
     Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

 	try {
 		Parent root = null;
 		root =  FXMLLoader.load(getClass().getResource("/View/Sample.fxml"));
 		
 		stage.setScene(new Scene(root));
 		stage.show();
 		
 		
 	} catch (IOException e) {
 		e.printStackTrace();
 	}
    
}

@FXML
void delete(ActionEvent event) {
	 try {
		 factureAttraction = attractionTable2.getSelectionModel().getSelectedItem();
       
         query="DELETE FROM facture WHERE nom ='"+factureAttraction.getTxt_nom()+"' LIMIT 1";
 		cnx=ConnexionMysql.connexionDB();
         st = cnx.prepareStatement(query);
         st.execute();
         refresh();
       

     } catch (SQLException ex) {
         Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
	   
 }
}

@FXML
void print(ActionEvent event) {

}

@FXML
void retour(ActionEvent event) {
	Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {
		Parent root = null;
		root =  FXMLLoader.load(getClass().getResource("/View/Sample.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}

  

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadDate();
		
	
	
	}
	
	private void loadDate(){
		cnx=ConnexionMysql.connexionDB();
		refresh();
		
		nom_col.setCellValueFactory(new PropertyValueFactory<>("txt_nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<>("txt_prix"));
		
				
	}
	private  void refresh() {
		 float s = 0;
		 try {
			 FactureAttractionList.clear();
			  query="select * from facture";
			 st=cnx.prepareStatement(query);
			 result=st.executeQuery();
			 while(result.next()) {
				 FactureAttractionList.add(new FactureAttraction (result.getString("nom"),result.getFloat("prix")));
				 attractionTable2.setItems(FactureAttractionList);
				
				 s=s+result.getFloat("prix");
				 
				}
			
			 }
			 
			 
		 catch (SQLException e) {
	            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, e);
		}
		
		 

	    }

}
