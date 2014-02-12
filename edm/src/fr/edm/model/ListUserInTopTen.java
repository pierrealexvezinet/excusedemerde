package fr.edm.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListUserInTopTen {
	
	private ArrayList<UserInTopTen> listUserInTopTen;

	public ListUserInTopTen() {
	}

	public ListUserInTopTen(ArrayList<UserInTopTen> listUserInTopTen) {
		super();
		this.listUserInTopTen = listUserInTopTen;
	}

	@JsonProperty(value = "listTopTen")
	public ArrayList<UserInTopTen> getListTopTen() {
		return listUserInTopTen;
	}

	public void setListTopTen(ArrayList<UserInTopTen> listUserInTopTen) {
		this.listUserInTopTen = listUserInTopTen;
	}


}
