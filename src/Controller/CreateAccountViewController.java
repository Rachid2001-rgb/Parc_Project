package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DBParc;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class CreateAccountViewController implements Initializable{



    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_username;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button button_signup;

    @FXML
    private Label lblLogin;
    
    @FXML
    private RadioButton rb_user;

    @FXML
    private RadioButton rb_admin;

    @Override
    public void initialize(URL location,ResourceBundle resources) {
        ToggleGroup toggleGroup =new ToggleGroup();
        rb_user.setToggleGroup(toggleGroup);
        rb_admin.setToggleGroup(toggleGroup);
        rb_admin.setSelected(true);
    	button_signup.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String toggleName =((RadioButton) toggleGroup.getSelectedToggle()).getText();
				if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().isEmpty()) {
					DBParc.signUpUser(event,tf_username.getText(), tf_password.getText(),toggleName);
				}else {
					System.out.println("Veuillez remplir toutes les informations.");
					Alert alert =new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Veuillez remplir toutes les informations pour vous inscrire!");
					alert.show();
				}
				
			}
    		
    		
    	});
    	
    }
    
    
    @FXML
    private void openLoginScene(MouseEvent event) throws IOException {
    	Parent root=FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
    	Scene accountScene=lblLogin.getScene();
    	root.translateYProperty().set(accountScene.getHeight());
    	
    	StackPane rootPane=(StackPane) accountScene.getRoot();
    	
    	
    	
    	
    	rootPane.getChildren().add(root);
    	Timeline timeline=new Timeline();
    	KeyValue keyValue=new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
    	KeyFrame keyFrame=new KeyFrame(Duration.seconds(2),keyValue);
    	timeline.getKeyFrames().add(keyFrame);
    	
    	timeline.play();
    	timeline.setOnFinished((ActionEvent event2)->{
    		
    		rootPane.getChildren().remove(anchorpane);
    	});
    }
}
