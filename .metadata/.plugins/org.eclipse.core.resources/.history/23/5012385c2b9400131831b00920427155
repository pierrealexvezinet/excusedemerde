package fr.edm.fragment;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.adapter.ClassementUserInTopTenAdapter;
import fr.edm.adapter.PostEdmAdapter;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.json.JsonHelper;
import fr.edm.json.JsonHelper.JsonListener;
import fr.edm.model.ClassementUsersInTopTen;
import fr.edm.model.Edm;
import fr.edm.model.PostEdm;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.utils.ToolBox;
import fr.edm.webservice.UserService;

public class ClassementTopTenDesMythosFragment extends EdmFragment{
	
	
	UserService userService = new UserService();
	ArrayList<ClassementUsersInTopTen> listClassementUserInTopTen = new ArrayList<ClassementUsersInTopTen>();
	
	private static ArrayList<NameValuePair> restrictionClassementUserInTopTen = new ArrayList<NameValuePair>();
	public static ClassementUsersInTopTen classementUserInTopTen = new ClassementUsersInTopTen();
	ListView listViewClassement = null;
	ClassementUserInTopTenAdapter adapter = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.classement_top_ten_des_mythos_fragment, container, false);
		
		listViewClassement = (ListView) v.findViewById(R.id.top_ten_des_mythos_listview);
		
		
		
		EdmApplication.showWaitingDialogCancelableAndCloseActivity(getActivity());
		restrictionClassementUserInTopTen.add(new BasicNameValuePair(ApplicationConstants.NUM_REQUEST, ApplicationConstants.GET_CLASSEMENT_TOP_TEN));			
		
		if(PreferenceHelper.getListClassementUserInTopTen() != null){
			adapter = new ClassementUserInTopTenAdapter(getApplicationContext(), R.layout.edm_textview_classement_top_ten_des_mythos_layout);
			listViewClassement.setAdapter(adapter);
		    bindEdmDatas(PreferenceHelper.getListClassementUserInTopTen());
		    listViewClassement.setScrollContainer(true);
		    ToolBox.setListViewScrollable(listViewClassement);
		    EdmApplication.unShowWaitingDialog();
    
		}else{
		
			JsonHelper.TYPE_JSON_RESULT = "list";
			
		userService.getNbVoteByUserInTopTenForClassement(getActivity(), new JsonHelper("post", ApplicationConstants.URI_WS, ApplicationConstants.GET_CLASSEMENT_TOP_TEN, restrictionClassementUserInTopTen,
				new JsonListener(){

					@Override
					public void onSuccess(JSONObject jsonObj) {
						
						JSONArray jsonArray = null;
						try {

							jsonArray = jsonObj.getJSONArray("list");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject map = jsonArray.getJSONObject(i);
						
								ClassementUsersInTopTen classementInTopTen = new ClassementUsersInTopTen();

								classementInTopTen.setPseudoUserInTopTen(map.getString("auteurEdm"));
								classementInTopTen.setUrlPhotoUserInTopTen(map.getString("photo"));
								String countVotesByUser = String.valueOf(map.getInt("nbVoteByUserInTopTen"));
								classementInTopTen.setNbVoteByUserInTopTen(countVotesByUser);
				

								listClassementUserInTopTen.add(classementInTopTen);
								
								Log.d("dede", "Call getNbVoteByUserInTopTenForClassement()");
								Log.d("dede", ApplicationConstants.URI_WS
										+ "?pseudo=" + classementInTopTen.getPseudoUserInTopTen() + "&"
										+ ApplicationConstants.NUM_REQUEST
										+ "="
										+ ApplicationConstants.GET_CLASSEMENT_TOP_TEN
										+ " : " + map.toString());
							}
							
							PreferenceHelper.setListClassementUserInTopTen(listClassementUserInTopTen);

						    bindEdmDatas(listClassementUserInTopTen);
						    listViewClassement.setScrollContainer(true);
						    ToolBox.setListViewScrollable(listViewClassement);
						  
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.d("dede",
									"error on call json services for getNbVoteByUserInTopTenForClassement : "
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
		
	}
		
		
		
		return v;
	}
	
	
	void bindEdmDatas(ArrayList<ClassementUsersInTopTen> listClassementUserInTopTen){
		
		adapter = new ClassementUserInTopTenAdapter(getApplicationContext(), R.layout.edm_textview_classement_top_ten_des_mythos_layout);
		for(ClassementUsersInTopTen classementUserInTopTen : listClassementUserInTopTen){
		
			ClassementUsersInTopTen classementUser;
			classementUser = new ClassementUsersInTopTen();
			classementUser.setUrlPhotoUserInTopTen(classementUserInTopTen.getUrlPhotoUserInTopTen());
			classementUser.setPseudoUserInTopTen(classementUserInTopTen.getPseudoUserInTopTen());
			classementUser.setNbVoteByUserInTopTen(classementUserInTopTen.getNbVoteByUserInTopTen());

		adapter.add(classementUser);
		}

		listViewClassement.setAdapter(adapter);
	} 



}
