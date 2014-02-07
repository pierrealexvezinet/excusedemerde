package fr.edm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import net.sourceforge.iharder.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

//import com.pax.itframework.exception.ITException;
//import com.pax.itframework.utils.JsonUtils;

import fr.edm.adapter.AutocompleteCursorAdapter;
import fr.edm.db.DbOpenHelper;
import fr.edm.db.SQLQueries;
import fr.edm.exception.AutoCompleteNotAvailableException;
import fr.edm.model.Edm;
import fr.edm.model.PostEdm;



public class ToolBox {

	private static final String TAG = ToolBox.class.getSimpleName();
	public static boolean IS_TABLET_MODE = false;
	static int listViewTouchAction;

	/**
	 * private constructor. this is a static helper. We do not want someone
	 * instantiating it.
	 */
	private ToolBox() {
	}

	/**
	 * this is an inverse of String.split() takes a collection and join
	 * everything into a string using delimiter.
	 * 
	 * @param col
	 * @param delimiter
	 * @return
	 */
	public static String join(Collection<?> col, String delimiter) {

		if (col == null || col.isEmpty())
			return "";

		Iterator<?> iter = col.iterator();
		StringBuilder builder = new StringBuilder();
		builder.append(iter.next());

		while (iter.hasNext()) {
			builder.append(delimiter).append(iter.next());
		}
		return builder.toString();
	}
	


	public static void copyStream(InputStream is, OutputStream os) {
		final int buffer_size = 1024;
		try {
			byte[] bytes = new byte[buffer_size];
			for (;;) {
				int count = is.read(bytes, 0, buffer_size);
				if (count == -1)
					break;
				os.write(bytes, 0, count);
			}
		} catch (Exception ex) {
		}
	}
	
	public static void setListViewScrollable(final ListView list) {
        list.setOnTouchListener(new OnTouchListener() {
       
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                 listViewTouchAction = event.getAction();
                if (listViewTouchAction == MotionEvent.ACTION_MOVE)
                {
                    list.scrollBy(0, -1);
                }
                return false;
            }

			
        });
 
