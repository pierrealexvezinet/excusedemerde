package fr.edm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInTopTen {
	
	private String urlPhotoUserInTopTen;
	private String pseudoUserInTopTen;
	private String nbVoteByUserInTopTen;
	
	
	public UserInTopTen() {
		super();
	}


	public UserInTopTen(String urlPhotoUserInTopTen,
			String pseudoUserInTopTen, String nbVoteByUserInTopTen) {
		super();
		this.urlPhotoUserInTopTen = urlPhotoUserInTopTen;
		this.pseudoUserInTopTen = pseudoUserInTopTen;
		this.nbVoteByUserInTopTen = nbVoteByUserInTopTen;
	}

	@JsonProperty(value = "photo")
	public String getUrlPhotoUserInTopTen() {
		return urlPhotoUserInTopTen;
	}


	public void setUrlPhotoUserInTopTen(String urlPhotoUserInTopTen) {
		this.urlPhotoUserInTopTen = urlPhotoUserInTopTen;
	}

	@JsonProperty(value = "auteurEdm")
	public String getPseudoUserInTopTen() {
		return pseudoUserInTopTen;
	}


	public void setPseudoUserInTopTen(String pseudoUserInTopTen) {
		this.pseudoUserInTopTen = pseudoUserInTopTen;
	}

	@JsonProperty(value = "nbVoteByUserInTopTen")
	public String getNbVoteByUserInTopTen() {
		return nbVoteByUserInTopTen;
	}


	public void setNbVoteByUserInTopTen(String nbVoteByUserInTopTen) {
		this.nbVoteByUserInTopTen = nbVoteByUserInTopTen;
	}
	
	
	
	
	
	
	

}
