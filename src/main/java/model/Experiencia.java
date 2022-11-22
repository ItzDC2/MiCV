package model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Experiencia {

	private ObjectProperty<LocalDate> desde = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> hasta = new SimpleObjectProperty<>();
	private StringProperty denominacion = new SimpleStringProperty();
	private StringProperty empleador = new SimpleStringProperty();
	
	public ObjectProperty<LocalDate> desdeProperty() {
		return this.desde;
	}
	
	public LocalDate getDesde() {
		return this.desdeProperty().get();
	}
	
	public void setDesde(final LocalDate desde) {
		this.desdeProperty().set(desde);
	}
	
	public ObjectProperty<LocalDate> hastaProperty() {
		return this.hasta;
	}
	
	public LocalDate getHasta() {
		return this.hastaProperty().get();
	}
	
	public void setHasta(final LocalDate hasta) {
		this.hastaProperty().set(hasta);
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
	
	public StringProperty empleadorProperty() {
		return this.empleador;
	}
	
	public String getEmpleador() {
		return this.empleadorProperty().get();
	}
	
	public void setEmpleador(final String empleador) {
		this.empleadorProperty().set(empleador);
	}
	
}
