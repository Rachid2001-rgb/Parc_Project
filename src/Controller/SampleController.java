package Controller;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.scene.layout.BorderPane;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;



import application.ConnexionMysql;
import application.DBParc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.stage.Stage;

import models.Attraction;
import models.Repas;



public class SampleController implements Initializable{
	   Connection cnx=null;
	    PreparedStatement st=null;
	    ResultSet result=null;
	   Attraction attraction=null;
	   Repas repas=null;
	   String query = null;
		AttractionViewController attractionViewController=new AttractionViewController();
		RepasViewController repasViewController=new RepasViewController();


	    public ObservableList<Attraction> AttractionList =FXCollections.observableArrayList();
	    public ObservableList<Repas> RepasList =FXCollections.observableArrayList();   


	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;
	@FXML
	private Button button_logout;
	@FXML
	private Button btn_add;
	private Button btn_addR;
	@FXML
    private AnchorPane add_1;
	  @FXML
	private TextField txt_search;
	 @FXML
	private TableView<Attraction> attractionTable;
	private TableView<Repas> repasTable;
	@FXML
    private TableColumn<Attraction,String> duree_col;
    private TableColumn<Repas,String> qteR_col;

    @FXML
    private TableColumn<Attraction, String> edit_col;

    @FXML
    private TableColumn<Attraction, String> id_col;
    private TableColumn<Repas, String> idR_col;

    @FXML
    private TableColumn<Attraction, String> nom_col;
    private TableColumn<Repas, String> nomR_col;


    @FXML
    private TableColumn<Attraction, String> prix_col;
    private TableColumn<Repas, String> prixR_col;

    @FXML
    private Button btn_delete;
    private Button btn_deleteR;


