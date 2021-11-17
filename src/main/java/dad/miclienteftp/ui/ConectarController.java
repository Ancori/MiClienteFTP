package dad.miclienteftp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.net.ftp.FTPClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class ConectarController implements Initializable {
	StringProperty server = new SimpleStringProperty();
	StringProperty port = new SimpleStringProperty();
	StringProperty user = new SimpleStringProperty();
	StringProperty contraseña = new SimpleStringProperty();
	@FXML
	private GridPane view;

	@FXML
	private Button conectarbutton;

	@FXML
	private Button cancelarbutton;

	@FXML
	private TextField servidortext;

	@FXML
	private TextField puertotext;

	@FXML
	private TextField usuariotext;

	@FXML
	private PasswordField contraseñatext;

	public ConectarController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Conectarview.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		server.bind(servidortext.textProperty());
		port.bind(puertotext.textProperty());
		user.bind(usuariotext.textProperty());
		contraseña.bind(contraseñatext.textProperty());
	}

	@FXML
	void oncancelarbutton(ActionEvent event) {
		App.getSecondStage().close();
	}

	@FXML
	void onconectarbutton(ActionEvent event) {
		Conexion.cliente.set(new FTPClient());
		int puerto = Integer.valueOf(port.get());
		try {
			Conexion.cliente.get().connect(server.get(), puerto);
			Conexion.cliente.get().login(user.get(), contraseña.get());
			Conexion.cliente.get().changeWorkingDirectory("/");
			App.info("Conexion", "Conexion establecida con exito", null);
			App.getSecondStage().close();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se puedo conectar con el servidor "+server.get());
			alert.showAndWait();
		}
		
	}

	public GridPane getView() {
		return view;
	}

}
