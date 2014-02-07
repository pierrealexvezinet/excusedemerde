package fr.edm.model;

public class LikeEdm {
	
	/**
	 * @author pvezinet
	 *
	 */

	private String numLike;
	private int nbLike;
	private String auteurLike;
	private String auteurEdm;
	private String numEdm; // FOREIGN KEY : numEdm
	private String keyLike;

	public LikeEdm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeEdm(String numLike, int nbLike, String auteurLike, String auteurEdm,
			String numEdm, String keyLike) {
		super();
		this.numLike = numLike;
		this.nbLike = nbLike;
		this.auteurLike = auteurLike;
		this.auteurEdm = auteurEdm;
		this.numEdm = numEdm;
		this.keyLike = keyLike;
	}

	public String getNumLike() {
		return numLike;
	}

	public void setNumLike(String numLike) {
		this.numLike = numLike;
	}

	public int getNbLike() {
		return nbLike;
	}

	public void setNbLike(int nbLike) {
		this.nbLike = nbLike;
	}

	public String getAuteurLike() {
		return auteurLike;
	}

	public void setAuteurLike(String auteurLike) {
		this.auteurLike = auteurLike;
	}

	public String getAuteurEdm() {
		return auteurEdm;
	}

	public void setAuteurEdm(String auteurEdm) {
		this.auteurEdm = auteurEdm;
	}

	public String getNumEdm() {
		return numEdm;
	}

	public void setNumEdm(String numEdm) {
		this.numEdm = numEdm;
	}

	public String getKeyLike() {
		return keyLike;
	}

	public void setKeyLike(String keyLike) {
		this.keyLike = keyLike;
	}

}
