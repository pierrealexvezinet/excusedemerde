package fr.edm.ui;

import android.app.Activity;
import fr.edm.json.JsonHelper;

public interface UserWebServiceInterface {
	
	
	public void subscriptionEdm(Activity activity, JsonHelper json);
	public void loginUser(Activity activity, JsonHelper json);
	public void deconnectUser();
	public void getUser(Activity activity, JsonHelper json);
	public void createUser(Activity activity, JsonHelper json);
	public void updateUser(Activity activity, JsonHelper json);
	public void deleteUser(Activity activity, JsonHelper json);
	public void getNbVoteByUserInTopTenForClassement(Activity activity, JsonHelper json);
	public void getUserRoiDesMythos(Activity activity, JsonHelper json);
	


}
