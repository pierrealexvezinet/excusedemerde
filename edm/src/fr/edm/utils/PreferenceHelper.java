package fr.edm.utils;

import java.util.ArrayList;

import fr.edm.model.UserInTopTen;
import fr.edm.model.Edm;
import fr.edm.model.EdmUser;
import fr.edm.model.LikeEdm;
import fr.edm.model.RoiDesMythos;
import fr.edm.model.TopTen;
import fr.edm.model.User;
import fr.edm.model.UserConnexion;

public class PreferenceHelper {

	/********************************************* LOGIN USER REMIND ME **************************************************/

	public static String loginConnexionUser, passwordConnexionUser;
	public static UserConnexion userConnexion;

	public static UserConnexion getUserConnexionInPreferences() {
		return userConnexion;
	}

	public static void setUserConnexionInPreferences(UserConnexion userConnexion) {
		PreferenceHelper.userConnexion = userConnexion;
		PreferenceHelper.loginConnexionUser = userConnexion
				.getLoginUserConnexion();
		PreferenceHelper.passwordConnexionUser = userConnexion
				.getPasswordConnexionUser();
	}

	public static String getLoginConnexionUser() {
		return loginConnexionUser;
	}

	public static void setLoginConnexionUser(String loginConnexionUser) {
		PreferenceHelper.loginConnexionUser = loginConnexionUser;
	}

	public static String getPasswordConnexionUser() {
		return passwordConnexionUser;
	}

	public static void setPasswordConnexionUser(String passwordConnexionUser) {
		PreferenceHelper.passwordConnexionUser = passwordConnexionUser;
	}

	/**************************************** USER PREFERENCES ********************************************************/

	public static String pseudo, mdp, nom, prenom, dateNaissance, numResidence,
			extension, rue, codePostal, ville, pays, adresseComplete, mail,
			civilite, statut, fuseau, cacherprofil, photo, description;
	public static User user = null;
	public static ArrayList<User> listUser = null;

	public static User getUserInPreferences() {
		return PreferenceHelper.user;
	}

	public static void setUserInPreferences(User user) {
		PreferenceHelper.user = user;
		pseudo = user.getPseudo();
		mdp = user.getMdp();
		nom = user.getNom();
		prenom = user.getPrenom();
		dateNaissance = user.getDateNaissance();
		numResidence = user.getNumResidence();
		extension = user.getExtension();
		rue = user.getRue();
		codePostal = user.getCodePostal();
		ville = user.getVille();
		pays = user.getPays();
		adresseComplete = numResidence + " " + extension + " " + rue + " "
				+ codePostal + " " + ville + " " + pays;
		mail = user.getMail();
		civilite = user.getCivilite();
		statut = user.getStatut();
		fuseau = user.getFuseau();
		cacherprofil = user.getCacherprofil();
		photo = user.getPhoto();
		description = user.getDescription();
		// listUser.add(user);
	}

	public static ArrayList<User> getListUserInPreference() {
		return listUser;
	}

	public static String getPseudo() {
		return pseudo;
	}

	public static void setPseudo(String pseudo) {
		PreferenceHelper.pseudo = pseudo;
	}

	public static String getMdp() {
		return mdp;
	}

	public static void setMdp(String mdp) {
		PreferenceHelper.mdp = mdp;
	}

	public static String getNom() {
		return nom;
	}

	public static void setNom(String nom) {
		PreferenceHelper.nom = nom;
	}

	public static String getPrenom() {
		return prenom;
	}

	public static void setPrenom(String prenom) {
		PreferenceHelper.prenom = prenom;
	}

	public static String getDateNaissance() {
		return dateNaissance;
	}

	public static void setDateNaissance(String dateNaissance) {
		PreferenceHelper.dateNaissance = dateNaissance;
	}

	public static String getNumResidence() {
		return numResidence;
	}

	public static void setNumResidence(String numResidence) {
		PreferenceHelper.numResidence = numResidence;
	}

	public static String getExtension() {
		return extension;
	}

	public static void setExtension(String extension) {
		PreferenceHelper.extension = extension;
	}

	public static String getRue() {
		return rue;
	}

