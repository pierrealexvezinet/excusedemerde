package fr.edm.dialog;

import fr.activity.edm.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WaitingProgressBar extends Dialog {
	
	//private static final String TAG = WaitingProgressBar.class.getSimpleName();
		private Activity context;
		private OnCancelListener onCancelListener;
		
		public WaitingProgressBar(Activity context) {
			super(context, android.R.style.Theme_Translucent_NoTitleBar);
			this.context = context;
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.dialog_progress_bar);
			setCancelable(false);
			
			onCancelListener = new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					if (WaitingProgressBar.this.context != null && !WaitingProgressBar.this.context.isFinishing()) {
						WaitingProgressBar.this.context.finish();
					}
				}
			};
			
		}
		
		public void setCancelableWithOnCancelListener() {
			this.setCancelable(true);
			this.setOnCancelListener(onCancelListener);
		}
		
		@Override
		public void show() {
//	 		if (EdmApplication.isCasinoShopping()) {
					TextView tv = (TextView) findViewById(R.id.progressbar_tv_patienter);
				//	tv.setTextColor(getContext().getResources().getColor(R.color.pink));
					
					LinearLayout lyt = (LinearLayout) findViewById(R.id.progressbar_lyt_main);
					//lyt.setBackgroundResource(R.drawable.basicborderselectedpink);
			//} else {
				//TextView tv = (TextView) findViewById(R.id.progressbar_tv_patienter);
				//tv.setTextColor(getContext().getResources().getColor(R.color.text_green));
				
				//LinearLayout lyt = (LinearLayout) findViewById(R.id.progressbar_lyt_main);
				//lyt.setBackgroundResource(R.drawable.basicborderselected);
			//}
			super.show();
			
		}

}
