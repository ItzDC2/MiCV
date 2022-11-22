package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Nacionalidad {

	private StringProperty denominacion = new SimpleStringProperty();

	public Nacionalidad(StringProperty denominacion) {
		this.denominacion = denominacion;
	}
	
	public StringProperty denominacionProperty() {
		return this.denominacion;
	}
	
	public String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}
	
	@Override
	public String toString() {
		char c = denominacion.get().charAt(0);
		String str = denominacion.get().substring(1, denominacion.get().length());
		return Character.toUpperCase(c) + str;
	}
	
}
