package fr.edm.fragment;

import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.EditionProfilActivity;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.User;
import fr.edm.utils.PreferenceHelper;
import android.content.Intent;
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
import com.itelios.itframework.image.*;

public class AccountFragment extends EdmFragment implements OnClickListener {

	/**
	 * @author pvezinet
	 * 
	 */

	Button btnToast;
	ImageView imageUser;
	TextView tvResidence, tvResidenceResult;
	TextView tvCivilite, tvCiviliteResult;
	TextView tvDateDeNaissance, tvDateDeNaissanceResult;
	TextView tvVisitesProfil, tvVisitesProfilResult;
	TextView tvNbCommentaires, tvNbCommentairesResult;
	TextView tvNbEdms, tvNbEdmsResults;
	Button btEditerProfil;
	
	Intent intent = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.account_fragment, container, false);
		
		imageUser = (ImageView) v.findViewById(R.id.imgv_user_photo);

		tvResidence = (TextView) v.findViewById(R.id.tv_residence);
		tvResidenceResult = (TextView) v.findViewById(R.id.tv_residence_result);

		tvCivilite = (TextView) v.findViewById(R.id.tv_civilite);
		tvCiviliteResult = (TextView) v.findViewById(R.id.tv_civilite_result);

		tvDateDeNaissance = (TextView) v.findViewById(R.id.tv_date_naissance);
		tvDateDeNaissanceResult = (TextView) v
				.findViewById(R.id.tv_date_naissance_result);

		tvVisitesProfil = (TextView) v.findViewById(R.id.tv_visites_profil);
		tvVisitesProfilResult = (TextView) v
				.findViewById(R.id.tv_visites_profil_result);

		tvNbCommentaires = (TextView) v.findViewById(R.id.tv_nb_commentaires);
		tvNbCommentairesResult = (TextView) v
				.findViewById(R.id.tv_nb_commentaires_result);

		tvNbEdms = (TextView) v.findViewById(R.id.tv_nb_edm);
		tvNbEdmsResults = (TextView) v.findViewById(R.id.tv_nb_edm_result);

		btEditerProfil = (Button) v.findViewById(R.id.bt_user_edit_profil);
		btEditerProfil.setOnClickListener(this);
		
		populateInfosUser();

		return v;
	}

	public void populateInfosUser() {

		User user = new User();
		user = PreferenceHelper.getUserInPreferences();
		String photoUser = user.getPhoto();
		Log.d("tete", "photo url" +  user.getPhoto());
		if(user.getPhoto() != null){
		// Bitmap bitmap = ImageLoader.DownloadImage(user.getPhoto());
	        //imageUser.setImageBitmap(bitmap);
	        
	        ImageLoader imageLoader = new ImageLoader(getApplicationContext());
			
			imageLoader.DisplayImage(photoUser, R.id.imgv_user_photo, imageUser);
	    	EdmApplication.unShowWaitingDialog();    
	        
	        
		}

		Log.d("tete", "ville "+user.getVille() );
        
		if(PreferenceHelper.getUserInPreferences().getVille() == null){
		tvResidenceResult.setText("Aucun(e)");
		}else{
			tvResidenceResult.setText(user.getVille());
		}
		tvCiviliteResult.setText(user.getCivilite());
		Log.d("tete", "date de naissance :" + user.getDateNaissance());
		if(user.getDateNaissance() == null){
			tvDateDeNaissanceResult.setText("Aucun(e)");
		}else{
			tvDateDeNaissanceResult.setText(user.getDateNaissance());
		}
		
		tvVisitesProfilResult.setText("");
		tvNbCommentairesResult.setText("");
		tvNbEdmsResults.setText(nbEdmsUser());
		nbEdmsUser();

	}

	public String nbEdmsUser() {
		
		String nbEdmUser = "0";
		
		if(PreferenceHelper.listUserEdm != null){
		 nbEdmUser = String.valueOf(PreferenceHelper.getListUserEdm().size());
		}
		
		return nbEdmUser;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btEditerProfil) {
			intent = new Intent(getApplicationContext(), EditionProfilActivity.class);
			getActivity().finish();
			startActivity(intent);
		}
	}

}
