package fr.edm;

import java.net.ContentHandler;
import java.net.URLStreamHandlerFactory;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.filecache.FileResponseCache;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.imageloader.BitmapContentHandler;
import com.google.android.imageloader.ImageLoader;
import com.itelios.itframework.ITApplication;


import fr.edm.dialog.WaitingProgressBar;
import fr.edm.net.EdmCache;

/**
 * 
 * @author Pierre-Alexandre VEZINET
 * 
 */
public class EdmApplication extends ITApplication implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener,
		View.OnClickListener {
	// private static final int DRAWER_HANDLE_HEIGHT = 39;

	public final static int REQUEST_STORE = 1;
	public final static String PARAM_MCOMMERCE = "mCommerce";
	public final static String RESULT_STORE_TYPE = "store_type";
	public final static String PARAM_STORE_FAVORITE = "favorite";
	public final static String RESULT_STORE_ID = "store_id";

	public static final String FILTER_OPEN_ALL_ACTION = "filterOpenAll";
	public static final String FILTER_OPEN_SUNDAY_ACTION = "filterOpenSunday";

	public static final int ZOOM_GEOLOC = 10;
	public static final double QUERY_RADIUS_DEFAULT = 20;
	public static final String INITIAL_SETUP = "INITIAL_SETUP";
	public static int NB_INSTANCE_EDM_FRAGMENT_ACTIVITY = 0;

	private static WaitingProgressBar waitingProgressBar;

	/**
	 * show stores with showStoresWithinMapBounds() or not.
	 */

	/* *****************************************************************************************************
	 * **************************************** Application dialogs
	 * *****************************************
	 * *********************************
	 * ********************************************************************
	 */
	/**
	 * Don't use it, annoying for user!!!
	 */
	public static void showWaitingDialogBlocking(Activity activity) {
		unShowWaitingDialog();
		waitingProgressBar = new WaitingProgressBar(activity);
		waitingProgressBar.show();
	}

	public static void showWaitingDialog(Activity activity) {
		unShowWaitingDialog();
		waitingProgressBar = new WaitingProgressBar(activity);
		waitingProgressBar.setCancelable(true);
		waitingProgressBar.show();
	}

	public static void showWaitingDialogCancelableAndCloseActivity(
			Activity activity) {
		unShowWaitingDialog();
		waitingProgressBar = new WaitingProgressBar(activity);
		waitingProgressBar.setCancelableWithOnCancelListener();
		waitingProgressBar.show();
	}

	public static void unShowWaitingDialog() {
		if (waitingProgressBar != null && waitingProgressBar.isShowing()) {
			try {
				waitingProgressBar.dismiss();
			} catch (IllegalArgumentException e) {
				Log.e("dede",
						"waitingProgressBar disappear before, it could be dismiss");
			}
		}
	}

	/**
	 * Create the image loader for the application
	 * 
	 * @param context
	 * @return
	 */
	private static ImageLoader createImageLoader(Context context) {
		// Install the file cache (if it is not already installed)
		EdmCache.install(context);

		// Just use the default URLStreamHandlerFactory because
		// it supports all of the required URI schemes (http).
		URLStreamHandlerFactory streamFactory = null;

		// Load images using a BitmapContentHandler
		// and cache the image data in the file cache.
		ContentHandler bitmapHandler = FileResponseCache.capture(new BitmapContentHandler(), null);

		// For pre-fetching, use a "sink" content handler so that the
		// the binary image data is captured by the cache without actually
		// parsing and loading the image data into memory. After pre-fetching,
		// the image data can be loaded quickly on-demand from the local cache.
		ContentHandler prefetchHandler = FileResponseCache.capture(FileResponseCache.sink(), null);

		// Perform callbacks on the main thread
		Handler handler = null;

		return new ImageLoader(10, streamFactory, bitmapHandler, prefetchHandler, 25000000, handler);
	}
	
	
	public static void initWaitingDialog(Activity act) {
		if (waitingProgressBar == null)
			waitingProgressBar = new WaitingProgressBar(act);
	}

	// public static Dialog initMCCDialog(Activity act) {
	// // MCCOptin = new Dialog(act);
	// //return MCCOptin;
	// }
	//
	// public static Dialog GetMCCDialog() {
	// return MCCOptin;
	// }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub

	}

	/* *****************************************************************************************************
	 * ************************************** END Application dialogs
	 * ***************************************
	 * ***********************************
	 * ******************************************************************
	 */

}
