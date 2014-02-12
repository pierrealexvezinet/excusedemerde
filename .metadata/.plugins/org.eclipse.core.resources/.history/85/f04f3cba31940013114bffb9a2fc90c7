package fr.edm.adapter;


import java.util.ArrayList;

import com.itelios.itframework.ITApplication;

import fr.activity.edm.R;
import fr.edm.EdmApplication;
import fr.edm.model.UserInTopTen;
import fr.edm.utils.ImageLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassementUserInTopTenAdapter extends ArrayAdapter<UserInTopTen>{
	
	private LayoutInflater mInflater = null;
	public UserInTopTen storage = new UserInTopTen();
	StorageClassementUserInTopTenRelativeLayoutDatas layoutDatas;
	ArrayList<UserInTopTen> currentListClassementUsers = new ArrayList<UserInTopTen>();
	int resource =0;
	

	public ClassementUserInTopTenAdapter(Context mContext, int resource) {
		super(mContext, 0);
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resource = resource;
	}
	
	@Override
	final public View getView(final int position, View convertView,
			ViewGroup parent) {
        
		
	    layoutDatas = new StorageClassementUserInTopTenRelativeLayoutDatas();
		View row = convertView;

		if (row == null) {
			row = mInflater
					.inflate(this.resource, parent, false);
			layoutDatas.userImage = (ImageView) row.findViewById(R.id.imgv_user_photo_top_ten_des_mythos);
			layoutDatas.pseudoUser = (TextView) row.findViewById(R.id.tv_pseudo_classement);
			layoutDatas.countVote = (TextView) row.findViewById(R.id.tv_count_nb_vote);

			row.setTag(layoutDatas);
		} else {
			layoutDatas = (StorageClassementUserInTopTenRelativeLayoutDatas) row.getTag();
		}

		UserInTopTen entity = getItem(position);

        ITApplication.getImageLoader().displayImage(entity.getUrlPhotoUserInTopTen(),layoutDatas.userImage);
		// Bitmap bitmap = ImageLoader.DownloadImage(entity.getUrlPhotoUserInTopTen());
   
		
			//layoutDatas.userImage.setImageBitmap(bitmap);
			layoutDatas.pseudoUser.setText(entity.getPseudoUserInTopTen());
			layoutDatas.countVote.setText(entity.getNbVoteByUserInTopTen() + " vote(s)");

		return row;
	}
	

	class StorageClassementUserInTopTenRelativeLayoutDatas {

		ImageView userImage;
		TextView pseudoUser;
		TextView countVote;

	}

}
