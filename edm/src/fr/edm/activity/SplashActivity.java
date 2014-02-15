package fr.edm.activity;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import fr.activity.edm.R;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.model.ListEdmsUser;
import fr.edm.model.ListUsers;
import fr.edm.model.User;
import fr.edm.request.edm.LoginUserRequest;
import fr.edm.request.edm.UserEdmsRequest;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.EdmSpiceService;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends Activity
{
 private static final int STOPSPLASH = 0;
 LoginUserRequest loginUserRequest;
 UserEdmsRequest userEdmRequest;
 String loginUser, mdpUser, isSafedUser = "";
 SharedPreferences preferences = null;
 SpiceManager spiceManager = null;

    /**
     * Default duration for the splash screen (milliseconds)
     */
 private static final long SPLASHTIME = 3000;

    /**
     * Handler to close this activity and to start automatically {@link MainActivity}
     * after <code>SPLASHTIME</code> seconds.
     */
 private final transient Handler splashHandler = new Handler()
    {
        @Override
 public void handleMessage(Message msg)
        {
 if (msg.what == STOPSPLASH)
            {
	 finish();
 final Intent intent = new Intent(SplashActivity.this, AccueilActivity.class);
                startActivity(intent);
               
            }

 super.handleMessage(msg);
        }
    };

    @Override
 protected void onCreate(Bundle savedInstanceState)
    {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.splash_edm);
 
   spiceManager = new SpiceManager(EdmSpiceService.class);
   preferences = PreferenceManager.getDefaultSharedPreferences(this);
 
 if(isSafedConnectionUser()) {
		loginUserRequest = new LoginUserRequest(ApplicationConstants.LOGIN_USER, loginUser, mdpUser);
		
		spiceManager
			.execute(loginUserRequest, loginUserRequest.getCacheKey(), 
					ApplicationConstants.NEVER_EXPIRE_CACHE_DATA, new LoginRequestListener() );
		
		
		userEdmRequest = new UserEdmsRequest(ApplicationConstants.GET_USER_EDMs, loginUser);
  		
		spiceManager
			.execute(userEdmRequest, userEdmRequest.getCacheKey(), 
					ApplicationConstants.NEVER_EXPIRE_CACHE_DATA, new UserEdmsRequestListener());
		
	}


 final Message msg = new Message();
        msg.what = STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);
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

		if((result.getListUsers()!= null)){
		
	       PreferenceHelper.setUserInPreferences(result.getListUsers().get(0));
	       //Toast.makeText(getApplicationContext(), "splash User sauvegardé en preference " + result.getListUsers().get(0).getDescription(), Toast.LENGTH_LONG).show();
		}else{
			 Toast.makeText(getApplicationContext(), "splash User non sauvegardé en preferences ", Toast.LENGTH_LONG).show();
		 
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
			        PreferenceHelper.setListUserEdm(result.getListEdmsUser());    
			       
			}
	    }
	

    
}