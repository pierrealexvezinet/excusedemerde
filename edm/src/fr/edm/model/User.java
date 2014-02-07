package fr.edm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	/**
	 * @author pvezinet
	 *
	 */

	String pseudo, mdp, nom, prenom, dateNaissance, numResidence, extension, rue,
			codePostal, ville, pays, mail, civilite, statut, fuseau,
			cacherprofil, adresseComplete, photo, description;

	public User() {

	}

	public User(String pseudo, String mdp, String nom, String prenom, String dateNaissance,
			String numResidence, String extension, String rue,
			String codePostal, String ville, String pays, String mail,
			String civilite, String statut, String fuseau, String cacherprofil, String photo, String description) {

		this.pseudo = pseudo;
		this.mdp = mdp;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
        this.photo = photo;
		this.numResidence = numResidence;
		this.extension = extension;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.adresseComplete = numResidence + " " + extension + " " + rue + " " + codePostal + " " + ville + " " + pays;
		this.mail = mail;
		this.civilite = civilite;
		this.statut = statut;
		this.fuseau = fuseau;
		this.description = description;
		this.cacherprofil = cacherprofil;
		
		
		/*"pseudo": "pierrot",
		"nom": "Vezinet",
		"prenom": "Pierre",
		"jourAnniv": "26",
		"moisAnniv": "05",
		"anneeAnniv": "1988",
		"numResidence": "14",
		"extension": "Res",
		"rue": "de la grande prairie",
		"codePostal": "91330",
		"ville": "Yerres",
		"pays": "France",
		"mail": "pavezinet@live.fr",
		"mdp": "pipi",
		"civilite": "Monsieur",
		"statut": "CŽlibataire",
		"fuseau": "Europe/Paris",
		"description": "Pas grand chose ",
		"photo": "http://excuse-de-merde.fr/edm/edmeiffel_tower_7-wallpaper-1680x1050.jpg",
		"cacherprofil": "0"*/
		

	}
	@JsonProperty(value = "pseudo")
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	@JsonProperty(value = "mdp")
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	@JsonProperty(value = "nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	@JsonProperty(value = "prenom")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	@JsonProperty(value = "numResidence")
	public String getNumResidence() {
		return numResidence;
	}

	public void setNumResidence(String numResidence) {
		this.numResidence = numResidence;
	}
	@JsonProperty(value = "extension")
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	@JsonProperty(value = "rue")
	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}
	@JsonProperty(value = "codePostal")
	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	@JsonProperty(value = "ville")
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	@JsonProperty(value = "pays")
	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAdresseComplete() {
		return adresseComplete;
	}

	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}
	@JsonProperty(value = "mail")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	@JsonProperty(value = "civilite")
	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}
	@JsonProperty(value = "statut")
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	@JsonProperty(value = "fuseau")
	public String getFuseau() {
		return fuseau;
	}

	public void setFuseau(String fuseau) {
		this.fuseau = fuseau;
	}
	@JsonProperty(value = "cacherProfil")
	public String getCacherprofil() {
		return cacherprofil;
	}

	public void setCacherprofil(String cacherProfil) {
		this.cacherprofil = cacherProfil;
	}
	@JsonProperty(value = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@JsonProperty(value = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
