package fr.edm.webservice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.json.JsonHelper;
import fr.edm.model.User;
import fr.edm.ui.UserWebServiceInterface;
import fr.edm.utils.ApplicationConstants;
import fr.edm.utils.PreferenceHelper;

public class UserService implements UserWebServiceInterface {

	/**
	 * @author pvezinet
	 * 
	 */
	
	SharedPreferences preferences = null;

	public UserService() {
		super();
	}

	@Override
	public void subscriptionEdm(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void loginUser(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);

	}

	@Override
	public void deconnectUser() {
		PreferenceHelper.userConnexion = null;
		PreferenceHelper.user = null;
		PreferenceHelper.setListUserEdm(null);
		PreferenceHelper.listUserEdm = null;
		PreferenceHelper.pseudo = null;
		PreferenceHelper.mdp = null;
		
	}

	@Override
	public void getUser(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void createUser(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void updateUser(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void deleteUser(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void getNbVoteByUserInTopTenForClassement(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);

	}

	@Override
	public void getUserRoiDesMythos(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

}
