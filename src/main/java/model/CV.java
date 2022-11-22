package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CV {

	private ObjectProperty<Personal> personal = new SimpleObjectProperty<>(new Personal());
	private ObjectProperty<Contacto> contacto = new SimpleObjectProperty<>(new Contacto());
	private ListProperty<Conocimiento> habilidades = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Titulo> formacion = new SimpleListProperty<>(FXCollections.observableArrayList());
	
	public ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}
	
	public Personal getPersonal() {
		return this.personalProperty().get();
	}
	
	public void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}
	
	public ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}
	
	public Contacto getContacto() {
		return this.contactoProperty().get();
	}
	
	public void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}
	
	public ListProperty<Conocimiento> habilidadesProperty() {
		return this.habilidades;
	}
	
	public ObservableList<Conocimiento> getHabilidades() {
		return this.habilidadesProperty().get();
	}
	
	public void setHabilidades(final ObservableList<Conocimiento> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}
	
	public ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}
	
	public ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}
	
	public void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}
	
	public ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	
	public ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}
	
	public void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

}