package fr.edm.activity;

import android.os.Bundle;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.ShoppingFragment;

public class ShoppingActivity extends EdmFragmentActivity {
	
	/**
	 * @author pvezinet
	 *
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		showFragment(new ShoppingFragment());
	}

}
