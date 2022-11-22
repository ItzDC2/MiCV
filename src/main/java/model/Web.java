package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Web {

	private StringProperty url = new SimpleStringProperty();

	public StringProperty urlProperty() {
		return this.url;
	}

	public String getUrl() {
		return this.urlProperty().get();
	}

	public void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
}
