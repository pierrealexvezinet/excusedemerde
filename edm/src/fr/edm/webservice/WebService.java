package fr.edm.webservice;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.*;

import fr.edm.model.User;

public class WebService {

	private final String URL = "http://excuse-de-merde.fr";

	Gson gson;

	public WebService() {
		gson = new Gson();
	}

	private InputStream sendRequest(URL url) throws Exception {

		try {
			// Ouverture de la connexion
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			// Connexion à l'URL
			urlConnection.connect();

			// Si le serveur nous répond avec un code OK
			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				return urlConnection.getInputStream();
			}
		} catch (Exception e) {
			throw new Exception("");
		}
		return null;
	}

	public User getUser() {

		try {
			// Envoi de la requête
			InputStream inputStream = sendRequest(new URL(URL));

			// Vérification de l'inputStream
			if (inputStream != null) {
				// Lecture de l'inputStream dans un reader
				InputStreamReader reader = new InputStreamReader(inputStream);

				// Retourne la liste désérialisée par le moteur GSON
				return gson.fromJson(reader, new TypeToken<User>() {
				}.getType());
			}

		} catch (Exception e) {
			Log.d("dede", "Impossible de rapatrier les données :(");
		}
		return null;
	}
}