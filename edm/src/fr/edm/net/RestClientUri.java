package fr.edm.net;

import org.json.JSONObject;

import android.net.Uri;

public class RestClientUri{
	
	private Uri uri;
	
	private JSONObject json;

	public RestClientUri(String url) {
		this.json = null;
		uri = Uri.parse(url);
	}

	public RestClientUri(JSONObject object, String url) {
		this.json = object;
		uri = Uri.parse(url);
	}

	public String generateUri() {
		return uri.toString();
	}

	public boolean isPost() {
		return json != null;
	}

	public String generateParameters() {
		return json.toString();
	}
	
	public boolean isAbsolute() {
		return uri.isAbsolute();
	}

}
