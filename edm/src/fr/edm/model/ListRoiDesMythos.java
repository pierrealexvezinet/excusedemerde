package fr.edm.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListRoiDesMythos {
	
	private ArrayList<RoiDesMythos> listRoiDesMythos;
	
	public ListRoiDesMythos(){
		super();
	}

	public ListRoiDesMythos(ArrayList<RoiDesMythos> listRoiDesMythos) {
		super();
		this.listRoiDesMythos = listRoiDesMythos;
	}
	@JsonProperty(value = "roiDesMythosUser")
	public ArrayList<RoiDesMythos> getListRoiDesMythos() {
		return listRoiDesMythos;
	}

	public void setListRoiDesMythos(ArrayList<RoiDesMythos> listRoiDesMythos) {
		this.listRoiDesMythos = listRoiDesMythos;
	}
	
	

}
