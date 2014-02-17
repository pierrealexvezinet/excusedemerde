package fr.edm.fragment;


import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import fr.activity.edm.R;

import fr.edm.EdmApplication;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.adapter.PostEdmAdapter;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.Edm;
import fr.edm.model.EdmUser;
import fr.edm.model.ListEdms;
import fr.edm.model.PostEdm;
import fr.edm.model.User;
import fr.edm.request.edm.AllEdmsRequest;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.utils.ToolBox;
import fr.edm.webservice.EdmService;
import fr.edm.webservice.UserService;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class AccueilFragment extends EdmFragment {
	
	/**
	 * @author pvezinet
	 *
	 */
	
	Button btnToast;
	String contenuEdm  = null;
	Button btValiderEdm;
	
	UserService userServ = new UserService();
	EdmService edmService = new EdmService(); 
	StringBuilder sb = new StringBuilder();
	int listViewTouchAction;

	ArrayList<Edm> listUserEdmS_RS = new ArrayList<Edm>();
	public Edm mDetailSummaryProspectusCategoryResult;//rs
	AllEdmsRequest allEdmRequest = null;//rs
	public static User user = new User();
	public static Edm edm = new Edm();
	public static String pseudo = "julien";
	ListView listViewEdm;
	public static PostEdmAdapter adapter = null;
	int cpt = 0;
	private static ArrayList<NameValuePair> restrictionLikerEdm = new ArrayList<NameValuePair>();
	private static ArrayList<NameValuePair> restrictionNbLikeByNumEdm = new ArrayList<NameValuePair>();
	

	@SuppressWarnings("deprecation")
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	
		// Toast.makeText(getApplicationContext(), "accueil User sauvegardé en preferences " + PreferenceHelper.getUserInPreferences().getPseudo(), Toast.LENGTH_LONG).show();
		   View v = inflater.inflate(R.layout.accueil_fragment, container, false);
		   listViewEdm = (ListView) v.findViewById(R.id.edm_listview);
		   btValiderEdm = (Button) v.findViewById(R.id.bt_valider_edm);
		  
		   
		   
		   EdmApplication.showWaitingDialog(getActivity());
		   
		   /*spice request for all edm in accueil activity*/
		  allEdmRequest = new AllEdmsRequest(ApplicationConstants.GET_ALL_EDMs);
		
	
		  
		  ((EdmFragmentActivity) getActivity()).getSpiceManager()
			.execute(allEdmRequest, allEdmRequest.getCacheKey(), 
					ApplicationConstants.ONE_MINUTE_EXPIRE_CACHE_DATA, new AccueilRequestListener() );
			
		  
			    listViewEdm.setScrollContainer(true);
			    ToolBox.setListViewScrollable(listViewEdm);
			  
		   
	        return v;
	    }
	   
		public void bindEdmDatas(ArrayList<Edm>  listEdm){
			adapter = new PostEdmAdapter(getApplicationContext(), R.layout.edm_textview_layout);
			for(Edm edm : listEdm){
			
			PostEdm postEdm;
			postEdm = new PostEdm();
			postEdm.setPost(edm.getContenu());
			postEdm.setDatePost(edm.getDatePost());
			postEdm.setHeurePost(edm.getHeurePost());
			postEdm.setAuteurPost(edm.getPseudo());
			
			
		     /**final OnClickListener likeEdm = new View.OnClickListener() {
			    public void onClick(View v) {
			    	
			    	if(v == btValiderEdm){
			       
			        	
			        	//GET_NB_LIKE_BY_NUM_EDM
			        
			        	restrictionNbLikeByNumEdm.add(new BasicNameValuePair(ApplicationConstants.NUM_REQUEST, ApplicationConstants.GET_NB_LIKE_BY_NUM_EDM));
			        	restrictionNbLikeByNumEdm.add(new BasicNameValuePair(ApplicationConstants.NUM_EDM, ));
			        	
			        	//nbVote , auteurVote,auteurEdm, numEdm, keyVote
			        	restrictionLikerEdm.add(new BasicNameValuePair(ApplicationConstants.NUM_REQUEST, ApplicationConstants.LIKER_EDM));
						restrictionLikerEdm.add(new BasicNameValuePair(ApplicationConstants.PSEUDO, edm.getPseudo() ));
						restrictionLikerEdm.add(new BasicNameValuePair(ApplicationConstants.VICTIME, edm.getVictime()));
						restrictionLikerEdm.add(new BasicNameValuePair(ApplicationConstants.CATEGORIE,edm.getCategorie()));
						restrictionLikerEdm.add(new BasicNameValuePair(ApplicationConstants.DATE_POST,edm.getDatePost()));
			        
			        	
			        	Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
			        }
						
			      
			    	
			    }
			};
			
			 btValiderEdm.setOnClickListener(likeEdm);**/
			
		
			adapter.add(postEdm);
			}
	
			listViewEdm.setAdapter(adapter);
		} 
		
		public static void updateArrayAdapterEdm(ArrayList<Edm> itemsArrayList, ArrayAdapter<PostEdm> mAdapter ) {

		    mAdapter.clear(); 

		    if (itemsArrayList != null){
		    	
		    	itemsArrayList = PreferenceHelper.getListAllEdm();
	
		        for (Edm edm : itemsArrayList) {
		        	
		        	PostEdm postEdm = new PostEdm();
		        	postEdm.setAuteurPost(edm.getPseudo());
		        	postEdm.setDatePost(edm.getDatePost());
		        	postEdm.setHeurePost(edm.getHeurePost());
		        	postEdm.setPost(edm.getContenu());
		        
		        	mAdapter.insert(postEdm, mAdapter.getCount());
		        }
		    }

		    mAdapter.notifyDataSetChanged();

		}
		
		
		public static void updateArrayAdapterEdmUser(ArrayList<EdmUser> itemsArrayList, ArrayAdapter<PostEdm> mAdapter ) {

		    mAdapter.clear(); 

		    if (itemsArrayList != null){
		    	
		    	itemsArrayList = PreferenceHelper.getListUserEdm();
	
		        for (EdmUser edmUser : itemsArrayList) {
		        	
		        	PostEdm postEdm = new PostEdm();
		        	postEdm.setAuteurPost(edmUser.getPseudo());
		        	postEdm.setDatePost(edmUser.getDatePost());
		        	postEdm.setHeurePost(edmUser.getHeurePost());
		        	postEdm.setPost(edmUser.getContenu());
		        
		        	mAdapter.insert(postEdm, mAdapter.getCount());
		        }
		    }
		 
		    mAdapter.notifyDataSetChanged();

		}
		
	
		public final class  AccueilRequestListener implements RequestListener<ListEdms> {
		

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
		public void onRequestSuccess(ListEdms result) {
			// TODO Auto-generated method stub
		
			
			for(Edm edm : result.getListEdms()){
				listUserEdmS_RS.add(edm);
			}
			    PreferenceHelper.setListAllEdm(listUserEdmS_RS);
				bindEdmDatas(listUserEdmS_RS);
				EdmApplication.unShowWaitingDialog();
			
		}
    }


		
	
}
