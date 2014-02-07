package fr.edm.activity.parent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public interface IEdmActivity {
	
	/**
	 * @author pvezinet
	 *
	 */
	
	void onCreate(Bundle savedInstanceState);
	void onCreate(Bundle savedInstanceState, int layout);
	boolean onCreateOptionsMenu(Menu menu);
	boolean onOptionsItemSelected(MenuItem item);
	void onStart();
	void onPause();
	void onStop();
	void onResume();
	void onActivityResult(int requestCode, int resultCode, Intent data);
	void showWait(boolean show, long key);
}
