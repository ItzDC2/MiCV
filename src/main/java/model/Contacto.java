package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {
	
	private ListProperty<Telefono> telefonos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Email> emails = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Web> webs = new SimpleListProperty<>(FXCollections.observableArrayList());

	public ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}
	
	public ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}
	
	public void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}
	
	public ListProperty<Email> emailsProperty() {
		return this.emails;
	}
	
	public ObservableList<Email> getEmails() {
		return this.emailsProperty().get();
	}

	public void setEmails(final ObservableList<Email> emails) {
		this.emailsProperty().set(emails);
	}
	
	public ListProperty<Web> websProperty() {
		return this.webs;
	}
	
	public ObservableList<Web> getWebs() {
		return this.websProperty().get();
	}
	
	public void setWebs(final ObservableList<Web> webs) {
		this.websProperty().set(webs);
	}
	
}
