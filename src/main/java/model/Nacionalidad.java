package model;

import java.util.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import utils.StringUtils;

public class Nacionalidad {

	private StringProperty denominacion = new SimpleStringProperty();

	public Nacionalidad(String denominacion) {
		this.denominacion.set(StringUtils.capitalize(denominacion));
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
		return Character.toUpperCase(denominacion.get().charAt(0)) + denominacion.get().substring(1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nacionalidad other = (Nacionalidad) obj;
		return Objects.equals(denominacion.get(), other.denominacion.get());
	}
	
	
	
}
