package fr.edm.activity.parent;

import fr.activity.edm.R;
import java.util.ArrayList;
import com.octo.android.robospice.SpiceManager;
import fr.edm.EdmApplication;
import fr.edm.activity.*;
import fr.edm.adapter.EdmDrawerAdapter;
import fr.edm.fragment.AccueilFragment;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.EdmService;
import fr.edm.webservice.EdmSpiceService;
import fr.edm.webservice.UserService;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.DialogInterface.*;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ResourceAsColor")
public class EdmFragmentActivity extends ActionBarActivity {

	/**
	 * @author pvezinet
	 * 
	 */
	// /Users/Pierre-AlexandreVezinet/Documents/android-sdk-macosx/tools/emulator
	// -avd AVD_for_Nexus_7 -dns-server 8.8.8.8
	private DrawerLayout drawerLayout;
	ListView listViewDrawer;
	EdmDrawerAdapter adapter;

	SharedPreferences preferences = null;
   
	private static String ITEM_ACCUEIL = "Accueil";
	private static String ITEM_POSTER_EDM = "Poster une EDM";
	private static String ITEM_MES_EDMS = "Mes EDMs";
	private static String ITEM_TOP_TEN = "Top 10";
	private static String ITEM_ROI_DES_MYTHOS = "Roi des mythos";
	private static String ITEM_PIPOTEK = "Pipotek";
	private static String ITEM_PLUS = "Plus";

	private static int POSITION_ITEM_ACCUEIL = 0;
	private static int POSITION_ITEM_POSTER_EDM = 1;
	private static int POSITION_ITEM_MES_EDMS = 2;
	private static int POSITION_ITEM_TOP_TEN = 3;
	private static int POSITION_ITEM_ROI_DES_MYTHOS = 4;
	private static int POSITION_ITEM_PIPOTEK = 5;
	private static int POSITION_ITEM_PLUS = 5;


	protected static Activity EDM_ACTIVITY;
	private ArrayList<String> menu_item;
	private ActionBarDrawerToggle drawerToggle = null;
	// UserService userService;
	EdmService edmService = new EdmService();
	UserService userService = new UserService();
	Intent intent = null;
	String loginUser, mdpUser, isSafedUser = "";


	private SpiceManager spiceManager = new SpiceManager(EdmSpiceService.class);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("dede", "onCreate");
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		


		// CALL WS FOR GET USER INFO CONNECTED
		isOnline();
		setContentView(R.layout.edm_fragment_main_activity);
		showFragment(new AccueilFragment());

		menu_item = new ArrayList<String>();
		listViewDrawer = new ListView(getApplicationContext());

		menu_item.add(ITEM_ACCUEIL);
		menu_item.add(ITEM_POSTER_EDM);
		menu_item.add(ITEM_MES_EDMS);
		menu_item.add(ITEM_TOP_TEN);
		menu_item.add(ITEM_ROI_DES_MYTHOS);
		menu_item.add(ITEM_PIPOTEK);
		menu_item.add(ITEM_PLUS);

		listViewDrawer = (ListView) findViewById(R.id.left_drawer);

		adapter = new EdmDrawerAdapter(getApplicationContext(),
				R.layout.menu_drawer, menu_item);
		listViewDrawer.setAdapter(adapter);

		initItemsDrawer();

