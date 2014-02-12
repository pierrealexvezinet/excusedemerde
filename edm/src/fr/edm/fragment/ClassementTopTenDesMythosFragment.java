package fr.edm.fragment;

import java.util.ArrayList;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.adapter.ClassementUserInTopTenAdapter;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.ListUserInTopTen;
import fr.edm.model.UserInTopTen;
import fr.edm.request.edm.TopTenDesMythosRequest;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.UserService;

public class ClassementTopTenDesMythosFragment extends EdmFragment{
	
	
	UserService userService = new UserService();
	ArrayList<UserInTopTen> listClassementUserInTopTen = new ArrayList<UserInTopTen>();
	
	public static UserInTopTen classementUserInTopTen = new UserInTopTen();
	ListView listViewClassement = null;
	ClassementUserInTopTenAdapter adapter = null;
	TopTenDesMythosRequest topTenDesMythosRequest;
	ArrayList<UserInTopTen> listUserInTopTen_RS = new ArrayList<UserInTopTen>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.classement_top_ten_des_mythos_fragment, container, false);
		
		listViewClassement = (ListView) v.findViewById(R.id.top_ten_des_mythos_listview);
		
		
		
		EdmApplication.showWaitingDialog(getActivity());
		
		  /*spice request for all edm in accueil activity*/
		topTenDesMythosRequest = new TopTenDesMythosRequest(ApplicationConstants.GET_CLASSEMENT_TOP_TEN);
		
	
		  
		  ((EdmFragmentActivity) getActivity()).getSpiceManager()
			.execute(topTenDesMythosRequest, topTenDesMythosRequest.getCacheKey(), 
					ApplicationConstants.NEVER_EXPIRE_CACHE_DATA, new TopTenRequestListener() );
		

		
		
		return v;
	}
	
	
	void bindEdmDatas(ArrayList<UserInTopTen> listClassementUserInTopTen){
		
		adapter = new ClassementUserInTopTenAdapter(getApplicationContext(), R.layout.edm_textview_classement_top_ten_des_mythos_layout);
		for(UserInTopTen classementUserInTopTen : listClassementUserInTopTen){
		
			UserInTopTen classementUser;
			classementUser = new UserInTopTen();
			classementUser.setUrlPhotoUserInTopTen(classementUserInTopTen.getUrlPhotoUserInTopTen());
			classementUser.setPseudoUserInTopTen(classementUserInTopTen.getPseudoUserInTopTen());
			classementUser.setNbVoteByUserInTopTen(classementUserInTopTen.getNbVoteByUserInTopTen());

		adapter.add(classementUser);
		}

		listViewClassement.setAdapter(adapter);
	} 
	
	
	
	
	
	public final class  TopTenRequestListener implements RequestListener<ListUserInTopTen> {
		

		@Override
		public void onRequestFailure(SpiceException spiceException) {	
			if (spiceException instanceof NoNetworkException) {
				Log.d("dede", "failure request for AccueilRequestListner " + spiceException.getCause());
			}
			else if (spiceException.getCause() instanceof HttpMessageNotReadableException){
				Log.d("dede", "failure request for AccueilRequestListener " + spiceException.getCause());
			}
			
			EdmApplication.unShowWaitingDialog();
		}

		@Override
		public void onRequestSuccess(ListUserInTopTen result) {
			// TODO Auto-generated method stub
		
			
			for(UserInTopTen userInTopTen : result.getListTopTen()){
				listUserInTopTen_RS.add(userInTopTen);
			}
			    PreferenceHelper.setListClassementUserInTopTen(listClassementUserInTopTen);
				bindEdmDatas(listUserInTopTen_RS);
				
				EdmApplication.unShowWaitingDialog();
			
		}
    }



}
