package fr.edm.ui;

import android.app.Activity;
import android.content.Context;
import fr.edm.json.JsonHelper;


public interface EdmWebServiceInterface {
	
	public void getUserEdmS(Activity activity, JsonHelper json);
	public void getEdmByNumEdm(Activity activity, JsonHelper json);
	public void getNbLikeByNumEdm(Activity activity, JsonHelper json);
	public void getNbLikeByNumEdmByContext(Context context, JsonHelper json);
	public void getAllEdmS(Activity activity, JsonHelper json);
	public void getlistEdmByCategorieEdm(Activity activity, JsonHelper json);
	public void getlistEdmByAuteurEdm(Activity activity, JsonHelper json);
	public void createEdm(Activity activity, JsonHelper json);
	public void deleteEdm(Activity activity, JsonHelper json);
	public void likerEdm(Activity activity, JsonHelper json);
	public void likerEdmByContext(Context context, JsonHelper json);
	public void hasUserVotedForEdm(Context context, JsonHelper json);
	

}
