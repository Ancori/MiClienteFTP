package file;

import org.apache.commons.net.ftp.FTPFile;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FTFile {
	//private ListProperty<FTPFile> ficheros = new SimpleListProperty<FTPFile>(FXCollections.observableArrayList());
	private StringProperty nombre=new SimpleStringProperty();
	private LongProperty tamano=new SimpleLongProperty();
	private StringProperty tipo=new SimpleStringProperty();
	
	public FTFile(FTPFile f) {
		this.setNombre(f.getName());
		this.setTamano(f.getSize());
		String tipo = "";
		switch (f.getType()) {
		case FTPFile.DIRECTORY_TYPE: tipo = "Directorio"; break;
		case FTPFile.FILE_TYPE: tipo = "Fichero"; break;
		case FTPFile.SYMBOLIC_LINK_TYPE: tipo = "Enlace"; break;
		default: tipo = "Desconocido"; break;
		}
		this.setTipo(tipo);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}
	

	public String getNombre() {
		return this.nombreProperty().get();
	}
	

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public StringProperty tipoProperty() {
		return this.tipo;
	}
	

	public String getTipo() {
		return this.tipoProperty().get();
	}
	

	public void setTipo(final String tipo) {
		this.tipoProperty().set(tipo);
	}

	public LongProperty tamanoProperty() {
		return this.tamano;
	}
	

	public long getTamano() {
		return this.tamanoProperty().get();
	}
	

	public void setTamano(final long tamano) {
		this.tamanoProperty().set(tamano);
	}
	
	 

}
