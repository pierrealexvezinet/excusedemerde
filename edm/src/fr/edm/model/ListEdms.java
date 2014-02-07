package fr.edm.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListEdms {

	private ArrayList<Edm> listEdms;

	public ListEdms() {
	}

	public ListEdms(ArrayList<Edm> listEdms) {
		super();
		this.listEdms = listEdms;
	}

	@JsonProperty(value = "listEdms")
	public ArrayList<Edm> getListEdms() {
		return listEdms;
	}

	public void setListEdm(ArrayList<Edm> listEdms) {
		this.listEdms = listEdms;
	}

}
