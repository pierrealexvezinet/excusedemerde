package fr.edm.model;

public class TopTen {

	/**
	 * @author pvezinet
	 *
	 */
	
	private String numVote;
	private int nbVote;
	private String auteurVote;
	private String auteurEdm;
	private String numEdm;
	private String keyVote;

	public TopTen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopTen(String numVote, int nbVote, String auteurVote, String auteurEdm,
			String numEdm, String keyVote) {
		super();
		this.numVote = numVote;
		this.nbVote = nbVote;
		this.auteurVote = auteurVote;
		this.auteurEdm = auteurEdm;
		this.numEdm = numEdm;
		this.keyVote = keyVote;
	}

	public String getNumVote() {
		return numVote;
	}

	public void setNumVote(String numVote) {
		this.numVote = numVote;
	}

	public int getNbVote() {
		return nbVote;
	}

	public void setNbVote(int nbVote) {
		this.nbVote = nbVote;
	}

	public String getAuteurVote() {
		return auteurVote;
	}

	public void setAuteurVote(String auteurVote) {
		this.auteurVote = auteurVote;
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

	public String getKeyVote() {
		return keyVote;
	}

	public void setKeyVote(String keyVote) {
		this.keyVote = keyVote;
	}

}
