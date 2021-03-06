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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.ListRoiDesMythos;
import fr.edm.model.RoiDesMythos;
import fr.edm.request.edm.RoiDesMythosRequest;
import fr.edm.utils.ApplicationConstants;
import fr.edm.webservice.UserService;
import com.itelios.itframework.image.*;

public class RoiDesMythosFragment extends EdmFragment implements OnClickListener {
	
	TextView tvGrandGagnant; //contain pseudo + nb votes
	ImageView imvGrandGagnant;
	Button btAfficherProfil; 
	UserService userService = new UserService();
	RoiDesMythosRequest roiDesMythosRequest;
	ArrayList<RoiDesMythos> listRoiDesMythos = new ArrayList<RoiDesMythos>();

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.roi_des_mythos, container, false);
		
		tvGrandGagnant = (TextView) v.findViewById(R.id.tv_grand_gagnant);
		imvGrandGagnant = (ImageView) v.findViewById(R.id.imgv_roi_des_mythos_photo);
		btAfficherProfil = (Button) v.findViewById(R.id.bt_roi_des_mythos_afficher_profil);
		
		btAfficherProfil.setOnClickListener(this);
		//imageLoader = EdmApplication.getImageLoader();
		
		
		
		EdmApplication.showWaitingDialog(getActivity());
		
		roiDesMythosRequest = new RoiDesMythosRequest(ApplicationConstants.GET_ROI_DES_MYTHOS_USER);
  		
		  ((EdmFragmentActivity) getActivity()).getSpiceManager()
			.execute(roiDesMythosRequest, roiDesMythosRequest.getCacheKey(), 
					ApplicationConstants.ONE_HOUR_EXPIRE_CACHE_DATA, new RoiDesMythosRequestListener());
	
		return v;
	}
	
	
	 public final class  RoiDesMythosRequestListener implements RequestListener<ListRoiDesMythos> {
			

			@Override
			public void onRequestFailure(SpiceException spiceException) {	
				if (spiceException instanceof NoNetworkException) {
					Log.d("dede", "failure request for EdmsUserRequestListener " + spiceException.getCause());
				}
				else if (spiceException.getCause() instanceof HttpMessageNotReadableException){
					Log.d("dede", "failure request for EdmsUserRequestListener " + spiceException.getCause());
				}
			}

			@Override
			public void onRequestSuccess(ListRoiDesMythos result) {
				// TODO Auto-generated method stub
				
				ImageLoader imageLoader = new ImageLoader(getApplicationContext());
				tvGrandGagnant.setText(result.getListRoiDesMythos().get(0).getPseudoRoiDesMythos() + " avec " + result.getListRoiDesMythos().get(0).getNbVoteRoiDesMythos() + " vote(s) ");
				imageLoader.DisplayImage(result.getListRoiDesMythos().get(0).getUrlPhotoRoiDesMythos(), R.id.imgv_roi_des_mythos_photo, imvGrandGagnant);
		    	EdmApplication.unShowWaitingDialog();    
				    
			}
	    }
	 

	@Override
	public void onClick(View v) {
		
		if(v == btAfficherProfil){
			
		}
		
	}


}
