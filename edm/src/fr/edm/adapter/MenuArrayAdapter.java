package fr.edm.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.activity.edm.R;
import fr.edm.utils.ToolBox;

public class MenuArrayAdapter extends ArrayAdapter<String> implements OnItemClickListener {

	private Context mContext;
	public TypedArray mIcons, mIconsHover;
	private TextView mSeletedTextView;
	private ListView.OnItemClickListener mListener;
	private int mRowHeight = -1;

	private int mSelectedPosition = 0;

	public MenuArrayAdapter(Context context, int resource, String[] objects, ListView.OnItemClickListener listener) {
		super(context, resource, objects);
		this.mContext = context;
		//this.mIcons = mContext.getResources().obtainTypedArray(R.array.menu_icons);
		//this.mIconsHover = mContext.getResources().obtainTypedArray(R.array.menu_icons_hover);
		this.mListener = listener;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) super.getView(position, convertView, parent);
		view.setCompoundDrawablesWithIntrinsicBounds(mIcons.getResourceId(position, 0), 0, 0, 0);
		//view.setCompoundDrawablePadding((int) mContext.getResources().getDimension(R.di));
		//view.setTypeface(HelveticaAllCapsTextView.getFont(mContext));
		if (mRowHeight == -1){
			mRowHeight = (parent.getHeight() / this.getCount());
		}
		LayoutParams params = view.getLayoutParams();
		if (params == null) { 
		    params = new LayoutParams(LayoutParams.MATCH_PARENT, mRowHeight); 
		} else {
		    params.height = mRowHeight;
		}
		view.setLayoutParams(params);
		//view.setTextSize(mContext.getResources().getDimension(R.dimen.menu_text_size));
		CharSequence seq = view.getText();
		view.setText(seq.toString().toUpperCase());
		if (position == mSelectedPosition)
			select(view);
		return view;
	}

	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		deselect();
		mSelectedPosition = position;
		select((TextView)view);
		mListener.onItemClick(parent, view, position, id);
	}

	public void deselect(){
		if (mSeletedTextView != null){
			Resources res = mContext.getResources();
			//mSeletedTextView.setTextColor(res.getColor(R.color.casino_dark_grey));
			//Drawable d = res.getDrawable(R.drawable.list_selector_unfocused);
			//fr.edm.utils.ToolBox.setBackground(mSeletedTextView, d);
			mSeletedTextView.setCompoundDrawablesWithIntrinsicBounds(mIcons.getResourceId(mSelectedPosition, 0), 0, 0, 0);
			mSeletedTextView = null;
		}
	}

	private void select(TextView tv){
		Resources res = mContext.getResources();
		mSeletedTextView = tv;
		//mSeletedTextView.setTextColor(res.getColor(R.color.white));
		//Drawable d = res.getDrawable(R.drawable.list_selector_focused);
		mSeletedTextView.setCompoundDrawablesWithIntrinsicBounds(mIconsHover.getResourceId(mSelectedPosition, 0), 0, 0, 0);
		//fr.edm.utils.ToolBox.setBackground(mSeletedTextView, d);
	}

	public int getSelectedPosition() {
		return mSelectedPosition;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.mSelectedPosition = selectedPosition;
	}
}
