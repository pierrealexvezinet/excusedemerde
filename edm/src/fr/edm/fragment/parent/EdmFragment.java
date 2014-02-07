package fr.edm.fragment.parent;

import fr.edm.activity.parent.EdmFragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;

public class EdmFragment extends Fragment {

	public static final String TAG = EdmFragment.class.getSimpleName();
	private Context context = null;

	private boolean hideActionBar = false;
	public String mCapptainFragmentName = null;
	public Bundle mCapptainFragmentBundle = null;

	public static boolean isCasinoShopping = true;
	public static boolean isSAPActivated = true;
	/* sync related variables */
	static public long last_sync = 0;
	public static final long DAY = DateUtils.HOUR_IN_MILLIS;

	static public boolean compatible = false;


	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity.getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getActivity() != null) {
			context = getActivity().getApplicationContext();
			
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		startCapptainActivity();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		endCapptainActivity();
	}
	
	protected void startCapptainActivity(){
		if (mCapptainFragmentName != null){
			
		}
		//	CapptainAgent.getInstance(getActivity()).startActivity(getActivity(), mCapptainFragmentName, mCapptainFragmentBundle);
	}
	
	protected void endCapptainActivity() {
		if (mCapptainFragmentName != null){
			
		}
		//	CapptainAgent.getInstance(getActivity()).endActivity();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (getActivity() != null && hideActionBar && getActivity() instanceof EdmFragmentActivity){
			//((EdmFragmentActivity)getActivity()).showActionBar();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		/*MCasinoApplication app = (MCasinoApplication) getApplicationContext();

		boolean isValide = app.validateAppContext(getActivity());
		if (isValide) {
			app.setCurrentActivity(getActivity());
		}*/
		super.onActivityResult(requestCode, resultCode, data);
	}

	/*public CasinoActivityContext getCasinoActivityContext() {
		if (getActivity() == null) {
			return null;
		}
		if (!(getActivity() instanceof ICasinoActivity)) {
			throw new IllegalArgumentException("You cannot get context from an Activity that does not implements ICasinoActivity");
		}
		return ((ICasinoActivity) getActivity()).getCasinoActivityContext();
	}*/

	public Context getApplicationContext() {
		return context;

	}
	
	public boolean isHideActionBar() {
		return hideActionBar;
	}

	public void setHideActionBar(boolean hideActionBar) {
		this.hideActionBar = hideActionBar;
	}
}
