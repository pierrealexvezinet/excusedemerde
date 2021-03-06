package fr.edm.fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.CacheLoadingException;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.AccueilActivity;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.adapter.PostEdmAdapter;
import fr.edm.fragment.MesEdmsFragment.UserEdmsRequestListener;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.EdmUser;
import fr.edm.model.ListEdmsUser;
import fr.edm.model.ListUsers;
import fr.edm.model.User;
import fr.edm.model.UserConnexion;
import fr.edm.request.edm.LoginUserRequest;
import fr.edm.request.edm.UserEdmsRequest;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.utils.ToolBox;
import fr.edm.webservice.EdmService;
import fr.edm.webservice.UserService;

public class LoginFragment extends EdmFragment implements OnClickListener {
	
	
	public LoginFragment(){
		
	}

	/**
	 * @author pvezinet
	 * 
	 */

	EditText etLogin;
	EditText etPassword;
	CheckBox cbRemindMe;
	Button btnConnexion;
	UserService userService = new UserService();
	EdmService edmService = new EdmService();
	Intent intent = null;
	LoginUserRequest loginUserRequest;
	UserEdmsRequest userEdmRequest;
	ArrayList<EdmUser> listUserEdmS_RS = new ArrayList<EdmUser>();

	
	ArrayList<EdmUser> listUserEdmS = new ArrayList<EdmUser>();
	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		editor = preferences.edit();
	
		View v = inflater.inflate(R.layout.login_fragment, container, false);

		etLogin = (EditText) v.findViewById(R.id.et_user_login);
		etPassword = (EditText) v.findViewById(R.id.et_user_mdp);
		cbRemindMe = (CheckBox) v.findViewById(R.id.cb_remind_me);
		btnConnexion = (Button) v.findViewById(R.id.bt_user_connexion);

		btnConnexion.setOnClickListener(this);
		btnConnexion.setOnClickListener(this);
		
		if(PreferenceHelper.getUserInPreferences() != null){
			cbRemindMe.setChecked(true);
			etLogin.setText(PreferenceHelper.getPseudo());
			etPassword.setText(PreferenceHelper.getMdp());
		}else{
			cbRemindMe.setChecked(false);
			etLogin.setText("");
			etPassword.setText("");
		}

		return v;
	}
	
	
	public  void getUserRequest(){
		 /*spice request for all edm in accueil activity*/
  		loginUserRequest = new LoginUserRequest(ApplicationConstants.LOGIN_USER, etLogin.getText().toString(), etPassword.getText().toString());
  
  		  ((EdmFragmentActivity) getActivity()).getSpiceManager()
  			.execute(loginUserRequest, loginUserRequest.getCacheKey(), 
  					ApplicationConstants.ONE_MINUTE_EXPIRE_CACHE_DATA, new LoginRequestListener() );
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnConnexion && ! etLogin.getText().toString().equals("")
				&& ! etPassword.getText().toString().equals("")) {
			
           EdmApplication.showWaitingDialog(getActivity());
           
           
           getUserRequest();
  		  
  	// ((EdmFragmentActivity) getActivity()).getSpiceManager().getFromCache(ListUsers.class, "LoginUserRequest"+ApplicationConstants.GET_USER, DurationInMillis.NEVER, null);
	
	
  	 
		}else{
			Toast.makeText(getActivity(), "Vous n'avez pas saisis vos identdifiants de connexion.", Toast.LENGTH_SHORT).show();
		}

	}

	public final class  LoginRequestListener implements RequestListener<ListUsers> {
	

	@Override
	public void onRequestFailure(SpiceException spiceException) {	
		if (spiceException instanceof NoNetworkException) {
			Log.d("dede", "failure request for LoginRequestListner " + spiceException.getCause());
		}
		else if (spiceException.getCause() instanceof HttpMessageNotReadableException){
			Log.d("dede", "failure request for LoginRequestListener " + spiceException.getCause());
		}
	}

	@Override
	public void onRequestSuccess(ListUsers result) {
		// TODO Auto-generated method stub

		if((result.getListUsers() == null)){
	        EdmApplication.unShowWaitingDialog();
			Toast.makeText(getActivity(), "Les identifiants saisis sont incorrects.", Toast.LENGTH_LONG).show();
		}else{
		
	    User user = new User();
		user = result.getListUsers().get(0);

		PreferenceHelper.setUserInPreferences(user);
		PreferenceHelper.setPseudo(user.getPseudo());
		PreferenceHelper.setMdp(user.getMdp());

		if(cbRemindMe.isChecked()){
			
			editor.putString("loginUser",user.getPseudo());
			editor.putString("mdpUser",user.getMdp());
			editor.putString("safeUser","ok");
		    editor.commit();
		    Toast.makeText(getActivity(), "Bienvenue "+ user.getPseudo(), Toast.LENGTH_SHORT).show();
		
		}else{
		
			editor.putString("loginUser",null);
			editor.putString("mdpUser",null);
			editor.putString("safeUser","no");
			editor.commit();  
		    
		}
		
		Toast.makeText(getActivity(), "Bienvenue "+ user.getPseudo(), Toast.LENGTH_SHORT).show();
		userEdmRequest = new UserEdmsRequest(ApplicationConstants.GET_USER_EDMs, user.getPseudo());
  		
		  ((EdmFragmentActivity) getActivity()).getSpiceManager()
			.execute(userEdmRequest, userEdmRequest.getCacheKey(), 
					ApplicationConstants.ONE_MINUTE_EXPIRE_CACHE_DATA, new UserEdmsRequestListener());
		
		 
		}
					

	}
	
	}
	

	 public final class  UserEdmsRequestListener implements RequestListener<ListEdmsUser> {
			

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
			public void onRequestSuccess(ListEdmsUser result) {
				// TODO Auto-generated method stub
			        
			        int nbEdmUser = 0;
			        
			        if(result.getListEdmsUser() != null){
			        	   nbEdmUser = result.getListEdmsUser().size();
			        	   PreferenceHelper.setListUserEdm(result.getListEdmsUser());
			        }
				    
				    getActivity().finish();
			     	intent = new Intent(getActivity(), AccueilActivity.class);
					startActivity(intent);
				    
					 if(result.getListEdmsUser() != null){
				    Toast.makeText(getActivity(), "Chargement de vos " + nbEdmUser + " EDMs ", Toast.LENGTH_LONG).show();
					 }else{
						 Toast.makeText(getActivity(), "Vous n'avez pas encore post� d'EDM ", Toast.LENGTH_LONG).show(); 
					 }

				
				    
				    
			}
	    }
	
	
	
	
}
