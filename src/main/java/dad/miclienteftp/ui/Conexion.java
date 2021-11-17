package dad.miclienteftp.ui;

import org.apache.commons.net.ftp.FTPClient;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Conexion {
    static ObjectProperty<FTPClient> cliente = new SimpleObjectProperty<>();

	public ObjectProperty<FTPClient> clienteProperty() {
		return Conexion.cliente;
	}
	

	public FTPClient getCliente() {
		return this.clienteProperty().get();
	}
	

	public void setCliente(final FTPClient cliente) {
		this.clienteProperty().set(cliente);
	}
	
	

}
