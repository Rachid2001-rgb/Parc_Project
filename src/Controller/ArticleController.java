package Controller;

import java.io.IOException;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import application.Article;
import application.Commerce;
import application.MySqlConnect.mysqlconnect;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class ArticleController implements Initializable {
	
	GestionArticleController gestionarticlecontroller = new GestionArticleController();

	  @FXML
	    private TableView<Article> table_article;

	    @FXML
	    private TableColumn<Article,String> col_id_art;

	    @FXML
	    private TableColumn<Article, String> col_nom;

	    @FXML
	    private TableColumn<Article, String> col_quantite;

	    @FXML
	    private TableColumn<Article, String> col_categorie;

	    @FXML
	    private TableColumn<Article, String> col_prix;
	    @FXML
	    private TableColumn<Commerce,String> col_id_com;
	    @FXML
	    private TextField filterField;

	    @FXML
	    private Button ok_btn;


	


	       
	    ObservableList<Article> listM;
	    ObservableList<Article> dataList;
	    
	   
	    
	   Article article = null;
	   int index = -1;
	    
	    Connection conn =null;
	    ResultSet rs = null;
	    PreparedStatement pst1 = null;
	    
	    PreparedStatement pst2 = null;
	     
	   
	    

	
	    @FXML
	    void edit(ActionEvent event) {
		   
		  article = table_article.getSelectionModel().getSelectedItem();

			 FXMLLoader loader = new FXMLLoader ();
           loader.setLocation(getClass().getResource("/View/UpdateArticle.fxml"));
           try {
               loader.load();
           } catch (IOException ex) {
               Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
           }
          GestionArticleController gestionarticlecontroller=loader.getController();
           gestionarticlecontroller.setUpdate(true);
           gestionarticlecontroller.setTextField(article.getId(), article.getNom(), 
        		   article.getQuantite(),article.getCategorie(),article.getPrix(),article.getId_com());
   		Parent parent = loader.getRoot();
   	  Stage stage = new Stage();
           stage.setScene(new Scene(parent));
           stage.show();

	    }
	

	    public void Delete(){
	    	
	  	    conn = mysqlconnect.ConnectDb();
	  	   index = table_article.getSelectionModel().getSelectedIndex();
		    if (index <= -1){
		    
		        return;
		    }
		   
	  	    String sql1 = "delete from commerce where id_commerce = ?";
	  	    String sql2 = "delete from article where id_article = ?";

	  	        try {
	  	            pst1 = conn.prepareStatement(sql1);
	  	            pst2 = conn.prepareStatement(sql2);
	  	         pst1.setString(1,col_id_com.getCellData(index).toString() );
	  	            pst2.setString(1, col_id_art.getCellData(index).toString());
	  	            pst2.execute();
	  	            pst1.execute();
	  	            JOptionPane.showMessageDialog(null, "Delete Success");
	  	            UpdateTable();
	  	        
	  	          //  search_user();
	  	        } catch (Exception e) {
	  	            JOptionPane.showMessageDialog(null, e);
	  	        }
	  	    
	  	    }

	
	    public void  getDatausers(){
	        Connection conn = mysqlconnect.ConnectDb();
	        ObservableList<Article> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from commerce c,article a where c.id_commerce = a.id_commerce");
	            ResultSet rs = ps.executeQuery();
	            
	            while (rs.next()){   
	                list.add(new Article(rs.getString("id_article"), rs.getString("nom"),rs.getString("quantite"),rs.getString("categorie"),rs.getString("prix"),rs.getString("id_commerce")));               
	                table_article.setItems(list);
	            }
	        } catch (Exception e) {
	        }
	       
	    }
	    
	    public void UpdateTable(){
	    	getDatausers();
	    	 
	    	col_id_art.setCellValueFactory(new PropertyValueFactory<>("id"));
	        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
	       col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	       col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
	       col_id_com.setCellValueFactory(new PropertyValueFactory<Commerce,String>("id_com"));
	        
	       
	        
	       
	   }
	    

	    @FXML
	    private void getAddView(MouseEvent event) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("/View/AddArticle.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.show();
	        } catch (IOException ex) {
	            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	                
	    }
	    @FXML
	    private void getUpdateView(MouseEvent event) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateArticle.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.show();
	       
	         
	        } catch (IOException ex) {
	            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	                
	    }
	    @FXML
	    private void getDeleteView(MouseEvent event) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("/View/DeleteArticle.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.show();
	       
	         
	        } catch (IOException ex) {
	            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	                
	    }

	   
	 @FXML
	    void search_user() {          
	        col_id_art.setCellValueFactory(new PropertyValueFactory<>("id"));
	        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
	        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
	        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
	        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
	        col_id_com.setCellValueFactory(new PropertyValueFactory<Commerce,String>("id_com"));
	        
	        dataList = mysqlconnect.getDatausers();
	        table_article.setItems(dataList);
	        FilteredList<Article> filteredData = new FilteredList<>(dataList, b -> true);  
	       filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	 filteredData.setPredicate(person -> {
	    if (newValue == null || newValue.isEmpty()) {
	     return true;
	    }    
	    String lowerCaseFilter = newValue.toLowerCase();
	    
	    if (person.getId().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	     return true; // Filter matches id
	    } else if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches nom
	    }else if (person.getQuantite().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	     return true; // Filter matches quantite
	    }
	    else if (person.getCategorie().indexOf(lowerCaseFilter)!=-1) {
	         return true;// Filter matches categorie
	    }else if (person.getPrix().indexOf(lowerCaseFilter)!=-1) {
	         return true;// Filter matches  prix
	    }else if (person.getId_com().indexOf(lowerCaseFilter)!=-1)
	         return true;// Filter matches id_commerce
	         else  
	          return false; // Does not match.
	   });
	  });  
	  SortedList<Article> sortedData = new SortedList<>(filteredData);  
	  sortedData.comparatorProperty().bind(table_article.comparatorProperty());  
	  table_article.setItems(sortedData);      
	    }
	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    UpdateTable();
	    search_user();
	    	 
	  //  search_user();
	    // Code Source in description
	    } 
	}