		listViewDrawer.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View item,
					int position, long arg) {

				selectedItem(item);

			}
		});

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_closed);
		drawerLayout.setDrawerListener(drawerToggle);
		drawerToggle.setDrawerIndicatorEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		drawerToggle.syncState();

	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		Log.d("dede", "onCreateOptionsMenu");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(final MenuItem item) {

		Log.d("dede",
				"onOptionsItemSelected ouvert item selectionné : "
						+ item.getTitle());

		if (android.R.id.home == item.getItemId()) {
			if (drawerLayout.isDrawerOpen(Gravity.LEFT) == false) {
				drawerLayout.openDrawer(Gravity.LEFT);
			} else {
				drawerLayout.closeDrawers();
			}
		} else if (R.id.refresh == item.getItemId()) {
			MenuItemCompat.setActionView(item, R.layout.progressbar);
		}

		return super.onOptionsItemSelected(item);
	}

	public boolean isCheckedSafeUserCase() {
		isSafedUser = preferences.getString("safeUser", null);
		if (isSafedUser.equals("ok")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSafedConnectionUser() {

		loginUser = preferences.getString("loginUser", null);
		mdpUser = preferences.getString("mdpUser", null);
		isSafedUser = preferences.getString("safeUser", null);
		// Toast.makeText(this, "login : " + loginUser + " / mdp : " + mdpUser ,
		// Toast.LENGTH_LONG).show();

		if ((loginUser != null || mdpUser != null)) {
			PreferenceHelper.setPseudo(loginUser);
			PreferenceHelper.setMdp(mdpUser);
			
			//User user = new User();
			//user.setPseudo(loginUser);
			//user.setMdp(mdpUser);
			//PreferenceHelper.setUserInPreferences(user);
			return true;
		} else {

			return false;
		}
	}

	private void initItemsDrawer() {

		/*
		 * listViewDrawer.getAdapter() .getView(POSITION_ITEM_ACCUEIL, null,
		 * listViewDrawer)
		 * .setBackgroundColor(fr.activity.edm.R.color.btn_accueil_color);
		 * listViewDrawer.getAdapter() .getView(POSITION_ITEM_POSTER_EDM, null,
		 * listViewDrawer)
		 * .setBackgroundColor(fr.activity.edm.R.color.btn_forum_color);
		 * listViewDrawer.getAdapter() .getView(POSITION_ITEM_SHOPPING, null,
		 * listViewDrawer)
		 * .setBackgroundColor(fr.activity.edm.R.color.btn_shopping_color);
		 * listViewDrawer.getAdapter() .getView(POSITION_ITEM_PIPOTEK, null,
		 * listViewDrawer)
		 * .setBackgroundColor(fr.activity.edm.R.color.btn_account_color);
		 * listViewDrawer.getAdapter() .getView(POSITION_ITEM_ACCOUNT, null,
		 * listViewDrawer)
		 * .setBackgroundColor(fr.activity.edm.R.color.btn_pipotek_color);
		 * listViewDrawer .getAdapter() .getView(POSITION_ITEM_PARAMETRES, null,
		 * listViewDrawer) .setBackgroundColor(
		 * fr.activity.edm.R.color.btn_parametres_color);
		 */

	}

	private void selectedItem(View view) {

		TextView v = (TextView) view;
		Intent intent = null;

		if (v.getText().equals(ITEM_ACCUEIL)) {
			Log.d("dede", "selected Item Name is " + v.getText());
			drawerLayout.closeDrawers();

			/*
			 * EdmApplication.NB_INSTANCE_EDM_FRAGMENT_ACTIVITY++;
			 * 
			 * if(EdmApplication.NB_INSTANCE_EDM_FRAGMENT_ACTIVITY > 1){
			 * finish(); }
			 */

			intent = new Intent(this, AccueilActivity.class);
			startActivity(intent);
		}
		if (v.getText().equals(ITEM_POSTER_EDM)) {
			Log.d("dede", "selected Item Name is " + v.getText());

			if (isSafedConnectionUser()) {
				intent = new Intent(this, PosterEdmActivity.class);
				startActivity(intent);
			} else {

				if (PreferenceHelper.getUserInPreferences() != null) {
					intent = new Intent(this, PosterEdmActivity.class);
					startActivity(intent);
				} else {

					displayDialogMessageRetourActivity(
							R.string.title_alert_dialog_retour_to_post_edm,
							R.string.message_pas_connecte,
							R.string.button_connect_me, new LoginActivity());
				}
			}

			drawerLayout.closeDrawers();

		}

		if (v.getText().equals(ITEM_MES_EDMS)) {
			Log.d("dede", "selected Item Name is " + v.getText());

			drawerLayout.closeDrawers();
			EdmApplication.showWaitingDialog(this);
			if (isSafedConnectionUser()) {
				intent = new Intent(this, MesEdmsActivity.class);
				startActivity(intent);
			} else if (!isSafedConnectionUser()) {

				if (PreferenceHelper.getUserInPreferences() != null) {
					intent = new Intent(this, MesEdmsActivity.class);
					startActivity(intent);
				} else {

					displayDialogMessageRetourActivity(
							R.string.title_alert_dialog_retour_accueil,
							R.string.message_aucunes_edms_utilisateur,
							R.string.button_valid_retour_accueil,
							new AccueilActivity());
				}
			} else {
				intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}

			EdmApplication.unShowWaitingDialog();
		}
		if (v.getText().equals(ITEM_TOP_TEN)) {
			Log.d("dede", "selected Item Name is " + v.getText());
			drawerLayout.closeDrawers();
			intent = new Intent(this, ClassementTopTenDesMythosActivity.class);
			startActivity(intent);
		}
		if (v.getText().equals(ITEM_ROI_DES_MYTHOS)) {
			Log.d("dede", "selected Item Name is " + v.getText());
			drawerLayout.closeDrawers();
			intent = new Intent(this, RoiDesMythosActivity.class);
			startActivity(intent);
		}
		if (v.getText().equals(ITEM_PIPOTEK)) {
			Log.d("dede", "selected Item Name is " + v.getText());
			drawerLayout.closeDrawers();
		}
		if (v.getText().equals(ITEM_PLUS)) {
			Log.d("dede", "selected Item Name is " + v.getText());
			isSafedConnectionUser();
			
			drawerLayout.closeDrawers();
			intent = new Intent(this, PlusActivity.class);
			startActivity(intent);

		}

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		spiceManager.start(this);
		Log.d("dede", "onstart");

	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d("dede", "onpause");

	}

	@Override
	public void onStop() {
		super.onStop();
		spiceManager.shouldStop();
		Log.d("dede", "onstop");

	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("dede", "onresume");

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

	}

	public void showFragment(final Fragment fragmenttoDisplay) {
		if (fragmenttoDisplay == null)
			return;

		final FragmentManager fm = getSupportFragmentManager();
		final FragmentTransaction ft = fm.beginTransaction();
		// We can also animate the changing of fragment
		// ft.setCustomAnimations(android.R.anim.slide_out_right,
		// android.R.anim.slide_out_right);
		ft.replace(R.id.content_frame, fragmenttoDisplay);
		ft.commit();
	}

	public boolean isOnline() {
		getApplicationContext();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			Log.d("dede", "online online online online online ONLINE");
			return true;
		}
		return false;
	}
	
	 public static  boolean isNetworkAvailable(Context context) {
		    if(context == null) { return false; }
		    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    // if no network is available networkInfo will be null, otherwise check if we are connected
		    try {
		        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
		            return true;
		        }
		    } catch (Exception e) {
		        Log.d("edm", "isNetworkAvailable()" + e.getMessage());
		    }
		    return false;
		}

	public void finishEdmApplication(int title, int message) {

		final AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setTitle(title)
				.setMessage(message)
				.setCancelable(true)
				.setOnCancelListener(new OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialog) {

					}
				})
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								if (which == DialogInterface.BUTTON_POSITIVE) {

									try {

										if (getParent() != null) {

											getParent().finish();
										} else {
											finish();
										}
									} catch (Exception e) {
										Log.d("dede", e.getMessage());
									}
								}
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();

							}
						}).create();
		alertDialog.show();

	}

	public void displayDialogMessageRetourActivity(int title, int message,
			int buttonResource, final EdmFragmentActivity activity) {

		final AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(buttonResource,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								if (which == DialogInterface.BUTTON_POSITIVE) {
									dialog.cancel();
									intent = new Intent(
											getApplicationContext(), activity
													.getClass());
									startActivity(intent);
								}
							}
						}).create();
		alertDialog.show();

	}

	public SpiceManager getSpiceManager() {
		return spiceManager;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getMdpUser() {
		return mdpUser;
	}

	public void setMdpUser(String mdpUser) {
		this.mdpUser = mdpUser;
	}

	public SharedPreferences getPreferences() {
		return preferences;
	}

	public void setPreferences(SharedPreferences preferences) {
		this.preferences = preferences;
	}
	
	


}
