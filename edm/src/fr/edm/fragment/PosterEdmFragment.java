package fr.edm.fragment;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.AccueilActivity;
import fr.edm.activity.EditionProfilActivity;
import fr.edm.activity.MesEdmsActivity;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.json.JsonHelper;
import fr.edm.json.JsonHelper.JsonListener;
import fr.edm.model.Edm;
import fr.edm.model.EdmUser;
import fr.edm.model.PostEdm;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.utils.ToolBox;
import fr.edm.webservice.EdmService;

public class PosterEdmFragment extends EdmFragment implements OnClickListener{
	
	TextView tvPosterEdmPseudo;
	EditText etPostEdm;
	Spinner spPosterEdmType;
	Button btPosterEdm;
	EdmService edmService = new EdmService();
	Edm edm = new Edm();
	EdmUser edmUserPosted = new EdmUser();
	ArrayAdapter<CharSequence> adapter = null;
	Intent intent = null;
	String loginUser, mdpUser, isSafedUser = "";
	SharedPreferences preferences = null;
	
	private static ArrayList<NameValuePair> restrictionPosterEdm = new ArrayList<NameValuePair>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.poster_edm_fragment, container,
				false);
		
		tvPosterEdmPseudo = (TextView) v.findViewById(R.id.tv_pseudo_poster_edm);
		preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
		

		 String pseudoEdmPreferences = "";
		 loginUser = preferences.getString("loginUser", null);
		 mdpUser = preferences.getString("mdpUser", null);
		
		if(loginUser != null){
			pseudoEdmPreferences = loginUser;
			//PreferenceHelper.getUserInPreferences().setPseudo(pseudoEdmPreferences);
			PreferenceHelper.setPseudo(pseudoEdmPreferences);
		}else{
			pseudoEdmPreferences = PreferenceHelper.getUserInPreferences().getPseudo();
			PreferenceHelper.setPseudo(pseudoEdmPreferences);
		}

		
		tvPosterEdmPseudo.setText(pseudoEdmPreferences);
		etPostEdm = (EditText) v.findViewById(R.id.et_poster_edm);
		spPosterEdmType = (Spinner) v.findViewById(R.id.sp_poster_edm);
		

		 adapter=ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.array_type_edm, android.R.layout.simple_list_item_1);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spPosterEdmType.setAdapter(adapter);
		
		btPosterEdm = (Button) v.findViewById(R.id.bt_poster_edm);
		
		btPosterEdm.setOnClickListener(this);
		
		return v;
	}


	@Override
	public void onClick(View v) {
	
		if(v == btPosterEdm){
			//19-06-2013 � 21:21
			EdmApplication.showWaitingDialog(getActivity());
			
			Date currentDate = new Date();	
			
			@SuppressWarnings("deprecation")
			String datePost = String.valueOf(currentDate.getHours()) + ":" + String.valueOf(currentDate.getMinutes());
			@SuppressWarnings("deprecation")
			String heurePost = String.valueOf(currentDate.getDay() + "-" + currentDate.getMonth() + "-" + currentDate.getYear());
			
			
			
			edm.setCategorie(spPosterEdmType.getSelectedItem().toString());
			edm.setPseudo(tvPosterEdmPseudo.getText().toString());
			edm.setContenu(etPostEdm.getText().toString());
			edm.setDatePost(datePost);
			edm.setHeurePost(heurePost);
			
			
			
			edmUserPosted.setCategorie(spPosterEdmType.getSelectedItem().toString());
			edmUserPosted.setPseudo(tvPosterEdmPseudo.getText().toString());
			edmUserPosted.setContenu(etPostEdm.getText().toString());
		    edmUserPosted.setDatePost(datePost);
		    edmUserPosted.setHeurePost(heurePost);
			
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.NUM_REQUEST, ApplicationConstants.CREATE_EDM));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.PSEUDO, edm.getPseudo() ));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.VICTIME, edm.getVictime()));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.CATEGORIE,edm.getCategorie()));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.DATE_POST,edm.getDatePost()));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.HEURE_POST,edm.getHeurePost()));
			restrictionPosterEdm.add(new BasicNameValuePair(ApplicationConstants.SAISIR_EDM, edm.getContenu()));
			JsonHelper.TYPE_JSON_RESULT = "object";
			edmService.createEdm(getActivity(), new JsonHelper("post", ApplicationConstants.URI_WS, ApplicationConstants.CREATE_EDM, restrictionPosterEdm,
					new JsonListener(){

						@Override
						public void onSuccess(JSONObject jsonObj) {
							
						ArrayList<Edm> aledm = new ArrayList<Edm>();
						aledm =  PreferenceHelper.getListAllEdm();
						ArrayList<EdmUser> aledmUser = new ArrayList<EdmUser>();
						aledmUser = PreferenceHelper.getListUserEdm();
						
						
						
						   //PreferenceHelper.getListAllEdm().add(0, edm);
						   //PreferenceHelper.getListUserEdm().add(0, edmUserPosted);
						   
						   PreferenceHelper.setCountEdmsUser(1);
							Toast.makeText(getActivity(), "Edm post�e avec succ�s ! ", Toast.LENGTH_LONG).show();
							Log.d("tete ",
									"Edm post�e avec succ�s");
							
						
							
							AccueilFragment.updateArrayAdapterEdm(PreferenceHelper.getListAllEdm(), AccueilFragment.adapter);
							AccueilFragment.updateArrayAdapterEdmUser(PreferenceHelper.getListUserEdm(), AccueilFragment.adapter);
						
							EdmApplication.unShowWaitingDialog();
							intent = new Intent(getApplicationContext(), MesEdmsActivity.class);
							
							startActivity(intent);
							getActivity().finish();
		
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