	public static void setRue(String rue) {
		PreferenceHelper.rue = rue;
	}

	public static String getCodePostal() {
		return codePostal;
	}

	public static void setCodePostal(String codePostal) {
		PreferenceHelper.codePostal = codePostal;
	}

	public static String getVille() {
		return ville;
	}

	public static void setVille(String ville) {
		PreferenceHelper.ville = ville;
	}

	public static String getPays() {
		return pays;
	}

	public static void setPays(String pays) {
		PreferenceHelper.pays = pays;
	}

	public static String getAdresseComplete() {
		return adresseComplete;
	}

	public static void setAdresseComplete(String adresseComplete) {
		PreferenceHelper.adresseComplete = adresseComplete;
	}

	public static String getMail() {
		return mail;
	}

	public static void setMail(String mail) {
		PreferenceHelper.mail = mail;
	}

	public static String getCivilite() {
		return civilite;
	}

	public static void setCivilite(String civilite) {
		PreferenceHelper.civilite = civilite;
	}

	public static String getStatut() {
		return statut;
	}

	public static void setStatut(String statut) {
		PreferenceHelper.statut = statut;
	}

	public static String getFuseau() {
		return fuseau;
	}

	public static void setFuseau(String fuseau) {
		PreferenceHelper.fuseau = fuseau;
	}

	public static String getCacherprofil() {
		return cacherprofil;
	}

	public static void setCacherprofil(String cacherprofil) {
		PreferenceHelper.cacherprofil = cacherprofil;
	}

	public static String getPhoto() {
		return photo;
	}

	public static void setPhoto(String photo) {
		PreferenceHelper.photo = photo;
	}

	public static String getDescription() {
		return description;
	}

	public static void setDescription(String description) {
		PreferenceHelper.description = description;
	}

	public static ArrayList<User> getListUser() {
		return listUser;
	}

	public static void setListUser(ArrayList<User> listUser) {
		PreferenceHelper.listUser = listUser;
	}

	/**************************************** EDM PREFERENCES ********************************************************/

	public static String numEdm, victime, categorie, contenu, datePost,
			heurePost, pseudo_foreignKey_Edm;
	public static int countEdmsUser = 0;
	public static Edm edm = null;
	public static ArrayList<Edm> listEdm = null;

	public static ArrayList<EdmUser> listUserEdm = null;
	public static ArrayList<Edm> listAllEdm = null;

	public static Edm getEdmInPreferences() {
		return edm;
	}

	// EDM
	public static void setEdmInPreferences(Edm edm) {
		PreferenceHelper.edm = edm;
		numEdm = edm.getNumEdm();
		victime = edm.getVictime();
		categorie = edm.getCategorie();
		contenu = edm.getContenu();
		datePost = edm.getDatePost();
		heurePost = edm.getHeurePost();
		pseudo_foreignKey_Edm = edm.getPseudo();
		// listEdm.add(edm);
	}

	public static String getNbEdmsUserToString() {

		String nbEdmUser = "0";

		if (PreferenceHelper.getListUserEdm() != null) {
			nbEdmUser = String
					.valueOf(PreferenceHelper.getListUserEdm().size());
		}

		return nbEdmUser;
	}
	
	public static int getCountEdmsUser(){
		countEdmsUser = PreferenceHelper.getListUserEdm().size();
		return countEdmsUser;
	}
	
	public static void setCountEdmsUser(int edmUnity){
		countEdmsUser += edmUnity;
	}

	public static String getNumEdm() {
		return numEdm;
	}

	public static void setNumEdm(String numEdm) {
		PreferenceHelper.numEdm = numEdm;
	}

	public static String getVictime() {
		return victime;
	}

	public static void setVictime(String victime) {
		PreferenceHelper.victime = victime;
	}

	public static String getCategorie() {
		return categorie;
	}

	public static void setCategorie(String categorie) {
		PreferenceHelper.categorie = categorie;
	}

	public static String getContenu() {
		return contenu;
	}

	public static void setContenu(String contenu) {
		PreferenceHelper.contenu = contenu;
	}

	public static String getDatePost() {
		return datePost;
	}

	public static void setDatePost(String datePost) {
		PreferenceHelper.datePost = datePost;
	}

