package fr.edm.activity;


import android.os.Bundle;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.ClassementTopTenDesMythosFragment;

public class ClassementTopTenDesMythosActivity extends EdmFragmentActivity {
	
	
	/**
	 * @author pvezinet
	 *
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		showFragment(new ClassementTopTenDesMythosFragment());
	}
	


}
