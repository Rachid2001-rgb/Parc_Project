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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Attraction;

public class AttractionViewController implements Initializable {
     Connection cnx;
   public PreparedStatement st;
   public ResultSet result;
   private boolean update;
   int attractionId;
   String query = null;

   
 
    @FXML
    private Button btn_ajouter;

    @FXML
    private Button btn_sortir;

    @FXML
    private TextField txt_duree;
    public TextField getTxt_duree() {
		return txt_duree;
	}
	public void setTxt_duree(TextField txt_duree) {
		this.txt_duree = txt_duree;
	}
	public TextField getTxt_prix() {
		return txt_prix;
	}
	public void setTxt_prix(TextField txt_prix) {
		this.txt_prix = txt_prix;
	}
	public TextField getTxt_nom() {
		return txt_nom;
	}
	public void setTxt_nom(TextField txt_nom) {
		this.txt_nom = txt_nom;
	}



	@FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_nom;
   
   @FXML
   private TableView<Attraction> attractionTable;


    @FXML
    void ev_sortir(ActionEvent event) {
    	Parent root =null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/Sample4.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
    }
    @FXML
    void addAttraction(MouseEvent event) {
		cnx=ConnexionMysql.connexionDB();

    	String nom= txt_nom.getText();
    	String prix= txt_prix.getText();
    	String duree= txt_duree.getText();
        if (!nom.equals("")&&!prix.equals("")&&!duree.equals("")) {
            getQuery();
            insert();
            
            clean();

    	
    	
    	Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/Sample4.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}else {
	Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tout les champs!",javafx.scene.control.ButtonType.OK);
    alert.showAndWait();
        	
        }
    

    }

    
    
    public ObservableList<Attraction> data =FXCollections.observableArrayList();   
    
    public void showAttraction() {
    	String sql="select * from attraction";
    	try {
			st=cnx.prepareStatement(sql) ;
			result=st.executeQuery();
			while(result.next()) {
				data.add(new Attraction (result.getInt("id"),result.getString("txt_nom"),result.getFloat("txt_duree"),result.getFloat("txt_prix")));
				
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	void setTextField(int id, String name, float prix, float duree) {

        attractionId = id;
        txt_nom.setText(name);
        txt_prix.setText(String.valueOf(prix));
        txt_duree.setText(String.valueOf(duree));


    }

    void setUpdate(boolean b) {
        this.update = b;

    }


    @FXML
    private void clean() {
    	 txt_nom.setText(null);
         txt_prix.setText(null);
         txt_duree.setText(null);
        
    }
    
    private void getQuery() {

        if (update == false) {
        	 query="insert into attraction(txt_nom,txt_prix,txt_duree) values(?,?,?)";

        	 Alert alert = new Alert(AlertType.CONFIRMATION,"Attraction ajouté avec succès!",javafx.scene.control.ButtonType.OK);
 	        alert.showAndWait();
        }else{
            query = "UPDATE attraction SET "
                    + "`txt_nom`=?,"
                    + "`txt_prix`=?,"
                    + "`txt_duree`=?"
                    + " WHERE id = '"+attractionId+"'";
            Alert alert = new Alert(AlertType.CONFIRMATION,"Attraction modifié avec succès!",javafx.scene.control.ButtonType.OK);
	        alert.showAndWait();
        }

    }
    private void insert() {
    	String nom= txt_nom.getText();
    	String prix= txt_prix.getText();
    	String duree= txt_duree.getText();
        try {

        	st=cnx.prepareStatement(query);
			st.setString(1,nom);
			st.setString(2,prix);
			st.setString(3,duree);
			st.execute();
			

        } catch (SQLException ex) {
            Logger.getLogger(AttractionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	
}