	public static String getHeurePost() {
		return heurePost;
	}

	public static void setHeurePost(String heurePost) {
		PreferenceHelper.heurePost = heurePost;
	}

	public static String getPseudo_foreignKey_Edm() {
		return pseudo_foreignKey_Edm;
	}

	public static void setPseudo_foreignKey_Edm(String pseudo_foreignKey_Edm) {
		PreferenceHelper.pseudo_foreignKey_Edm = pseudo_foreignKey_Edm;
	}

	public static ArrayList<Edm> getListEdm() {
		return listEdm;
	}

	public static void setListEdm(ArrayList<Edm> listEdm) {
		PreferenceHelper.listEdm = listEdm;
	}

	public static ArrayList<Edm> getListAllEdm() {
		return listAllEdm;
	}

	public static void setListAllEdm(ArrayList<Edm> listAllEdm) {
		PreferenceHelper.listAllEdm = listAllEdm;
	}

	public static ArrayList<EdmUser> getListUserEdm() {
		return listUserEdm;
	}

	public static void setListUserEdm(ArrayList<EdmUser> listUserEdm) {
		PreferenceHelper.listUserEdm = listUserEdm;
	}

	/**************************************** LikeEdm PREFERENCES ********************************************************/

	public static String numLike, auteurLike, auteurEdm,
			numEdm_foreignKey_LikeEdm, keyLike;
	public static int nbLike = 0;
	public static LikeEdm likeEdm = null;
	public static ArrayList<LikeEdm> listLikeEdm = null;

	public static LikeEdm getLikeEdmInPreferences() {
		return likeEdm;
	}

	public static void setLikeEdmInPreferences(LikeEdm likeEdm) {
		PreferenceHelper.likeEdm = likeEdm;
		numLike = likeEdm.getNumLike();
		nbLike = likeEdm.getNbLike();
		auteurLike = likeEdm.getAuteurLike();
		numEdm_foreignKey_LikeEdm = likeEdm.getNumEdm();
		keyLike = likeEdm.getKeyLike();
		auteurEdm = likeEdm.getAuteurEdm();
		// listLikeEdm.add(likeEdm);
	}

	public static String getNumLike() {
		return numLike;
	}

	public static void setNumLike(String numLike) {
		PreferenceHelper.numLike = numLike;
	}

	public static String getAuteurLike() {
		return auteurLike;
	}

	public static void setAuteurLike(String auteurLike) {
		PreferenceHelper.auteurLike = auteurLike;
	}

	public static String getAuteurEdm() {
		return auteurEdm;
	}

	public static void setAuteurEdm(String auteurEdm) {
		PreferenceHelper.auteurEdm = auteurEdm;
	}

	public static String getNumEdm_foreignKey() {
		return numEdm_foreignKey_LikeEdm;
	}

	public static void setNumEdm_foreignKey_likeEdm(
			String numEdm_foreignKey_LikeEdm) {
		PreferenceHelper.numEdm_foreignKey_LikeEdm = numEdm_foreignKey_LikeEdm;
	}

	public static String getKeyLike() {
		return keyLike;
	}

	public static void setKeyLike(String keyLike) {
		PreferenceHelper.keyLike = keyLike;
	}

	public static int getNbLike() {
		return nbLike;
	}

	public static void setNbLike(int nbLike) {
		PreferenceHelper.nbLike = nbLike;
	}

	public static ArrayList<LikeEdm> getListLikeEdm() {
		return listLikeEdm;
	}

	public static void setListLikeEdm(ArrayList<LikeEdm> listLikeEdm) {
		PreferenceHelper.listLikeEdm = listLikeEdm;
	}

	/**************************************** TopTen PREFERENCES ********************************************************/

	public static String numVote, auteurVote, auteurEdm_foreignKey_topTen,
			numEdm_foreignKey_topTen, keyVote;
	public static int nbVote;
	public static TopTen topTen = null;
	public static ArrayList<TopTen> listTopTen;

	public static TopTen getTopTenInPreferences() {
		return topTen;
	}

