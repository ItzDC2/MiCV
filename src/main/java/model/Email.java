package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {

	private StringProperty direccion = new SimpleStringProperty();

	public StringProperty direccionProperty() {
		return this.direccion;
	}

	public String getDireccion() {
		return this.direccionProperty().get();
	}
	
	public void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}
		
}
