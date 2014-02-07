package fr.edm.webservice;

import android.app.Activity;
import fr.edm.json.JsonHelper;
import fr.edm.ui.TopTenWebServiceInterface;
import fr.edm.utils.ApplicationConstants;

public class TopTenService implements TopTenWebServiceInterface{
	
	public TopTenService(){
		super();
	}

	@Override
	public void getNombreVotesByNumEdm(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
		
	}

	@Override
	public void getNombreVotesByEdm(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
		
	}

	@Override
	public void voterPourUneEdm(Activity activity, JsonHelper json) {
		json.execute(ApplicationConstants.URI_WS);
		
	}



}
