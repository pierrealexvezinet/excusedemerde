package fr.edm.activity;

import com.octo.android.robospice.SpiceManager;

import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.AccueilFragment;
import fr.edm.json.JsonSpiceService;
import android.os.Bundle;


public class AccueilActivity extends EdmFragmentActivity {
	/**
	 * @author pvezinet
	 *
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		showFragment(new AccueilFragment());
	}

	


	
	
}
