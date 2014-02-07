package fr.edm.webservice;


import fr.edm.json.JsonHelper;
import fr.edm.utils.ApplicationConstants;
import android.app.Activity;
import fr.edm.ui.LikerEdmWebServiceInterface;

public class LikeService implements LikerEdmWebServiceInterface {
	
	public LikeService(){
		super();
	}

	@Override
	public void getNombreLikesByNumEdm(Activity activity,JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void getNombreLikesByEdmS(Activity activity,JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

	@Override
	public void likerEdm(Activity activity,JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
	}

}
