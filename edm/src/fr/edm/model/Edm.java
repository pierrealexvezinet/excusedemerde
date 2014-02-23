package fr.edm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author pvezinet
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Edm {

	private String numEdm;
	private String victime;
	private String categorie;
	private String contenu;
	private String datePost;
	private String heurePost;
	private String pseudo;
	private String nbLikeForEdm;

	public Edm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Edm(String numEdm, String victime, String categorie, String contenu,
			String datePost, String heurePost, String pseudo, String nbLikeForEdm) {
		super();
		this.numEdm = numEdm;
		this.victime = victime;
		this.categorie = categorie;
		this.contenu = contenu;
		this.datePost = datePost;
		this.heurePost = heurePost;
		this.pseudo = pseudo;
		this.nbLikeForEdm = nbLikeForEdm;
	}
	@JsonProperty(value = "numEdm")
	public String getNumEdm() {
		return numEdm;
	}

	public void setNumEdm(String numEdm) {
		this.numEdm = numEdm;
	}
	@JsonProperty(value = "victime")
	public String getVictime() {
		return victime;
	}

	public void setVictime(String victime) {
		this.victime = victime;
	}
	@JsonProperty(value = "categorie")
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	@JsonProperty(value = "contenu")
	public String getContenu() {
		return contenu;
	}
   
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	@JsonProperty(value = "datePost")
	public String getDatePost() {
		return datePost;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}
	@JsonProperty(value = "heurePost")
	public String getHeurePost() {
		return heurePost;
	}

	public void setHeurePost(String heurePost) {
		this.heurePost = heurePost;
	}
	@JsonProperty(value = "pseudo")
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNbLikeForEdm() {
		return nbLikeForEdm;
	}

	public void setNbLikeForEdm(String nbLikeForEdm) {
		this.nbLikeForEdm = nbLikeForEdm;
	}
	
	

}
