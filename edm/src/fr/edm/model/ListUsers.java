package fr.edm.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListUsers {
	
	private ArrayList<User> listUsers;

	public ListUsers() {
	}

	public ListUsers(ArrayList<User> listUsers) {
		super();
		this.listUsers = listUsers;
	}

	@JsonProperty(value = "listUsers")
	public ArrayList<User> getListUsers() {
		return listUsers;
	}

	public void setListUsers(ArrayList<User> listUsers) {
		this.listUsers = listUsers;
	}


}