	public static void setTopTenInPreferences(TopTen topTen) {
		PreferenceHelper.topTen = topTen;
		numVote = topTen.getNumVote();
		auteurVote = topTen.getAuteurVote();
		auteurEdm_foreignKey_topTen = topTen.getAuteurEdm();
		numEdm_foreignKey_topTen = topTen.getNumEdm();
		keyVote = topTen.getKeyVote();
		nbVote = topTen.getNbVote();
		// listTopTen.add(topTen);
	}

	public static String getNumEdm_foreignKey_LikeEdm() {
		return numEdm_foreignKey_LikeEdm;
	}

	public static void setNumEdm_foreignKey_LikeEdm(
			String numEdm_foreignKey_LikeEdm) {
		PreferenceHelper.numEdm_foreignKey_LikeEdm = numEdm_foreignKey_LikeEdm;
	}

	public static String getNumVote() {
		return numVote;
	}

	public static void setNumVote(String numVote) {
		PreferenceHelper.numVote = numVote;
	}

	public static String getAuteurVote() {
		return auteurVote;
	}

	public static void setAuteurVote(String auteurVote) {
		PreferenceHelper.auteurVote = auteurVote;
	}

	public static String getAuteurEdm_foreignKey_topTen() {
		return auteurEdm_foreignKey_topTen;
	}

	public static void setAuteurEdm_foreignKey_topTen(
			String auteurEdm_foreignKey_topTen) {
		PreferenceHelper.auteurEdm_foreignKey_topTen = auteurEdm_foreignKey_topTen;
	}

	public static String getNumEdm_foreignKey_topTen() {
		return numEdm_foreignKey_topTen;
	}

	public static void setNumEdm_foreignKey_topTen(
			String numEdm_foreignKey_topTen) {
		PreferenceHelper.numEdm_foreignKey_topTen = numEdm_foreignKey_topTen;
	}

	public static String getKeyVote() {
		return keyVote;
	}

	public static void setKeyVote(String keyVote) {
		PreferenceHelper.keyVote = keyVote;
	}

	public static int getNbVote() {
		return nbVote;
	}

	public static void setNbVote(int nbVote) {
		PreferenceHelper.nbVote = nbVote;
	}

	public static ArrayList<TopTen> getListTopTen() {
		return listTopTen;
	}

	public static void setListTopTen(ArrayList<TopTen> listTopTen) {
		PreferenceHelper.listTopTen = listTopTen;
	}

	/************************************** CLASSEMENT USERS IN TOP TEN PREFERENCES ***********************************/

	public static String classementUrlPhotoUserInTopTenInPreferences;
	public static String classementPseudoUserInTopTenInPreferences;
	public static String nbVoteByUserInTopTenInPreferences;
	public static UserInTopTen classementUsersInTopTenInPreferences;
	public static ArrayList<UserInTopTen> listClassementUserInTopTen;

	public static void setClassementUsersInTopTen(
			UserInTopTen classementUsersInTopTenInPreferences) {

		classementPseudoUserInTopTenInPreferences = classementUsersInTopTenInPreferences
				.getPseudoUserInTopTen();
		nbVoteByUserInTopTenInPreferences = PreferenceHelper.classementUsersInTopTenInPreferences
				.getNbVoteByUserInTopTen();
		classementUrlPhotoUserInTopTenInPreferences = classementUsersInTopTenInPreferences
				.getUrlPhotoUserInTopTen();
		PreferenceHelper.classementUsersInTopTenInPreferences = classementUsersInTopTenInPreferences;
		listClassementUserInTopTen.add(classementUsersInTopTenInPreferences);
	}

	public UserInTopTen getClassementUsersInTopTenInPreferences() {
		return classementUsersInTopTenInPreferences;
	}

	public static String getclassementUrlPhotoUserInTopTenInPreferences() {
		return classementUrlPhotoUserInTopTenInPreferences;
	}

	public static void setclassementUrlPhotoUserInTopTenInPreferences(
			String classementUrlPhotoUserInTopTenInPreferences) {
		PreferenceHelper.classementUrlPhotoUserInTopTenInPreferences = classementUrlPhotoUserInTopTenInPreferences;
	}

	public static String getclassementPseudoUserInTopTenInPreferences() {
		return classementPseudoUserInTopTenInPreferences;
	}

