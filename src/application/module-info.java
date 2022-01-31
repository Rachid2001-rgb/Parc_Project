module parc4 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;

	opens Controller to javafx.graphics, javafx.fxml;


}
