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
import models.Repas;

public class RepasViewController implements Initializable {
     Connection cnx;
   public PreparedStatement st;
   public ResultSet result;
   private boolean update;
   int repasId;
   String query = null;

    @FXML
    private Button btn_ajouterR;

    @FXML
    private Button btn_sortirR;

    @FXML
    private TextField txt_qteR;
    public TextField getTxt_qteR() {
		return txt_qteR;
	}
	public void setTxt_qteR(TextField txt_qteR) {
		this.txt_qteR = txt_qteR;
	}
	public TextField getTxt_prixR() {
		return txt_prixR;
	}
	public void setTxt_prixR(TextField txt_prixR) {
		this.txt_prixR = txt_prixR;
	}
	public TextField getTxt_nomR() {
		return txt_nomR;
	}
	public void setTxt_nomR(TextField txt_nomR) {
		this.txt_nomR = txt_nomR;
	}



	@FXML
    private TextField txt_prixR;

    @FXML
    private TextField txt_nomR;
   
   @FXML
   private TableView<Repas> repasTable;


    @FXML
    void ev_sortirR(ActionEvent event) {
    	Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/Sample5.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
    }
    @FXML
    void addRepas(MouseEvent event) {
		cnx=ConnexionMysql.connexionDB();

    	String nomR= txt_nomR.getText();
    	String prixR= txt_prixR.getText();
    	String qteR= txt_qteR.getText();
        if (!nomR.equals("")&&!prixR.equals("")&&!qteR.equals("")) {
            getQuery();
            insert();
            
            clean();

    	
    	
    	Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/Sample5.fxml"));
		
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

    
    
    public ObservableList<Repas> data =FXCollections.observableArrayList();   
    
    public void showAttraction() {
    	String sql="select * from repas";
    	try {
			st=cnx.prepareStatement(sql) ;
			result=st.executeQuery();
			while(result.next()) {
				data.add(new Repas (result.getInt("id"),result.getString("txt_nomR"),result.getInt("txt_qteR"),result.getFloat("txt_prixR")));
				
			}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	void setTextField(int id, String nameR, float prixR, int qteR) {

        repasId = id;
        txt_nomR.setText(nameR);
        txt_prixR.setText(String.valueOf(prixR));
        txt_qteR.setText(String.valueOf(qteR));


    }

    void setUpdate(boolean b) {
        this.update = b;

    }


    @FXML
    private void clean() {
    	 txt_nomR.setText(null);
         txt_prixR.setText(null);
         txt_qteR.setText(null);
        
    }
    
    private void getQuery() {

        if (update == false) {
        	 query="insert into repas(txt_nomR,txt_prixR,txt_dureeR) values(?,?,?)";

        	 Alert alert = new Alert(AlertType.CONFIRMATION,"Repas ajouté avec succès!",javafx.scene.control.ButtonType.OK);
 	        alert.showAndWait();
        }else{
            query = "UPDATE repas SET "
                    + "`txt_nomR`=?,"
                    + "`txt_prixR`=?,"
                    + "`txt_qteR`=?"
                    + " WHERE id = '"+repasId+"'";
            Alert alert = new Alert(AlertType.CONFIRMATION,"Repas modifié avec succès!",javafx.scene.control.ButtonType.OK);
	        alert.showAndWait();
        }

    }
    private void insert() {
    	String nomR= txt_nomR.getText();
    	String prixR= txt_prixR.getText();
    	String qteR= txt_qteR.getText();
        try {

        	st=cnx.prepareStatement(query);
			st.setString(1,nomR);
			st.setString(2,prixR);
			st.setString(3,qteR);
			st.execute();
			

        } catch (SQLException ex) {
            Logger.getLogger(RepasViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
	
}