    @FXML
    private Button btn_edit;
    private Button btn_editR;

        
    @FXML
    void close(MouseEvent event) {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
	@FXML
	void openAdd(ActionEvent event) {
		Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/AttractionView.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	}
	
	@FXML
	void openAddR(ActionEvent event) {
		Parent root = null;
		Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();

	try {

		root =  FXMLLoader.load(getClass().getResource("/View/RepasView.fxml"));
		
		stage.setScene(new Scene(root));
		stage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	
	}
	
    public ObservableList<Attraction> ProductSearchModelObservableList =FXCollections.observableArrayList(); 
    /*public ObservableList<Repas> ProductSearchModelObservableList =FXCollections.observableArrayList();*/   


	public void initialize(URL url, ResourceBundle rb) {
		button_logout.setOnAction(new EventHandler<ActionEvent>(){
	    		@Override 
	    		public void handle(ActionEvent event) {
	    			DBParc.changeScene(event,"/View/LoginView.fxml", "Connecter!",null);
	    			
	    		}
	    	});
	
		loadDate();

        attractionTable.setItems(AttractionList);
		FilteredList<Attraction> filterdData=new FilteredList<>(AttractionList,b->true);
		 txt_search.textProperty().addListener((observable,oldValue,newValue) -> {
			 filterdData.setPredicate( attraction -> {
			 if (newValue.isEmpty()|| newValue.isBlank()|| newValue==null) {
				 return true;
			 }
			String searchKeyWord= newValue.toLowerCase();
			if (String.valueOf(attraction.getId()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;
			}
			else if(String.valueOf(attraction.getTxt_nom()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;}
			else if(String.valueOf(attraction.getTxt_prix()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;}
			else if(String.valueOf(attraction.getTxt_duree()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;
			}else 
				return false;
		        
			
			 });
			 

		 });
		SortedList<Attraction> sortedData=new SortedList<>(filterdData);
		sortedData.comparatorProperty().bind(attractionTable.comparatorProperty());
		attractionTable.setItems(sortedData);
		 
	}
	
	
	public void initializeR(URL url, ResourceBundle rb) {
		button_logout.setOnAction(new EventHandler<ActionEvent>(){
	    		@Override 
	    		public void handle(ActionEvent event) {
	    			DBParc.changeScene(event,"/View/LoginView.fxml", "Connecter!",null);
	    			
	    		}
	    	});
	
		loadDate();

        repasTable.setItems(RepasList);
		FilteredList<Repas> filterdData=new FilteredList<>(RepasList,b->true);
		 txt_search.textProperty().addListener((observable,oldValue,newValue) -> {
			 filterdData.setPredicate( repas -> {
			 if (newValue.isEmpty()|| newValue.isBlank()|| newValue==null) {
				 return true;
			 }
			String searchKeyWord= newValue.toLowerCase();
			if (String.valueOf(repas.getIdR()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;
			}
			else if(String.valueOf(repas.getTxt_nomR()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;}
			else if(String.valueOf(repas.getTxt_prixR()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;}
			else if(String.valueOf(repas.getTxt_qteR()).toLowerCase().indexOf(searchKeyWord)>-1) {
				return true;
			}else 
				return false;
		        
			
			 });
			 

		 });
		SortedList<Repas> sortedData=new SortedList<>(filterdData);
		sortedData.comparatorProperty().bind(repasTable.comparatorProperty());
		repasTable.setItems(sortedData);
		 
	}
	
	   @FXML
	    void edit(ActionEvent event) {
		   
		   attraction = attractionTable.getSelectionModel().getSelectedItem();

			 FXMLLoader loader = new FXMLLoader ();
           loader.setLocation(getClass().getResource("/View/AttractionView.fxml"));
           try {
               loader.load();
           } catch (IOException ex) {
               Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
           }
   		AttractionViewController attractionViewController=loader.getController();
   		attractionViewController.setUpdate(true);
   		attractionViewController.setTextField(attraction.getId(), attraction.getTxt_nom(), 
                  attraction.getTxt_prix(),attraction.getTxt_duree());
   		Parent parent = loader.getRoot();
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(new Scene(parent));


           stage.show();

	    }
	   
	   @FXML
	    void editR(ActionEvent event) {
		   
		   repas = repasTable.getSelectionModel().getSelectedItem();

			 FXMLLoader loader = new FXMLLoader ();
          loader.setLocation(getClass().getResource("/View/RepasView.fxml"));
          try {
              loader.load();
          } catch (IOException ex) {
              Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
          }
  		RepasViewController repasViewController=loader.getController();
  		repasViewController.setUpdate(true);
  		repasViewController.setTextField(repas.getIdR(), repas.getTxt_nomR(), 
                 repas.getTxt_prixR(),repas.getTxt_qteR());
  		Parent parent = loader.getRoot();
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
          stage.setScene(new Scene(parent));


          stage.show();

	    }
	
	   @FXML
	    void delete(ActionEvent event) {
		   try {
               attraction = attractionTable.getSelectionModel().getSelectedItem();
               query = "DELETE FROM `attraction` WHERE id  ="+attraction.getId();
       		cnx=ConnexionMysql.connexionDB();
               st = cnx.prepareStatement(query);
               st.execute();
               refresh();
               
           } catch (SQLException ex) {
               Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
		   
	   }
	   }
	   
	   @FXML
	    void deleteR(ActionEvent event) {
		   try {
              repas = repasTable.getSelectionModel().getSelectedItem();
              query = "DELETE FROM `repas` WHERE id  ="+repas.getIdR();
      		cnx=ConnexionMysql.connexionDB();
              st = cnx.prepareStatement(query);
              st.execute();
              refresh();
              
          } catch (SQLException ex) {
              Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
		   
	   }
	   }
	   
	   @FXML
		private void Sample(MouseEvent event) {
			bp.setCenter(ap);
		}
	@FXML
	private void Sample4(MouseEvent event) {
		bp.setCenter(ap);
	}

	
	@FXML
	private void Sample5(MouseEvent event) {
		loadPage("/View/Sample5");
	}
	
	@FXML
	private void Sample6(MouseEvent event) {
		loadPage("/View/Sample6");
	}
	
	@FXML
	private void Sample7(MouseEvent event) {
		loadPage("/View/Sample7");
	}
	
	@FXML
	private void Sample8(MouseEvent event) {
		loadPage("/View/Sample8");
	}
	 @FXML
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
	 
	 @FXML
	  private  void refreshR() {
		 
		 try {
			 RepasList.clear();
			  query="select * from repas";
			 st=cnx.prepareStatement(query);
			 result=st.executeQuery();
			 while(result.next()) {
				 RepasList.add(new Repas (result.getInt("idR"),result.getString("txt_nomR"),result.getInt("txt_qteR"),result.getFloat("txt_prixR")));
				 repasTable.setItems(RepasList);
				}
			
			 }
			 
			 
		 catch (SQLException e) {
	            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, e);
		}
		
		 

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
	
	private void loadDate(){
		cnx=ConnexionMysql.connexionDB();
		refresh();
		id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom_col.setCellValueFactory(new PropertyValueFactory<>("txt_nom"));
		prix_col.setCellValueFactory(new PropertyValueFactory<>("txt_prix"));
		duree_col.setCellValueFactory(new PropertyValueFactory<>("txt_duree"));
				
	}
	
	private void loadDateR(){
		cnx=ConnexionMysql.connexionDB();
		refresh();
		idR_col.setCellValueFactory(new PropertyValueFactory<>("idR"));
		nomR_col.setCellValueFactory(new PropertyValueFactory<>("txt_nomR"));
		prixR_col.setCellValueFactory(new PropertyValueFactory<>("txt_prixR"));
		qteR_col.setCellValueFactory(new PropertyValueFactory<>("txt_qteR"));
				
	}
	
	 
	
	
	
}