	public static void setclassementPseudoUserInTopTenInPreferences(
			String classementPseudoUserInTopTenInPreferences) {
		PreferenceHelper.classementPseudoUserInTopTenInPreferences = classementPseudoUserInTopTenInPreferences;
	}

	public static String getNbVoteByUserInTopTenInPreferences() {
		return nbVoteByUserInTopTenInPreferences;
	}

	public static void setNbVoteByUserInTopTenInPreferences(
			String nbVoteByUserInTopTenInPreferences) {
		PreferenceHelper.nbVoteByUserInTopTenInPreferences = nbVoteByUserInTopTenInPreferences;
	}

	public static String getClassementUrlPhotoUserInTopTenInPreferences() {
		return classementUrlPhotoUserInTopTenInPreferences;
	}

	public static void setClassementUrlPhotoUserInTopTenInPreferences(
			String classementUrlPhotoUserInTopTenInPreferences) {
		PreferenceHelper.classementUrlPhotoUserInTopTenInPreferences = classementUrlPhotoUserInTopTenInPreferences;
	}

	public static String getClassementPseudoUserInTopTenInPreferences() {
		return classementPseudoUserInTopTenInPreferences;
	}

	public static void setClassementPseudoUserInTopTenInPreferences(
			String classementPseudoUserInTopTenInPreferences) {
		PreferenceHelper.classementPseudoUserInTopTenInPreferences = classementPseudoUserInTopTenInPreferences;
	}

	public static ArrayList<UserInTopTen> getListClassementUserInTopTen() {
		return listClassementUserInTopTen;
	}

	public static void setListClassementUserInTopTen(
			ArrayList<UserInTopTen> listClassementUserInTopTen) {
		PreferenceHelper.listClassementUserInTopTen = listClassementUserInTopTen;
	}

	public static void setClassementUsersInTopTenInPreferences(
			UserInTopTen classementUsersInTopTenInPreferences) {
		PreferenceHelper.classementUsersInTopTenInPreferences = classementUsersInTopTenInPreferences;
	}

	/************************************** ROI DES MYTHOS PREFERENCES ***********************************/

	public static String roiDesMythosUrlPhotoUserInPreferences;
	public static String roiDesMythosPseudoInPreferences;
	public static String roiDesMythosnbVoteInPreferences;
	public static RoiDesMythos roiDesMythosInPreferences;


	public static void setRoiDesMythosInPreferences(
			RoiDesMythos roiDesMythosInPreferences) {

		roiDesMythosPseudoInPreferences = roiDesMythosInPreferences
				.getPseudoRoiDesMythos();
		roiDesMythosUrlPhotoUserInPreferences = roiDesMythosInPreferences
				.getUrlPhotoRoiDesMythos();
		roiDesMythosnbVoteInPreferences = roiDesMythosInPreferences
				.getNbVoteRoiDesMythos();
	

	}

	public static RoiDesMythos getRoiDesMythosInPreferences() {
		return roiDesMythosInPreferences;
	}

	public static String getRoiDesMythosUrlPhotoUserInPreferences() {
		return roiDesMythosUrlPhotoUserInPreferences;
	}

	public static void setRoiDesMythosUrlPhotoUserInPreferences(
			String roiDesMythosUrlPhotoUserInPreferences) {
		PreferenceHelper.roiDesMythosUrlPhotoUserInPreferences = roiDesMythosUrlPhotoUserInPreferences;
	}

	public static String getRoiDesMythosPseudoInPreferences() {
		return roiDesMythosPseudoInPreferences;
	}

	public static void setRoiDesMythosPseudoInPreferences(
			String roiDesMythosPseudoInPreferences) {
		PreferenceHelper.roiDesMythosPseudoInPreferences = roiDesMythosPseudoInPreferences;
	}

	public static String getRoiDesMythosnbVoteInPreferences() {
		return roiDesMythosnbVoteInPreferences;
	}

	public static void setRoiDesMythosnbVoteInPreferences(
			String roiDesMythosnbVoteInPreferences) {
		PreferenceHelper.roiDesMythosnbVoteInPreferences = roiDesMythosnbVoteInPreferences;
	}

}
