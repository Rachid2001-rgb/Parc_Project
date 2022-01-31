package application;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DBParc {

	public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
		Parent root = null;
		if (username != null) {
			
				FXMLLoader loader = new FXMLLoader(DBParc.class.getResource(fxmlFile));
				
				

				try {
					root = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		} else {
			
				try {
					root = FXMLLoader.load(DBParc.class.getResource(fxmlFile));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		}
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();

	}

	public static void signUpUser(ActionEvent event, String username, String password, String level_user) {
		Connection connection = null;
		PreparedStatement psInsert = null;
		PreparedStatement psCheckUserExists = null;
		ResultSet resultSet = null;
		

		try {
			connection=ConnexionMysql.connexionDB();

			psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username=?");
			psCheckUserExists.setString(1, username);
			resultSet = psCheckUserExists.executeQuery();
			if (resultSet.isBeforeFirst()) {
				System.out.println("UTLISATEUR DEJA EXISTE");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Vous pouvez pas utiliser ce nom d'utilisateur");
				alert.show();
			} else {
				psInsert = connection
						.prepareStatement("INSERT INTO users (username,password,level_user) VALUES (?,?,?);");
				psInsert.setString(1, username);
				psInsert.setString(2, password);
				psInsert.setString(3, level_user);
				psInsert.executeUpdate();
				changeScene(event, "/View/LoginView.fxml", "commande!", username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (psCheckUserExists != null) {
				try {
					psCheckUserExists.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (psInsert != null) {
				try {
					psInsert.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void logInUser(ActionEvent event, String username, String password, String tab) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parc1", "root", "");
			preparedStatement = connection.prepareStatement("SELECT password ,level_user FROM users WHERE username=?");
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.isBeforeFirst()) {
				System.out.println("Utilisateur introuvable dans la base de données !");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Les informations d'identification fournies sont incorrectes!");
				alert.show();
			} else {
				while (resultSet.next()) {
					String retrievedPassword = resultSet.getString("password");
					String retrievedLevelUser = resultSet.getString("level_user");
					if (retrievedPassword.equals(password) && retrievedLevelUser.equals("Utilisateur")) {
						changeScene(event, "/View/Sample.fxml", "commande", username);

					} else if (retrievedPassword.equals(password) && retrievedLevelUser.equals("Administrateur")) {

						changeScene(event, "/View/Sample4.fxml", "gestion", username);

					} else {
						System.out.println("Passwords did not match!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Les informations d'identification fournies sont incorrectes!");
						alert.show();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
