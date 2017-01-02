package fr.edm.activity;



import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.AccueilFragment;
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
