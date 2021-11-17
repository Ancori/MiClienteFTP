package dad.miclienteftp.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPFile;
import java.io.File;
import java.io.FileOutputStream;
import file.FTFile;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PrincipalController implements Initializable {

	ConectarController controllercon = new ConectarController();
	private ListProperty<FTFile> ficheros = new SimpleListProperty<FTFile>(FXCollections.observableArrayList());
	private ObjectProperty<FTFile> seleccionado = new SimpleObjectProperty<>();
	private String ruta = "/";

	@FXML
	private BorderPane view;

	@FXML
	private MenuBar menu;

	@FXML
	private Menu servidormenu;

	@FXML
	private MenuItem conectaritem;

	@FXML
	private MenuItem desconectaritem;

	@FXML
	private TableView<FTFile> table;

	@FXML
	private TableColumn<FTFile, String> nombrecolumn;

	@FXML
	private TableColumn<FTFile, Number> tamañocolumn;

	@FXML
	private TableColumn<FTFile, String> tipocolumn;

	@FXML
	private Button descargarbutton;

	public PrincipalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.itemsProperty().bind(ficheros);
		desconectaritem.disableProperty().bind(Conexion.cliente.isNull());
		nombrecolumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		tamañocolumn.setCellValueFactory(v -> v.getValue().tamanoProperty());
		tipocolumn.setCellValueFactory(v -> v.getValue().tipoProperty());
		seleccionado.bind(table.getSelectionModel().selectedItemProperty());
	}

	@FXML
	void onconectaritem(ActionEvent event) throws IOException {
		App.getSecondStage().showAndWait();
		for (FTPFile fichero : Conexion.cliente.get().listFiles()) {
			ficheros.add(new FTFile(fichero));
		}

	}

	@FXML
	void ondescargarbutton(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Guardar");
		filechooser.getExtensionFilters().add(new ExtensionFilter("Archivo txt", "*.txt"));
		filechooser.getExtensionFilters().add(new ExtensionFilter("Archivo msg", "*.msg"));
		filechooser.getExtensionFilters().add(new ExtensionFilter("Todos los ficheros", "*.*"));
		filechooser.setInitialDirectory(new File("."));
		File fichero = filechooser.showSaveDialog(App.getPrimaryStage());
		if (fichero != null) {
			try {
				FileOutputStream flujo = new FileOutputStream(fichero);
				Conexion.cliente.get().retrieveFile(fichero.getName(), flujo);
				flujo.flush();
				flujo.close();
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("No se puede guardar el archivo");
				alert.showAndWait();
			}

		}
	}

	@FXML
	void ondesconectaritem(ActionEvent event) {
		try {
			Conexion.cliente.get().disconnect();
			Conexion.cliente.set(null);
			ficheros.clear();
			App.info("Conexion", "Conexion desconectada con exito", null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void onclicked(MouseEvent event) throws IOException {
		if (seleccionado.get().getTipo() == "Directorio" || seleccionado.get().getTipo() == "Enlace" ) {
			ruta = ruta + "/" + seleccionado.get().getNombre();
			Conexion.cliente.get().changeWorkingDirectory(ruta);
			ficheros.clear();
			for (FTPFile fichero : Conexion.cliente.get().listFiles()) {
				ficheros.add(new FTFile(fichero));
			}
		}
	}

	@FXML
	void onservidormenu(ActionEvent event) {
	}

	public BorderPane getView() {
		return view;
	}

}
