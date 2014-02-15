package fr.edm.fragment;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.json.JsonHelper;
import fr.edm.json.JsonHelper.JsonListener;
import fr.edm.model.RoiDesMythos;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.ImageLoader;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.UserService;

public class RoiDesMythosFragment extends EdmFragment implements OnClickListener {
	
	TextView tvGrandGagnant; //contain pseudo + nb votes
	ImageView imvGrandGagnant;
	Button btAfficherProfil; 
	UserService userService = new UserService();
	ArrayList<RoiDesMythos> listRoiDesMythos = new ArrayList<RoiDesMythos>();
	private static ArrayList<NameValuePair> restrictionRoiDesMythosUser = new ArrayList<NameValuePair>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.roi_des_mythos, container, false);
		
		tvGrandGagnant = (TextView) v.findViewById(R.id.tv_grand_gagnant);
		imvGrandGagnant = (ImageView) v.findViewById(R.id.imgv_roi_des_mythos_photo);
		btAfficherProfil = (Button) v.findViewById(R.id.bt_roi_des_mythos_afficher_profil);
		
		btAfficherProfil.setOnClickListener(this);
		
		
		
		EdmApplication.showWaitingDialogCancelableAndCloseActivity(getActivity());
		restrictionRoiDesMythosUser.add(new BasicNameValuePair(ApplicationConstants.NUM_REQUEST, ApplicationConstants.GET_ROI_DES_MYTHOS_USER));			
		
		
		if(PreferenceHelper.getRoiDesMythosInPreferences() != null){
			 tvGrandGagnant.setText(PreferenceHelper.getRoiDesMythosPseudoInPreferences()+ " avec " + PreferenceHelper.getRoiDesMythosnbVoteInPreferences() + " vote(s) ");
		     Bitmap bitmap = ImageLoader.DownloadImage(PreferenceHelper.getRoiDesMythosUrlPhotoUserInPreferences());
			 imvGrandGagnant.setImageBitmap(bitmap);
			 EdmApplication.unShowWaitingDialog();
		}else{
		
		userService.getUserRoiDesMythos(getActivity() ,new JsonHelper("post", ApplicationConstants.URI_WS, ApplicationConstants.GET_ROI_DES_MYTHOS_USER, restrictionRoiDesMythosUser,
				new JsonListener(){

					@Override
					public void onSuccess(JSONObject jsonObj) {
						
						JSONArray jsonArray = null;
						try {
							jsonArray = jsonObj.getJSONArray("list");
						
						
							JSONObject map = jsonArray.getJSONObject(0);
					
							RoiDesMythos roiDesMythos = new RoiDesMythos();
							
							roiDesMythos.setPseudoRoiDesMythos(map.getString("auteurEdm"));
							roiDesMythos.setUrlPhotoRoiDesMythos(map.getString("photo"));
							String nbVote = String.valueOf(map.getInt("nb_vote"));
							roiDesMythos.setNbVoteRoiDesMythos(nbVote);
							
							 tvGrandGagnant.setText(roiDesMythos.getPseudoRoiDesMythos()+ " avec " + roiDesMythos.getNbVoteRoiDesMythos() + " vote(s) ");
						     Bitmap bitmap = ImageLoader.DownloadImage(roiDesMythos.getUrlPhotoRoiDesMythos());
							 imvGrandGagnant.setImageBitmap(bitmap);
						PreferenceHelper.setRoiDesMythosInPreferences(roiDesMythos);
							
					
				
				} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.d("dede", "Exception levee : " + e.getMessage());
						}
						
						 EdmApplication.unShowWaitingDialog();
						
					}

					@Override
					public void onFailed(String msg) {
						// TODO Auto-generated method stub
						Log.d("dede", "Error on getting roi des mythos : " + msg);
						 EdmApplication.unShowWaitingDialog();
					}
			
		}));
	}
	
		
		return v;
	}

	@Override
	public void onClick(View v) {
		
		if(v == btAfficherProfil){
			
		}
		
	}

}
