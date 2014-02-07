package fr.edm.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.AccountActivity;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.json.JsonHelper;
import fr.edm.json.JsonHelper.JsonListener;
import fr.edm.model.User;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.UserService;

public class EditionProfilFragment extends EdmFragment implements
		OnClickListener {

	Intent intent = null;
	private static ArrayList<NameValuePair> restrictionMajUser = new ArrayList<NameValuePair>();
	private static ArrayList<NameValuePair> restrictionUser = new ArrayList<NameValuePair>();

	EditText etCivilite, etResidence, etStatut, etPays,
			etAPropos;
	DatePicker etDateDeNaissance;
	Button btValider;
	String jour, mois, annee;
	int year,month, day;
	UserService userService = new UserService();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.edition_profil_fragment, container,
				false);

		etCivilite = (EditText) v.findViewById(R.id.et_user_civilite);
		etDateDeNaissance = (DatePicker) v.findViewById(R.id.et_user_date_de_naissance);
		etResidence = (EditText) v.findViewById(R.id.et_user_ville);
		etStatut = (EditText) v.findViewById(R.id.et_user_statut);
		etPays = (EditText) v.findViewById(R.id.et_user_pays);
		etAPropos = (EditText) v.findViewById(R.id.et_user_a_propos);
		
		
		
		etCivilite.setText(PreferenceHelper.getUserInPreferences().getCivilite());
		if(PreferenceHelper.getUserInPreferences().getDateNaissance() != null){
	
			String dateNaissance = PreferenceHelper.getUserInPreferences().getDateNaissance();
			day = Integer.valueOf(dateNaissance.toString()
					.substring(0, 2));
		    month = Integer.valueOf(dateNaissance.toString()
					.substring(3, 5));
		    year = Integer.valueOf(dateNaissance.toString()
					.substring(6, 10));	
			etDateDeNaissance.init(year,month,day, null);
			
			
		}else{
			final Calendar c = Calendar.getInstance();
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			etDateDeNaissance.init(year,month,day, null);
		}
		etResidence.setText(PreferenceHelper.getUserInPreferences().getVille());
		etPays.setText(PreferenceHelper.getUserInPreferences().getPays());
		etAPropos.setText(PreferenceHelper.getUserInPreferences().getDescription());
		
		btValider = (Button) v.findViewById(R.id.bt_user_editer_profil);

		btValider.setOnClickListener(this);

		return v;
	}

	public boolean validiteChampsOk() {

		if (!etCivilite.getText().toString().equals("")
				&& !etResidence.getText().toString().equals("")
				&& !etStatut.getText().toString().equals("")
				&& !etPays.getText().toString().equals("")
				&& !etAPropos.getText().toString().equals("")) {
			return false;
		} else {
			Log.d("tete", "yeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v == btValider) {
			
			validiteChampsOk();
			EdmApplication.showWaitingDialog(getActivity());

			jour = String.valueOf(etDateDeNaissance.getDayOfMonth()).toString()
					.substring(0, 2);
		    mois = String.valueOf(etDateDeNaissance.getMonth()).toString()
					.substring(3, 5);
		    annee = String.valueOf(etDateDeNaissance.getYear()).toString()
					.substring(6, 10);
		
			Log.d("tete", "jour" + jour);
			Log.d("tete", "mois" + mois);
			Log.d("tete", "annee" + annee);

			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.NUM_REQUEST,
					ApplicationConstants.UPDATE_USER));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.PSEUDO, PreferenceHelper.getUserInPreferences().getPseudo()));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.CIVILITE, etCivilite.getText()
							.toString()));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.JOUR, jour));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.MOIS, mois));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.ANNEE, annee));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.VILLE, etResidence.getText()
							.toString()));
			restrictionMajUser
					.add(new BasicNameValuePair(ApplicationConstants.STATUT,
							etStatut.getText().toString()));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.PAYS, etPays.getText().toString()));
			restrictionMajUser.add(new BasicNameValuePair(
					ApplicationConstants.DESCRIPTION, etAPropos.getText()
							.toString()));

			userService.updateUser(getActivity(), new JsonHelper("post",
					ApplicationConstants.URI_WS,
					ApplicationConstants.UPDATE_USER, restrictionMajUser,
					new JsonListener() {

						@Override
						public void onSuccess(JSONObject jsonObj) {
							// TODO Auto-generated method stub
						
							 
					           
								restrictionUser.add(new BasicNameValuePair(
										ApplicationConstants.NUM_REQUEST,
										ApplicationConstants.GET_USER));
								restrictionUser.add(new BasicNameValuePair(
										ApplicationConstants.PSEUDO, PreferenceHelper.getUserInPreferences().getPseudo()));

								userService.getUser(getActivity(), new JsonHelper("post",
										ApplicationConstants.URI_WS,
										ApplicationConstants.GET_USER, restrictionUser,
										new JsonListener() {

											@Override
											public void onSuccess(JSONObject jsonObj) {
												// TODO Auto-generated method stub

												JSONArray jsonArray = null;
												try {
												   jsonArray = jsonObj.getJSONArray("list");
												
														JSONObject map = jsonArray.getJSONObject(0);
														
														User user = new User();
														user.setPseudo(map.getString("pseudo"));
														user.setNom(map.getString("nom"));
														user.setPrenom(map.getString("prenom"));
														user.setDateNaissance(map.getString("jourAnniv")
																+ "/" + map.getString("moisAnniv")
																+ "/" + map.getString("anneeAnniv"));
														user.setCacherprofil(map
																.getString("cacherprofil"));
														user.setCivilite(map.getString("civilite"));
														user.setCodePostal(map.getString("codePostal"));
														user.setExtension(map.getString("extension"));
														user.setFuseau(map.getString("fuseau"));
														user.setMail(map.getString("mail"));
														user.setPays(map.getString("pays"));
														user.setVille(map.getString("ville"));
														user.setPhoto(map.getString("photo"));
														user.setDescription(map.getString("description"));
													
														
														
														PreferenceHelper.setUserInPreferences(user);
														
														Toast.makeText(getActivity(), "Profil mis à jour avec succès !"+ user.getPseudo() , Toast.LENGTH_LONG).show();
														Log.d("dede",
																"MAJ réussie pour l'utilisateur : " + user.getPseudo());
												
														getActivity().finish();
														intent = new Intent(getActivity(), AccountActivity.class);
														startActivity(intent);
														
													      	
													
												}catch (JSONException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
													Toast.makeText(getActivity(), "MAJ non réussie \n Veuillez réessayer." , Toast.LENGTH_LONG).show();
													Log.d("dede",
															"error on call json services for getUser() : "
																	+ e.getMessage());
													
												}
												
												EdmApplication.unShowWaitingDialog();
											}

											@Override
											public void onFailed(String msg) {
												// TODO Auto-generated method stub
												EdmApplication.unShowWaitingDialog();
											}

										}));
								
		
							EdmApplication.unShowWaitingDialog();

						}

						@Override
						public void onFailed(String msg) {
							// TODO Auto-generated method stub
							EdmApplication.unShowWaitingDialog();
						}

					}));

		}

	}

}
