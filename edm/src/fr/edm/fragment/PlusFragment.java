package fr.edm.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.activity.AccountActivity;
import fr.edm.activity.AccueilActivity;
import fr.edm.activity.ClassementTopTenDesMythosActivity;
import fr.edm.activity.LoginActivity;
import fr.edm.activity.MesEdmsActivity;
import fr.edm.activity.RoiDesMythosActivity;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.fragment.parent.EdmFragment;
import fr.edm.model.User;
import fr.edm.utils.PreferenceHelper;
import fr.edm.webservice.UserService;

public class PlusFragment extends EdmFragment implements OnClickListener {

	/*
	 * MON COMPTE LUMINOSITE MES EDMS TOP TEN ROI DES MYTHOS DECONNEXION
	 */

	Button tvMonCompte, tvForum, tvBoutique, tvLuminosite, tvDeconnexion;
	UserService userService = new UserService();

	Intent intent = null;
	SharedPreferences preferences = null;
	 SharedPreferences.Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());

		View v = inflater.inflate(R.layout.plus_fragment, container,
				false);

		tvMonCompte = (Button) v.findViewById(R.id.bt_mon_compte);
		tvForum = (Button) v.findViewById(R.id.bt_forum);
		tvBoutique = (Button) v.findViewById(R.id.bt_boutique);
		tvLuminosite = (Button) v.findViewById(R.id.bt_luminosite);
		tvDeconnexion = (Button) v.findViewById(R.id.bt_deconnexion);

		tvMonCompte.setOnClickListener(this);
		tvForum.setOnClickListener(this);
		tvBoutique.setOnClickListener(this);
		tvLuminosite.setOnClickListener(this);
		tvDeconnexion.setOnClickListener(this);
		
	 User g = PreferenceHelper.getUserInPreferences();
	 

		if (PreferenceHelper.getUserInPreferences() != null) {
			tvDeconnexion.setOnClickListener(this);
			
		} else {
			tvDeconnexion.setVisibility(View.GONE);
		}

		return v;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v == tvMonCompte) {

			EdmApplication.showWaitingDialog(getActivity());
			if (PreferenceHelper.getUserInPreferences() != null) {
				intent = new Intent(getActivity(), AccountActivity.class);
				getActivity().finish();
			} else {
				getActivity().finish();
				intent = new Intent(getActivity(), LoginActivity.class);
			}
			startActivity(intent);
			EdmApplication.unShowWaitingDialog();

		}
		if (v == tvLuminosite) {

		}

			if (v == tvDeconnexion) {

				EdmApplication.showWaitingDialog(getActivity());
				userService.deconnectUser();
				
				editor = preferences.edit();
				editor.putString("loginUser",null);
				editor.putString("mdpUser",null);
			    editor.commit();
				getActivity().finish();
				intent = new Intent(getActivity(), AccueilActivity.class);
				startActivity(intent);
				EdmApplication.unShowWaitingDialog();

			}
		

	}

	public void displayDialogMessageRetourAccueil(int title, int message,
			int buttonResource) {

		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(buttonResource,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								if (which == DialogInterface.BUTTON_POSITIVE) {
									dialog.cancel();
									getActivity().finish();
									intent = new Intent(getActivity(),
											AccueilActivity.class);
									startActivity(intent);
								}
							}
						}).create();
		alertDialog.show();

	}

}