        list.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view,
                    int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
                if (listViewTouchAction == MotionEvent.ACTION_MOVE)
                {
                    list.scrollBy(0, 1);
                }
            }
        });

    }
   

	
	
	public boolean isTabletLandscape(Context ctx) {
		Point screenSize = ToolBox.getScreenSize(ctx);
		
		int orientation = Configuration.ORIENTATION_UNDEFINED;

		if (screenSize.x < screenSize.y) {
			orientation = Configuration.ORIENTATION_PORTRAIT;
			IS_TABLET_MODE = false;
		} else {
			orientation = Configuration.ORIENTATION_LANDSCAPE;
			IS_TABLET_MODE = true;
		}
		return IS_TABLET_MODE;
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public static String getProtocol(String url) {
		Uri uri = Uri.parse(url);
		return uri.getScheme();
	}

	public static void disconnect(URLConnection connection) {
		if (connection != null && connection instanceof HttpURLConnection) {
			HttpURLConnection http = (HttpURLConnection) connection;
			http.disconnect();
		}
	}

	public static String inputStreamToString(InputStream input) throws IOException {
		char[] buffer = new char[0x10000];
		StringBuilder out = new StringBuilder();
		Reader in;
		try {
			in = new InputStreamReader(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// go with whatever fits.
			in = new InputStreamReader(input);
		}
		int read;
		do {
			read = in.read(buffer, 0, buffer.length);
			if (read > 0) {
				out.append(buffer, 0, read);
			}
		} while (read >= 0);
		return out.toString();
	}

	public static void force32bitsBg(Window w, Drawable d) {
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		lp.copyFrom(w.getAttributes());
		lp.format = PixelFormat.RGBX_8888;
		w.setAttributes(lp);
		d.setDither(true);
		w.setBackgroundDrawable(d);
	}

	public static boolean isEmulator() {
		return "sdk".equals(Build.PRODUCT) || "google_sdk".equals(Build.PRODUCT);
	}

	public static boolean isWifi(Context ctx) {
		WifiManager wifiMgr = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		// WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
		return wifiMgr.isWifiEnabled();
	}

	public static boolean isMobile(Context ctx) {
		TelephonyManager telMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		return telMgr.getDataState() != TelephonyManager.DATA_DISCONNECTED;
	}

	public static boolean isConnected(Context ctx) {
		ConnectivityManager connManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		NetworkInfo mMobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		return mWifi.isAvailable() || mMobile.isAvailable();
	}

	/*
	 * private static String getIpAsString(InetAddress address) { byte[]
	 * ipAddress = address.getAddress(); StringBuffer str = new StringBuffer();
	 * for(int i=0; i<ipAddress.length; i++) { if(i > 0) str.append('.');
	 * str.append(ipAddress[i] & 0xFF); } return str.toString(); }
	 */

	public static String getValidFloat(String value) {
		String newValue = value.replace(",", ".");
		return newValue;
	}

	public static String toTitleCase(String in) {
		if (in == null) {
			return null;
		}
		in = in.trim();
		return in.substring(0, 1).toUpperCase() + in.substring(1).toLowerCase();
	}

	public static boolean isZxingScannerAvailable(Context ctx) {
		final PackageManager packageManager = ctx.getPackageManager();
		Intent intent = new Intent(); // hack: on crÃ©Ã© une intent scanner pour
		// vÃ©rifier s'il est bien installÃ©
		intent.setAction("com.google.zxing.client.android.SCAN");
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return (list.size() > 0);
	}


	public static boolean isFacebookAvailable(Context ctx) {
		final PackageManager packageManager = ctx.getPackageManager();
		Intent intent = new Intent(); // hack: we create a FB intent to check if
		// it's installed (v1.5)
		intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return (list.size() > 0);
	}


	
	


	public static String hexDump(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%02x", bytes[i]));
		}
		return sb.toString();
	}

	public static boolean isNotNullOrEmpty(String s) {
		if (s == null || s.length() == 0 || "null".equals(s) || "\\u000a".equals(s)) {
			return false;
		}
		return true;
	}

	public static void copyFile(File srcFile, File dstFile) throws IOException {
		InputStream in = new FileInputStream(srcFile);
		// For Append the file.
		// OutputStream out = new FileOutputStream(dstFile,true);
		// For Overwrite the file.
		OutputStream out = new FileOutputStream(dstFile);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public static boolean isAccountSet(Context ctx) {
		SharedPreferences settings = ctx.getSharedPreferences(PreferencesConstants.PREFS_FILE, Context.MODE_PRIVATE);
		return settings.getString(PreferencesConstants.PREFS_CASINO_PASSWORD, null) != null
				&& settings.getString(PreferencesConstants.PREFS_CASINO_USER, null) != null;
	}

	public static String dump(HttpURLConnection c) {

		StringBuilder sb = new StringBuilder();
		sb.append("Response code: ");
		try {
			sb.append(c.getResponseCode());
		} catch (IOException ioe) {
		}
		sb.append(" headers: ");
		for (Entry<String, List<String>> e : c.getHeaderFields().entrySet()) {
			sb.append("(");
			sb.append(e.getKey() + "->");
			int nbVal = 0;
			for (String val : e.getValue()) {
				if (nbVal > 0) {
					sb.append(",");
				}
				sb.append(val);
				nbVal++;
			}
			sb.append(")");
		}
		sb.append(" data: ");
		try {
			sb.append(c.getResponseMessage());
		} catch (IOException ioe) {
		}

		return sb.toString();
	}

	/**
	 * This is because overridePendingTransition doesn't exist in Donut 1.6
	 * 
	 * @param activity
	 *            where animation must be applied
	 * @param enterAnim
	 *            enter animation
	 * @param exitAnim
	 *            exit animation
	 */
	public static void overridePendingTransition(Activity activity, int enterAnim, int exitAnim) {
		try {
			Method animation = activity.getClass().getMethod("overridePendingTransition", int.class, int.class);
			animation.invoke(activity, enterAnim, exitAnim);
		} catch (SecurityException e) {
			Log.w(activity.getClass().getSimpleName(), "overridePendingTransition security eror, no transitions");
		} catch (NoSuchMethodException e) {
			Log.w(activity.getClass().getSimpleName(), "overridePendingTransition doesn't exist in this api, no transitions");
		} catch (IllegalArgumentException e) {
			Log.w(activity.getClass().getSimpleName(), "overridePendingTransition call eror, no transitions");
		} catch (IllegalAccessException e) {
			Log.w(activity.getClass().getSimpleName(), "overridePendingTransition call eror, no transitions");
		} catch (InvocationTargetException e) {
			Log.w(activity.getClass().getSimpleName(), "overridePendingTransition call eror, no transitions");
		}
	}

	/**
	 * This is because overridePendingTransition doesn't exist in Donut 1.6
	 * 
	 * @param activity
	 *            where animation must be applied
	 * @param enterAnim
	 *            enter animation
	 * @param exitAnim
	 *            exit animation
	 */
	public static String getExternalDir(Context context) {
		String path = null;
		try {
			Method getExternalFilesDirMeth = context.getClass().getMethod("getExternalFilesDir", String.class);
			String directoryDownload = (String) Environment.class.getField("DIRECTORY_DOWNLOADS").get(null);
			File file = (File) getExternalFilesDirMeth.invoke(context, directoryDownload);
			if (file != null) {
				path = file.getPath();
			}
		} catch (SecurityException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir security eror");
		} catch (NoSuchMethodException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir doesn't exist in this api");
		} catch (IllegalArgumentException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir call eror");
		} catch (IllegalAccessException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir call eror");
		} catch (InvocationTargetException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir call eror");
		} catch (NoSuchFieldException e) {
			Log.w(context.getClass().getSimpleName(), "getExternalFilesDir call eror");
		}

		if (path == null) {
			path = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data"
					+ File.separator + "fr.casino.mcasino" + File.separator + "files" + File.separator + "Download";
		}

		return path;
	}

	public static String getPrice(Double prix) {
		if (prix != Double.NaN) {
			DecimalFormat form = new DecimalFormat("#0.00");
			String subTotal = form.format(prix);
			return subTotal;
		} else {
			return "-.--";
		}

	}

	

	public static void initAutoComplete(Context context, AutoCompleteTextView autoComplete) {
		Cursor cursor;
		try {
			cursor = SQLQueries.getInstance().getFirsts(DbOpenHelper.getInstance(context));
			AutocompleteCursorAdapter adapter = new AutocompleteCursorAdapter(context, cursor);
			autoComplete.setAdapter(adapter);
		} catch (AutoCompleteNotAvailableException e) {
			Log.w(context.toString(),
					"Erreur lors du chargement des mots-clefs, fonction désactivée",
					e);
		}
	}

	public static List<String> getListFromJsonArray(JSONArray favoriteProducts) {
		List<String> favoris = new ArrayList<String>();
		for (int i = 0; i < favoriteProducts.length(); i++) {
			String idProd = favoriteProducts.optString(i);
			favoris.add(idProd);
		}
		return favoris;
	}

	public static List<JSONObject> getJSONObjectListFromJsonArray(JSONArray items) {
		List<JSONObject> itemsList = new ArrayList<JSONObject>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.optJSONObject(i);
			itemsList.add(item);
		}
		return itemsList;
	}

	public static String join(Iterator<?> iterator, String separator) {
		// taken from apache commons lang
		// handle null, zero and one elements before building a buffer
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return String.valueOf(first);
		}
		// two or more elements
		StringBuffer buf = new StringBuffer(256); // Java default is 16,
		// probably too small
		if (first != null) {
			buf.append(first);
		}
		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	public static boolean isCurrentActivity(Activity activity) {
		if (activity == null || activity.isFinishing()) {
			return false;
		}
		Activity currentActivity = activity;
		if (currentActivity == activity) {
			return true;
		} else {
			return false;
		}
	}

	/***/
	private static final String SALT = "wxcvbnmlkjhgfdsq123654789";
	/***/
	private static final String IV = "0123456789abcdef";

	/**
	 * 
	 * @param encryptMode
	 * @param data
	 * @param key
	 * @return
	 */
	private static byte[] cryptAes(int encryptMode, byte[] data, String key) {
		String PBE_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";
		String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
		byte[] salt = SALT.getBytes();
		byte[] iv = IV.getBytes();
		int NUM_OF_ITERATIONS = 1000;
		int KEY_SIZE = 256;
		try {
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(), salt, NUM_OF_ITERATIONS, KEY_SIZE);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM);
			SecretKey tempKey = keyFactory.generateSecret(pbeKeySpec);
			SecretKey secretKey = new SecretKeySpec(tempKey.getEncoded(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			Cipher encCipher = Cipher.getInstance(CIPHER_ALGORITHM);
			encCipher.init(encryptMode, secretKey, ivSpec);
			byte[] encryptedText = encCipher.doFinal(data);

			return encryptedText;
		} catch (Exception e) {
			Log.e("TOOLBOX", "Error encryption", e);
		}
		return null;
	}

	private static final String KEY = "M_CASINO";

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptAES(String data) {
		try {
			byte[] result = cryptAes(Cipher.ENCRYPT_MODE, data.getBytes(), KEY);
			return Base64.encodeBytes(result);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptAES(String data) {
		try {
			byte[] result = cryptAes(Cipher.DECRYPT_MODE, Base64.decode(data.getBytes()), KEY);
			return new String(result);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isNetworkAvailable(Context context) {
		boolean isNetworkAvailable = false;
		if (context != null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivityManager != null) {
				NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
				if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnected()) {
					isNetworkAvailable = true;
				}
			}
		}

		return isNetworkAvailable;
	}

	public static boolean getBooleanFromString(String toConvert, boolean defaultValue) {
		if (toConvert == null) {
			return defaultValue;
		}
		toConvert = toConvert.toLowerCase();
		return (!"false".equals(toConvert) && !"0".equals(toConvert));
	}

	public static boolean isTelephoneNumber(String tel) {
		String regExpTelInter = "^\\+[0-9]{2}([1-9](\\d{2}?){3}\\d{2})$";
		String regExpTelFrance = "^0([0-9]{9})$";
		if (tel.trim().length() == 12) {
			if (Pattern.matches(regExpTelInter, tel)) {
				return true;
			} else {
				return false;
			}

		} else if (tel.trim().length() == 10) {
			if (Pattern.matches(regExpTelFrance, tel)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public static Map<String, JSONObject> getJSONObjectMapFromJsonArray(JSONArray items) {
		Map<String, JSONObject> itemsList = new HashMap<String, JSONObject>();
		for (int i = 0; i < items.length(); i++) {
			JSONObject item = items.optJSONObject(i);
			String id;
			if (item != null) {
				//id = item.optString(ApplicationConstants.SHORTITEM_ID);
				//if (ToolBox.isNotNullOrEmpty(id)) {
				//	itemsList.put(id, item);
				//}
			}
		}
		return itemsList;
	}

	public static String UrlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// UTF-8 is mandatory and will always works.
		}
		return s;
	}

	public static boolean isUrlMC9Wall(String url) {
		boolean result = false;
		Uri uri = Uri.parse(url);
		/*if (ApplicationConstants.URL_WALL_HOST.equals(uri.getHost())
				&& ApplicationConstants.URL_WALL_SCHEME.equals(uri.getScheme())
				&& ApplicationConstants.URL_WALL_PATH.equals(uri.getPathSegments().get(0))) {
			result = true;
		}*/

		return result;
	}

	/**
	 * Convert a business object to a json object
	 * 
	 * @param businessObject
	 * @return The json object, null if there's an error
	 */
//	public static <BusinessObjectClass> JSONObject convertBusinessObjectToJSONObject(BusinessObjectClass businessObject) {
//		try {
//			String serializedJson = JsonUtils.serializeJson(businessObject);
//			return new JSONObject(serializedJson);
//		} catch (JSONException e) {
//			Log.e(TAG, "Object malformed");
//		} catch (ITException e) {
//			Log.e(TAG, "Error during JSON serialization", e);
//		} catch (Exception e) {
//			Log.e(TAG, "Unknown exception during business object conversion to json object", e);
//		}
//		return null;
//	}

	/***
	 * Luhn Check (retrieved from old class LuhnCheck)
	 */
	public static String getDigitsOnly(String s) {
		StringBuffer digitsOnly = new StringBuffer();
		char c;
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (Character.isDigit(c)) {
				digitsOnly.append(c);
			}
		}
		return digitsOnly.toString();
	}

	public static boolean isValid(String cardNumber) {
		// Luhn check
		String digitsOnly = getDigitsOnly(cardNumber);
		int sum = 0;
		int digit = 0;
		int addend = 0;
		boolean timesTwo = false;

		for (int i = digitsOnly.length() - 1; i >= 0; i--) {
			digit = Integer.parseInt(digitsOnly.substring(i, i + 1));
			if (timesTwo) {
				addend = digit * 2;
				if (addend > 9) {
					addend -= 9;
				}
			} else {
				addend = digit;
			}
			sum += addend;
			timesTwo = !timesTwo;
		}
		int modulus = sum % 10;
		return modulus == 0;
	}

	/**
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need
	 *            to convert into pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on
	 *         device density
	 */
	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

	public static Spanned getFormattedPrice(Double price) {
		int euros = price.intValue();
		int centimes = (int) ((price - (double) euros) * 100);
		return Html.fromHtml(String.format("<small>- </small>%1$s<small>,%2$02d€</small>", euros, centimes));
	}

	public static String getFormattedDateFromReversedDate(String reversedDate){
		String year = reversedDate.substring(0,4);
		String monthOfYear = reversedDate.substring(4,6);
		String dayOfMonth = reversedDate.substring(6,8);

		return (dayOfMonth+"/"+monthOfYear+"/"+year);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void setBackground(View v, Drawable d){
		if (android.os.Build.VERSION.SDK_INT >= 16){
			v.setBackground(d);
		}else{
			v.setBackgroundDrawable(d);
		}
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	/***
	 * Return a Point. <br />
	 * Point.x = width of the screen in pixels.
	 * Point.y = height of the screen in pixels.
	 * @param context
	 * @return point
	 */
	public static Point getScreenSize(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		if (android.os.Build.VERSION.SDK_INT >= 13){
			display.getSize(size);
		}else{
			int width = display.getWidth();
			int height = display.getHeight();
			size.x = width;
			size.y = height;
		}
		return size;
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public static void setImageViewAlpha(ImageView im, float alpha){
		if (android.os.Build.VERSION.SDK_INT >= 11){
			im.setAlpha(alpha);
		}else{
			im.setAlpha((int)(alpha * 255));
		}
	}
}
