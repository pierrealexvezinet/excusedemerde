package fr.edm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoiDesMythos {
	
	public String urlPhotoRoiDesMythos, pseudoRoiDesMythos, nbVoteRoiDesMythos;

	public RoiDesMythos() {
		super();
	}
	
	

	public RoiDesMythos(String urlPhotoRoiDesMythos, String pseudoRoiDesMythos,
			String nbVoteRoiDesMythos) {
		super();
		this.urlPhotoRoiDesMythos = urlPhotoRoiDesMythos;
		this.pseudoRoiDesMythos = pseudoRoiDesMythos;
		this.nbVoteRoiDesMythos = nbVoteRoiDesMythos;
	}


	@JsonProperty(value = "photo")
	public String getUrlPhotoRoiDesMythos() {
		return urlPhotoRoiDesMythos;
	}

	public void setUrlPhotoRoiDesMythos(String urlPhotoRoiDesMythos) {
		this.urlPhotoRoiDesMythos = urlPhotoRoiDesMythos;
	}
	@JsonProperty(value = "auteurEdm")
	public String getPseudoRoiDesMythos() {
		return pseudoRoiDesMythos;
	}

	public void setPseudoRoiDesMythos(String pseudoRoiDesMythos) {
		this.pseudoRoiDesMythos = pseudoRoiDesMythos;
	}
	@JsonProperty(value = "nb_vote")
	public String getNbVoteRoiDesMythos() {
		return nbVoteRoiDesMythos;
	}

	public void setNbVoteRoiDesMythos(String nbVoteRoiDesMythos) {
		this.nbVoteRoiDesMythos = nbVoteRoiDesMythos;
	}
	
	
	
	

}
