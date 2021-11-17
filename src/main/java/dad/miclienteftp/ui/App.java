package dad.miclienteftp.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class App extends Application {
	private PrincipalController controller;
	private static Stage primaryStage;
	private ConectarController conectcontroller;
	private static Stage secondStage;

	public void start(Stage primaryStage) throws Exception {

		App.primaryStage = primaryStage;
		secondStage=new Stage();
		controller = new PrincipalController();
		Scene scene = new Scene(controller.getView());
		conectcontroller = new ConectarController();
		Scene scene2 = new Scene(conectcontroller.getView());
		primaryStage.setTitle("Mi cliente FTP");
		secondStage.setTitle("Nueva conexion");
		primaryStage.setScene(scene);
		secondStage.setScene(scene2);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
	public static Stage getSecondStage() {
		return secondStage;
	}


	public static boolean confirm(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		return alert.showAndWait().get().equals(ButtonType.OK);
	}


	public static void info(String titulo, String cabezero, String Contenido) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(primaryStage);
		alert.setTitle(titulo);
		alert.setHeaderText(cabezero);
		alert.setContentText(Contenido);
		alert.showAndWait();
	}

}
