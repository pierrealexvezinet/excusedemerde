/*
 * XitiTagger.java - Copyright (c) 2010, Backelite.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.backelite.stats.xiti;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;
import fr.edm.utils.ToolBox;

/**
 * <p>
 * A simple class to send Xiti tags. TODO: Support action tag ("clic" parameter).
 * </p>
 * <p>
 * To use this class, initialize it with {@link #initialize(Context, String, String, String)}, and then tag pages by
 * calling {@link {@link #tagPage(String)}.
 * </p>
 * @author <a href="mailto:fabien.devos@backelite.com">Fabien Devos</a>
 */
public class XitiTagger {
    /** Used locally to tag Logs */
    private static final String TAG = "XitiTagger";
    /** Change this to false to disable log */
    private static final boolean LOG_ENABLED = true;

    /**
     * Used by tagAction to send actions to Xiti.
     * Actions are defined with the url parameter "clic"
     */
    public enum ActionType {
        /** Equivalent to "clic='A'" */
        ACTION("A"),
        /** Equivalent to "clic='S'" */
        EXIT("S"),
        /** Equivalent to "clic='N'" */
        NAVIGATION("N"),
        /** Equivalent to "clic='T'" */
        DOWNLOAD("T");
        
        private final String mClicValue;
        /** Constructor */
        private ActionType(String clicValue) {
            mClicValue = clicValue;
        }
        @Override
        public String toString() {
            return mClicValue;
        }
    }
    
    private static final String XITI_BASE_URL_FORMAT_STRING = "http://%s.xiti.com/hit.xiti";
    private static final String XITI_HOUR_FORMAT_STRING = "%02dx%02dx%02d";
    private static final String XITI_SITE_KEY = "s";
    private static final String XITI_SUB_SITE_KEY = "s2";
    private static final String XITI_CLIC_KEY = "clic";
    private static final String XITI_PAGE_KEY = "p";
    private static final String XITI_HOUR_KEY = "hl";
    private static final String XITI_ID_CLIENT_KEY = "idclient";
    private static final String XITI_OS_KEY = "os";
    private static final String XITI_MODEL_KEY = "mdl";
    private static final String XITI_CUSTOM_USER_AGENT = String.format("BkXitiTagger/1.0+(Android;[Android]-[%s])", android.os.Build.VERSION.RELEASE);

    private String mAndroidId;
    private String mBaseUrl;
    private String mSiteId;
    private String mSubSiteId;

    // ----------------------------------------------------------------
    // Singleton pattern
    private XitiTagger() {
    }

    // XitiTaggerHolder is loaded on the first execution of XitiTagger.getInstance()
    // or the first access to XitiTaggerHolder.INSTANCE, not before.
    private static class XitiTaggerHolder {
        private static final XitiTagger INSTANCE = new XitiTagger();
    }

    /** @return a unique instance of the class */
    public static XitiTagger getInstance() {
        return XitiTaggerHolder.INSTANCE;
    }

    /**
     * Not supported
     * @throws CloneNotSupportedException (every time)
     */
    @Override
	public Object clone() throws CloneNotSupportedException {
        // to prevent any kind of cheating
        throw new CloneNotSupportedException();
    }

    // ----------------------------------------------------------------

