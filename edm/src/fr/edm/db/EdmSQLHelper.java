package fr.edm.db;

import java.sql.*;

public class EdmSQLHelper {

	/**
	 * @author pvezinet
	 * 
	 */

	protected String nomDriver = "com.mysql.jdbc.Driver";
	protected static String urlConnexion = "jdbc:mysql://localhost:8889/edm";
	protected static String userName = "root";
	protected static String password = "root";
	protected static int cpt = 1;
	static Connection con;
	static ResultSet rs;
	static int rs2;
	static Statement st;

	// ///////////////////////////////////////////////////
	// ////////////METHODE chargementdriver()/////////////
	// ///////////////////////////////////////////////////

	public void chargementdriver() {

		try {
			Class.forName(nomDriver).newInstance();
		} catch (InstantiationException e) {
			// TODO Bloc catch auto-gŽnŽrŽ
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Bloc catch auto-gŽnŽrŽ
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Bloc catch auto-gŽnŽrŽ
			e.printStackTrace();
		}

	}

	// ///////////////////////////////////////////////////
	// ////////////METHODE connection()/////////////
	// ///////////////////////////////////////////////////

	public void connection() throws SQLException {
		con = DriverManager.getConnection(urlConnexion, userName, password);
		android.util.Log.d("dede", "drivers chargés");
	}

	// ///////////////////////////////////////////////////
	// ////////////METHODE executer_rq()/////////////
	// ///////////////////////////////////////////////////

	public void excuter_rq(String rq, String type) throws SQLException {
		if (type.equals("query")) {
			st = con.createStatement();
			rs = st.executeQuery(rq);
		}

		else if (type.equals("update")) {
			st = con.createStatement();
			rs2 = st.executeUpdate(rq);
		}

		else {
			android.util.Log
					.d("dede",
							"Une erreur s'est produite lors de l'execution de la requ�te");
		}
	}

	// ///////////////////////////////////////////////////
	// ////////////METHODE ACCESSEURS/////////////////////
	// ///////////////////////////////////////////////////

	public static Connection getCon() {
		return con;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static int getRs2() {
		return rs2;
	}

	public static Statement getSt() {
		return st;
	}

	// METHODE MAIN
	public static void main(String[] args) throws SQLException {
		android.util.Log.d("dede",
				"Classe Connexion corectement compilŽe ˆ cet instant prŽcis");

	}

}// fin class MethodesInteraction{}

