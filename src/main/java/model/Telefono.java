package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefono {

	private StringProperty numero = new SimpleStringProperty();
	private ObjectProperty<TipoTelefono> tipo = new SimpleObjectProperty<>();
	 
	public StringProperty numeroProperty() {
		return this.numero;
	}
	
	public String getNumero() {
		return this.numeroProperty().get();
	}

	public void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}

	public ObjectProperty<TipoTelefono> tipoProperty() {
		return this.tipo;
	}
	
	public TipoTelefono getTipo() {
		return this.tipoProperty().get();
	}
	
	public void setTipo(final TipoTelefono tipo) {
		this.tipoProperty().set(tipo);
	}
	
	
}
