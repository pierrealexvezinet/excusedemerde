package fr.edm.model;

import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListEdmsUser {

	private ArrayList<EdmUser> listEdmsUser;

	public ListEdmsUser() {
	}

	public ListEdmsUser(ArrayList<EdmUser> listEdmsUser) {
		super();
		this.listEdmsUser = listEdmsUser;
	}

	@JsonProperty(value = "listEdmsUser")
	public ArrayList<EdmUser> getListEdmsUser() {
		return listEdmsUser;
	}

	public void setListEdmsUser(ArrayList<EdmUser> listEdmsUser) {
		this.listEdmsUser = listEdmsUser;
	}

}