    /**
     * This method <strong>must</strong> be called at least once before any utilization of the XitiTagger class.
     * @param context the current {@link Context}, like the current Activity or Application object. This object
     *            <strong>won't</strong> keep any reference to it.
     * @param domain the xiti sub domain to use for all the xiti tags, (eg : if the call to Xiti should start by
     *            "http://yourdomain.xiti.com/hit.xiti", then the domain parameter must be "yourdomain")
     * @param siteId the first level site identifier (will be used as the "s" parameter :
     *            http://yourdomain.xiti.com/hit.xiti?s=siteId )
     * @param subSiteId the second level site identifier. Optional : can be null. (will be used as the "s2" parameter :
     *            http://yourdomain.xiti.com/hit.xiti?s=siteId&s2=subSiteId)
     */
    public void initialize(final Context context, final String domain, final String siteId, final String subSiteId) {

        // Retrieve unique device ID (this is the best way to get an unique id on Android platform)
        // Another option is Context.getSystemService(Context.TELEPHONY_SERVICE).getDeviceId(), but this won't works on
        // devices without telephony function
        // NB: System.ANDROID_ID is deprecated
        mAndroidId = Secure.getString(context.getApplicationContext().getContentResolver(), Secure.ANDROID_ID); 
        if (mAndroidId == null || mAndroidId.length() == 0) {
            mAndroidId = "0"; // the ID will be null on emulators, so we set it to a default value
        }
        // Make base url
        mBaseUrl = String.format(XITI_BASE_URL_FORMAT_STRING, domain);
        // Ids
        mSiteId = siteId;
        mSubSiteId = subSiteId; // null value here will be checked before building final url
    }

    /**
     * <p>
     * Tag the specified page with the specified subSiteId. This method will return immediately (it will perform an
     * asynchronous HTTP request without caring about the response).
     * </p>
     * <p>
     * Note that this method <strong>will actually change the internal subSiteId.</strong>
     * </p>
     * <p>
     * This method will throw a RuntimeException if initialize haven't be called yet
     * </p>
     * <p>The page param is automatically encoded before use in url.
     * </p>
     * @param subSiteId the subSiteId to use (and set in the XitiTagger object).
     * @param page the page to Tag (will send a request which will look like
     *            http://youdomain.xiti.com/hit.xiti?s=999999&
     *            s2=99&p=chapitre::page&hl=17x57x46&idclient=071013114239791117)
     */
    public void tagPage(final String subSiteId, final String page) {
        mSubSiteId = subSiteId;
        tagPage(page);
    }

    /**
     * <p>
     * Tag the specified page. This method will return immediately (it will perform an asynchronous HTTP request without
     * caring about the response).
     * </p>
     * <p>
     * This method will throw a RuntimeException if initialize haven't be called yet
     * </p>
     * <p>The page param is automatically encoded before use in url.
     * </p>
     * @param page the page to Tag (will send a request which will look like
     *            http://youdomain.xiti.com/hit.xiti?s=999999&
     *            s2=99&p=chapitre::page&hl=17x57x46&idclient=071013114239791117).
     */
    public void tagPage(final String page) {
        if (mBaseUrl == null) {
            throw new NotInitializedException("You must call XitiTagger.initialize() before calling tagPage().");
        }
        Log.d(TAG, "tagPage: " + String.valueOf(page));
        //ErrorReporter.getInstance().putCustomData("logXiti"+new Date().getTime(), "tagPage: " + String.valueOf(page));
        // Tag
        tag(page, null);
    }
    
    /**
     * <p>
     * Tag the specified action with the specified subSiteId. This method will return immediately (it will perform an
     * asynchronous HTTP request without caring about the response).
     * </p>
     * <p>
     * Note that this method <strong>will actually change the internal subSiteId.</strong>
     * </p>
     * <p>
     * This method will throw a RuntimeException if initialize haven't be called yet
     * </p>
     * <p>The action param is automatically encoded before use in url.
     * </p>
     * @param subSiteId the subSiteId to use (and set in the XitiTagger object).
     * @param actionType the type of action (ACTION, EXIT, NAVIGATION, or DOWNLOAD).
     * @param action the action to Tag (will send a request which will look like
     *            http://youdomain.xiti.com/hit.xiti?s=999999&
     *            s2=99&p=chapter::action_name&clic=A&hl=17x57x46&idclient=071013114239791117).
     */
    public void tagAction(final String subSiteId, final ActionType actionType, final String action) {
        mSubSiteId = subSiteId;
        tagAction(actionType, action);
    }

