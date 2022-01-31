package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Article;
import application.Commerce;
import application.MySqlConnect.mysqlconnect;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.FactureArticle;

public class CommandeArticleController implements Initializable{
 
	
    @FXML
    private TableView<FactureArticle> article_table;

    @FXML
    private TableColumn<FactureArticle, String> col_art;

    @FXML
    private TableColumn<FactureArticle, String> col_dispo;

    @FXML
    private TableColumn<FactureArticle, String> col_prix;
    
	    ObservableList<Article> listM;
	    ObservableList<Article> dataList;
	    
	   
	    
	  
	   int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst1 = null;
	    
	    @FXML
	    void close(ActionEvent event) {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
	        stage.close();
	    }
    
    public void insert(MouseEvent event){
    	
  	    conn = mysqlconnect.ConnectDb();
  	   index = article_table.getSelectionModel().getSelectedIndex();
	    if (index <= -1){
	    
	        return;
	    }
	   
	    String sql = "insert into facture (nom,prix)values(?,?)";
  	   

  	        try {
  	            pst1 = conn.prepareStatement(sql);
  	           
  	         pst1.setString(1,col_art.getCellData(index).toString() );
  	            pst1.setString(2, col_prix.getCellData(index).toString());
  	            pst1.execute();
  	            
  	            JOptionPane.showMessageDialog(null, "Article commande Success");
  	            UpdateTable();
  	        
  	          //  search_user();
  	        } catch (Exception e) {
  	            JOptionPane.showMessageDialog(null, e);
  	        }
  	    
  	    }
    public void  getDatausers(){
        Connection conn = mysqlconnect.ConnectDb();
        ObservableList<FactureArticle> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from commandearticle");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new FactureArticle(rs.getString("nom"),rs.getString("disponible"),rs.getString("prix")));               
                article_table.setItems(list);
            }
        } catch (Exception e) {
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
    
    public void UpdateTable(){
    	getDatausers();
    	 
    	col_art.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_dispo.setCellValueFactory(new PropertyValueFactory<>("disponible"));
       col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
       
        
       
   }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		UpdateTable();
		
	}
	

}
