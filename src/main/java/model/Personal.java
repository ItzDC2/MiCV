package model;

import java.time.LocalDate;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personal {

	private StringProperty identificacion = new SimpleStringProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty apellido = new SimpleStringProperty();
	private ObjectProperty<LocalDate> fechaNacimiento = new SimpleObjectProperty<>();
	private StringProperty direccion = new SimpleStringProperty();
	private StringProperty codigoPostal = new SimpleStringProperty();
	private StringProperty localidad = new SimpleStringProperty();

	private StringProperty pais = new SimpleStringProperty();
	private ListProperty<Nacionalidad> nacionalidades = new SimpleListProperty<>(FXCollections.observableArrayList());

	public StringProperty identificacionProperty() {
		return this.identificacion;
	}

	public String getIdentificacion() {
		return this.identificacionProperty().get();
	}

	public void setIdentificacion(final String identificacion) {
		this.identificacionProperty().set(identificacion);
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

	public StringProperty apellidoProperty() {
		return this.apellido;
	}

	public String getApellido() {
		return this.apellidoProperty().get();
	}

	public void setApellido(final String apellido) {
		this.apellidoProperty().set(apellido);
	}

	public ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	public LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public StringProperty direccionProperty() {
		return this.direccion;
	}

	public String getDireccion() {
		return this.direccionProperty().get();
	}

	public void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

	public StringProperty codigoPostalProperty() {
		return this.codigoPostal;
	}

	public String getCodigoPostal() {
		return this.codigoPostalProperty().get();
	}

	public void setCodigoPostal(final String codigoPostal) {
		this.codigoPostalProperty().set(codigoPostal);
	}

	public StringProperty localidadProperty() {
		return this.localidad;
	}

	public String getLocalidad() {
		return this.localidadProperty().get();
	}

	public void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}

	public ListProperty<Nacionalidad> nacionalidadesProperty() {
		return this.nacionalidades;
	}

	public ObservableList<Nacionalidad> getNacionalidades() {
		return this.nacionalidadesProperty().get();
	}

	public void setNacionalidades(final ObservableList<Nacionalidad> nacionalidades) {
		this.nacionalidadesProperty().set(nacionalidades);
	}

	public StringProperty paisProperty() {
		return this.pais;
	}

	public String getPais() {
		return this.paisProperty().get();
	}

	public void setPais(final String pais) {
		this.paisProperty().set(pais);
	}

}
