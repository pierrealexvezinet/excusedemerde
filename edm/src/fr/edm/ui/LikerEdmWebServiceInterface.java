package fr.edm.ui;

import android.app.Activity;
import fr.edm.json.JsonHelper;


public interface LikerEdmWebServiceInterface {
	
	public void getNombreLikesByNumEdm(Activity activity, JsonHelper json);
	public void getNombreLikesByEdmS(Activity activity, JsonHelper json);
	public void likerEdm(Activity activity, JsonHelper json);


}
