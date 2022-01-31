package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DBParc;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginViewController implements Initializable {

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblUser;

    @FXML
    private Pane slidingPane;

    @FXML
    private Tab tabAdmin;

    @FXML
    private TabPane tabPaneLogin;

    @FXML
    private Tab tabUser;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private Button button_login;
    @FXML
    private Button button_login1;
    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password1;

    @FXML
    private TextField tf_username1;

    @FXML
    private void openAdminTab(MouseEvent event) {
    	TranslateTransition toLeftAnimation = new TranslateTransition(new Duration (500), lblStatus);
    	toLeftAnimation.setToX(slidingPane.getTranslateX());
    	toLeftAnimation.play();
    	toLeftAnimation.setOnFinished(new EventHandler<ActionEvent>()
    	{
    		@Override
    		public void handle(ActionEvent event) {
    			lblStatus.setText("ADMINISTRATEUR");
    		}
    	});
    	
    	tabPaneLogin.getSelectionModel().select(tabAdmin);
    	
    }

    @FXML
    private void openUserTab(MouseEvent event) {
    	
    	TranslateTransition toRightAnimation = new TranslateTransition(new Duration (500), lblStatus);
    	toRightAnimation.setToX(slidingPane.getTranslateX()+(slidingPane.getPrefWidth()-lblStatus.getPrefWidth()));
    	toRightAnimation.play();
    	toRightAnimation.setOnFinished(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			lblStatus.setText("UTILISATEUR");
    		}
    	});
    	
    	tabPaneLogin.getSelectionModel().select(tabUser);  	 	
    }
    @FXML
    private void openCreateAccountScene(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/View/CreateAccountView.fxml"));
    	Scene loginScene=lblAdmin.getScene();
    	root.translateYProperty().set(loginScene.getHeight());
    	rootPane.getChildren().add(root);
    	Timeline timeline=new Timeline();
    	KeyValue keyValue=new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
    	KeyFrame keyFrame=new KeyFrame(Duration.seconds(2),keyValue);
    	timeline.getKeyFrames().add(keyFrame);
    	
    	timeline.play();
    	timeline.setOnFinished((ActionEvent event2)->{
    		
    		rootPane.getChildren().remove(anchorPane);
    	});
    }
    
    
     public void initialize(URL location, ResourceBundle resources) {
    	 
    	 button_login.setOnAction(new EventHandler<ActionEvent>(){
    		@Override 
    		public void handle(ActionEvent event) {
    			DBParc.logInUser(event,tf_username.getText(), tf_password.getText(),"Administrateur");
    			
    		}
    	});
     
   
    	 
    	 button_login1.setOnAction(new EventHandler<ActionEvent>(){
     		@Override 
     		public void handle(ActionEvent event) {
     			DBParc.logInUser(event,tf_username1.getText(), tf_password1.getText(),"Utilisateur");
     			
     		}
     	});
    	
    }
    
}
