package fr.edm.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.ContentHandler;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.google.android.filecache.FileResponseCache;


import fr.edm.utils.Installation;
import fr.edm.utils.PreferencesConstants;
import fr.edm.utils.ToolBox;


public class RestClient {

	private static String TAG = RestClient.class.getSimpleName();
	private static ContentHandler wsContentHandler;
	private static ContentHandler wsContentHandlerCache;
	
	/*public static Object HttpGetJson(RestClientUri uri, Context ctx) {
		return HttpGetJson(uri, ctx, false, false);
	}
	
	public static Object HttpGetJson(RestClientUri uri, Context ctx, boolean forceAuthentication) {
		return HttpGetJson(uri, ctx, forceAuthentication, false);*/
	}
	
	/**
	 * @param url
	 * @param ctx
	 * @param forceAuthentication will ensure authentication is added to the http request
	 * @param forceNoCache will avoid using cache. This only ignore server cache http header. 
	 * 			settings this to false will not use cache, server http header need to be present.
	 * @return
	 */
/*	public static Object HttpGetJson(RestClientUri uri, Context ctx, boolean forceAuthentication, boolean forceNoCache) {
		
		Object data = null;
		URLConnection connection = null;
		
		
		
        try {
        	//Doc said we have to force proxy here if we need one. It is not java compliant.
        	//http://developer.android.com/reference/java/net/HttpURLConnection.html
        	URL url = new URL(uri.generateUri());
        	if (MCasinoApplication.FORCE_PROXY) {
        		connection = url.openConnection(MCasinoApplication.proxy);
        	} else {
        		connection = url.openConnection();
        	}
        	connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        	connection.setRequestProperty("X-mCasino-UID", Installation.id(ctx));

        	//envoi des cookies
        	Map<String, String> cookies = MCasinoApplication.getCookies();
        	String headerCookie = "";
        	if(cookies.size() > 0){
        		for(String cookieName : cookies.keySet()){
        			if(ToolBox.isNotNullOrEmpty(headerCookie)) headerCookie = headerCookie + ";";
        			headerCookie = headerCookie + cookieName+"="+cookies.get(cookieName);
        		}
            	connection.addRequestProperty("Cookie", headerCookie);
        	}
        	
        	try {
				PackageInfo pInfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
	        	connection.setRequestProperty("X-mCasino-Version", pInfo.versionName); 
			} catch (NameNotFoundException e) {}
        	//USER AGENT: le device est present.
        	
        	if ( forceAuthentication ) {
    			if (!ToolBox.isAccountSet(ctx)){
    				return new CasinoWsError(CasinoWsError.ERR_CASINO_LOGIN_NO_ACCOUNT, "No valid account found");
    			}
    			SharedPreferences settings = ctx.getSharedPreferences(PreferencesConstants.PREFS_FILE, Context.MODE_PRIVATE);
    			String tmpPw = ToolBox.decryptAES(settings.getString(PreferencesConstants.PREFS_CASINO_PASSWORD, null));
    			final String password = (tmpPw == null) ? "" : tmpPw;
    			final String user = settings.getString(PreferencesConstants.PREFS_CASINO_USER, null);
    			Authenticator.setDefault(new Authenticator() {
    			private boolean alreadyTried = false;
    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					if (alreadyTried) {
    						return null;
    					}
    					alreadyTried = true;
    					return new PasswordAuthentication(user, password.toCharArray());
    				}
    			});
    			//HACK: try to be preemptive by forcing header manually.
    			byte[] authBytes = EncodingUtils.getBytes(user + ":" + password, "UTF-8"); 
        		connection.addRequestProperty("Authorization", "Basic " + net.sourceforge.iharder.Base64.encodeBytes(authBytes));
    		} else {
    			Authenticator.setDefault(null);
    		}
        	
        	// POST case
        	if(uri.isPost()){
            	connection.setDoOutput(true); // Triggers POST.
            	if (connection instanceof HttpURLConnection){
                    ((HttpURLConnection)connection).setRequestMethod("POST");
            	}
            	String postParameters = uri.generateParameters();
            	OutputStream output = null;
            	try {
            	     output = connection.getOutputStream();
            	     output.write(postParameters.getBytes("UTF-8"));
            	} finally {
            	     if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
            	}
        	}
        	
        	//TODO never cache http error or json errors.
        	
        	if ( forceNoCache ) {
        		if (wsContentHandler == null) {
        			wsContentHandler = new WsContentHandler();
        		}
        		data = wsContentHandler.getContent(connection);
        	} else {
        		if (wsContentHandlerCache == null) {
        			wsContentHandlerCache = FileResponseCache.capture(new WsContentHandler(), null); 
        		}
        		data = wsContentHandlerCache.getContent(connection);
        	}
        	
        	
        } catch (IOException ioe) {
			Log.e(TAG, "WS call failed", ioe);
			data = new CasinoWsError(CasinoWsError.ERR_CON, "connection error");
        } finally {
            ToolBox.disconnect(connection);
        }
		return data;
    }
}*/
