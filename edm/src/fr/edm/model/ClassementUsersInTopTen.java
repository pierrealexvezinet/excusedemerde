package fr.edm.model;

public class ClassementUsersInTopTen {
	
	private String urlPhotoUserInTopTen;
	private String pseudoUserInTopTen;
	private String nbVoteByUserInTopTen;
	
	
	public ClassementUsersInTopTen() {
		super();
	}


	public ClassementUsersInTopTen(String urlPhotoUserInTopTen,
			String pseudoUserInTopTen, String nbVoteByUserInTopTen) {
		super();
		this.urlPhotoUserInTopTen = urlPhotoUserInTopTen;
		this.pseudoUserInTopTen = pseudoUserInTopTen;
		this.nbVoteByUserInTopTen = nbVoteByUserInTopTen;
	}


	public String getUrlPhotoUserInTopTen() {
		return urlPhotoUserInTopTen;
	}


	public void setUrlPhotoUserInTopTen(String urlPhotoUserInTopTen) {
		this.urlPhotoUserInTopTen = urlPhotoUserInTopTen;
	}


	public String getPseudoUserInTopTen() {
		return pseudoUserInTopTen;
	}


	public void setPseudoUserInTopTen(String pseudoUserInTopTen) {
		this.pseudoUserInTopTen = pseudoUserInTopTen;
	}


	public String getNbVoteByUserInTopTen() {
		return nbVoteByUserInTopTen;
	}


	public void setNbVoteByUserInTopTen(String nbVoteByUserInTopTen) {
		this.nbVoteByUserInTopTen = nbVoteByUserInTopTen;
	}
	
	
	
	
	
	
	

}
