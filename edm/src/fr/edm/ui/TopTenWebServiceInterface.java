package fr.edm.ui;

import android.app.Activity;
import fr.edm.json.JsonHelper;


public interface TopTenWebServiceInterface {

	public void getNombreVotesByNumEdm(Activity activity, JsonHelper json);
	public void getNombreVotesByEdm(Activity activity, JsonHelper json);
	public void voterPourUneEdm(Activity activity, JsonHelper json);
}