    /**
     * <p>
     * Tag the specified action. This method will return immediately (it will perform an asynchronous HTTP request without
     * caring about the response).
     * </p>
     * <p>
     * This method will throw a RuntimeException if initialize haven't be called yet
     * </p>
     * <p>The action param is automatically encoded before use in url.
     * </p>
     * @param actionType the type of action (ACTION, EXIT, NAVIGATION, or DOWNLOAD)
     * @param action the action to Tag (will send a request which will look like
     *            http://youdomain.xiti.com/hit.xiti?s=999999&
     *            s2=99&p=chapter::action_name&clic=A&hl=17x57x46&idclient=071013114239791117)
     *            Note that the action param is
     *            automatically encoded before use in url.
     */
    public void tagAction(final ActionType actionType, final String action) {
        if (mBaseUrl == null) {
            throw new NotInitializedException("You must call XitiTagger.initialize() before calling tagPage().");
        }

        Log.i(TAG, "tagAction: " + String.valueOf(action) + " (" + String.valueOf(actionType) + ")");
        //ErrorReporter.getInstance().putCustomData("logXiti"+new Date().getTime(), "tagAction: " + String.valueOf(action) + " (" + String.valueOf(actionType) + ")");
        // Tag
        tag(action, actionType);
    }
    
    /** Internal. Generic function to "tag" whether it is an action or a page */
    private void tag(final String p, final ActionType actionType) {
        if (mBaseUrl == null) {
            throw new NotInitializedException("You must call XitiTagger.initialize() before trying to tag.");
        }

        // Start a thread to execute the request, then return
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Build up the url
                final String url = buildUrl(p, actionType);

                // Send the request
                performGet(url);
                
                // Log
                if(LOG_ENABLED) { Log.d(TAG, String.format("Xiti tag sent: %s", url)); }
            }
        }).start();
    }
    

    /** Internal. Build the xiti url */
    private String buildUrl(final String p, final ActionType actionType) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(XITI_SITE_KEY, mSiteId);
        parameters.put(XITI_SUB_SITE_KEY, mSubSiteId);

        String xitiPage = XitiTagger.toXitiString(StringUtility.toAscii(p)); // only ascii
        parameters.put(XITI_PAGE_KEY, ToolBox.UrlEncode(xitiPage));
        if(actionType != null) {
            parameters.put(XITI_CLIC_KEY, actionType); // toString is used
        }
        
        Date now = new Date();
        parameters.put(XITI_HOUR_KEY, String.format(XITI_HOUR_FORMAT_STRING, now.getHours(), now.getMinutes(), now.getSeconds()));
        parameters.put(XITI_ID_CLIENT_KEY, mAndroidId);
        parameters.put(XITI_OS_KEY, String.format("[Android]-[%s]", XitiTagger.toXitiString(StringUtility.toAscii(android.os.Build.VERSION.RELEASE))));
        parameters.put(XITI_MODEL_KEY, XitiTagger.toXitiString(StringUtility.toAscii(android.os.Build.MODEL)));
        
        return StringUtility.buildUrl(mBaseUrl, parameters);
    }

    /** Internal. Perform a GET request and manually set the User Agent */
    private void performGet(final String url) {
        // Create a simple http client
        HttpClient httpClient = new DefaultHttpClient();

        // Create the "get" request
        HttpUriRequest httpUriRequest = new HttpGet(url);

        // Set the user agent
        httpUriRequest.setHeader("User-Agent", XITI_CUSTOM_USER_AGENT);
        
        // Send the request
        try {
            httpClient.execute(httpUriRequest);
        } catch (ClientProtocolException e) {
            // Nothing to do : we don't want to care about errors ("fire and forget")
        } catch (IOException e) {
            // Nothing to do : we don't want to care about errors ("fire and forget")
        }
                
    }
    
    /**
     * Because Xiti doesn't support [^a-zA-Z0-9]
     * @param page
     * @return
     */
    private static String toXitiString(final String page) {
        return page.replaceAll("[^a-zA-Z0-9:]", "_");
    }
    

    /**************************************************************************
     * NotInitializedException
     * @author <a href="mailto:fabien.devos@backelite.com">Fabien Devos</a>
     */
    @SuppressWarnings("serial")
    public static class NotInitializedException extends RuntimeException {

        public NotInitializedException(String message) {
            super(message);
        }

    }
}
