package fr.edm.utils;

import com.octo.android.robospice.persistence.DurationInMillis;

public class ApplicationConstants {
	public final static Boolean DEBUG = true;
	public static Boolean DEBUG_FORM = false;

	static {
		// switch all debug to false
		if (DEBUG == false) {
			DEBUG_FORM = false;
		}
	}

	// WEB SERVICES URI
	public final static String URI_WS = "http://www.excuse-de-merde.fr/ws/EdmWebServices.php";
	// PROPERTIES
	public final static String NUM_REQUEST = "NUM_REQUEST";
	public static final String PSEUDO = "pseudo";
	public static final String AUTEUR_EDM = "auteurEdm";
	public static final String CIVILITE = "civilite";
	public static final String JOUR = "jourAnniv";
	public static final String MOIS = "moisAnniv";
	public static final String ANNEE = "anneeAnniv";
	public static final String VILLE = "ville";
	public static final String STATUT = "statut";
	public static final String PAYS = "pays";
	public static final String DESCRIPTION = "description";
	public static final String NUM_EDM = "numEdm";
	public static final String MAIL = "mail";
	public static final String MDP = "mdp";
	public static final String VICTIME = "victime";
	public static final String CATEGORIE = "categorie";
	public static final String HEURE_POST = "heurePost";
	public static final String DATE_POST = "datePost";
	public static final String SAISIR_EDM = "saisirEdm";
	public static final String NB_VOTE = "nbVote";
	public static final String AUTEUR_VOTE = "auteurVote";
	public static final String KEY_VOTE = "keyVote";


	// RESTRICTIONS ON USER REQUEST
	public final static String LOGIN_USER = "LOGIN_USER";
	public final static String DECONNECT_USER = "DECONNECT_USER";
	public final static String GET_USER = "GET_USER";
	public final static String CREATE_USER = "CREATE_USER";
	public final static String UPDATE_USER = "UPDATE_USER";
	public final static String DELETE_USER = "DELETE_USER";
	public final static String GET_CLASSEMENT_TOP_TEN = "GET_CLASSEMENT_TOP_TEN";
	public final static String GET_ROI_DES_MYTHOS_USER = "GET_ROI_DES_MYTHOS_USER";
	public final static String HAS_USER_VOTED_FOR_EDM = "HAS_USER_VOTED_FOR_EDM";

	// RESTRICTIONS ON EDM REQUEST
	public final static String GET_USER_EDMs = "GET_USER_EDMs";
	public static String GET_ALL_EDMs = "GET_ALL_EDMs";
	public final static String GET_EDM_BY_NUM_EDM = "GET_EDM_BY_NUM_EDM";
	public final static String GET_LIST_EDM_BY_CATEGORIE_EDM = "GET_LIST_EDM_BY_CATEGORIE_EDM";
	public final static String GET_LIST_EDM_BY_AUTEUR_EDM = "GET_LIST_EDM_BY_AUTEUR_EDM";
	public final static String CREATE_EDM = "CREATE_EDM";
	public final static String DELETE_EDM = "DELETE_EDM";

	// RESTRICTIONS ON TOPTEN PIPOTEK
	public final static String GET_NB_VOTES_BY_NUM_EDM = "GET_NB_VOTES_BY_NUM_EDM";
	public final static String GET_NB_VOTES_BY_EDMs = "GET_NB_VOTES_BY_EDMs";
	public final static String VOTER_POUR_UNE_EDM = "VOTER_POUR_UNE_EDM";

	// RESTRICTIONS ON EDM LIKER EDM
	public final static String GET_NB_LIKE_BY_NUM_EDM = "GET_NB_LIKE_BY_NUM_EDM";
	public final static String GET_NB_LIKES_BY_EDMs = "GET_NB_LIKES_BY_EDMs";
	public final static String LIKER_EDM = "LIKER_EDM";

	// EXPIRATION CACHE DATA FOR ROBOSPICE REQUEST
	public static long STANDARD_EXPIRE_CACHE_DATA = 0;
	public static long NEVER_EXPIRE_CACHE_DATA = DurationInMillis.NEVER;
	public static long ALWAYS_EXPIRE_CACHE_DATA = DurationInMillis.ALWAYS;
	public static long ONE_MINUTE_EXPIRE_CACHE_DATA = DurationInMillis.ONE_MINUTE;
	public static long ONE_HOUR_EXPIRE_CACHE_DATA = DurationInMillis.ONE_HOUR;

}
