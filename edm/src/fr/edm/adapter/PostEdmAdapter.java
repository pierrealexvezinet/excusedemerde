package fr.edm.adapter;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.activity.edm.R;
import fr.edm.activity.parent.EdmFragmentActivity;
import fr.edm.json.JsonHelper;
import fr.edm.json.JsonHelper.JsonListener;
import fr.edm.model.PostEdm;
import fr.edm.model.User;
import fr.edm.utils.ApplicationConstants;
import fr.edm.webservice.EdmService;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class PostEdmAdapter extends ArrayAdapter<PostEdm> {

	private LayoutInflater mInflater = null;
	public PostEdm storage = new PostEdm();
	StorageEdmRelativeLayoutDatas layoutDatas;
	ArrayList<PostEdm> currentListPost = new ArrayList<PostEdm>();
	int resourceTV =0;
	PostEdm entity;
	EdmService edmService = new EdmService(); 
	int nbLikesForCurrentEdm;
	
	private static ArrayList<NameValuePair> restrictionGetNbLikeEdmByNumEdm = new ArrayList<NameValuePair>();
	private static ArrayList<NameValuePair> restrictionLikerEdm = new ArrayList<NameValuePair>();
	


	public PostEdmAdapter(Context mContext, int resourceTV) {

		super(mContext, 0);
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resourceTV = resourceTV;
	}

	@Override
	final public View getView(final int position, View convertView,
			ViewGroup parent) {
        
		
	    layoutDatas = new StorageEdmRelativeLayoutDatas();
		View row = convertView;
	

		if (row == null) {
			row = mInflater
					.inflate(this.resourceTV, parent, false);
			layoutDatas.postEdm = (TextView) row.findViewById(R.id.tv_edm_post);
			layoutDatas.datePost = (TextView) row.findViewById(R.id.tv_edm_post_date);
			layoutDatas.heurePost = (TextView) row.findViewById(R.id.tv_edm_post_hour);
			layoutDatas.auteur = (TextView) row.findViewById(R.id.tv_edm_post_auteur);
			layoutDatas.btValiderEdm = (Button) row.findViewById(R.id.bt_valider_edm);
			layoutDatas.nbLikeByEdm = (TextView) row.findViewById(R.id.tv_edm_nb_like);

			row.setTag(layoutDatas);
	
		} else {
			layoutDatas = (StorageEdmRelativeLayoutDatas) row.getTag();	
		}

		
		
		    entity = getItem(position);
			layoutDatas.postEdm.setText(entity.getPost());
			layoutDatas.datePost.setText("Post� le " + entity.getDatePost());
			layoutDatas.heurePost.setText(" � " + entity.getHeurePost() + " |");
			layoutDatas.auteur.setText(entity.getAuteurPost() + " | ");
			layoutDatas.btValiderEdm.setText("Je valide | ");
			layoutDatas.nbLikeByEdm.setText(entity.getNbLikesEdm() + " vote(s)");
			
			layoutDatas.btValiderEdm.setClickable(true);
			layoutDatas.btValiderEdm.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					//FIX BUG with myPosition  instead of final position variable because we get 
					//the next value on click validation button edm. Use myPosition variable instead of position.
					
					//nbVote , auteurVote,auteurEdm, numEdm, keyVote
					int myPosition = position;
					int nbLikeEdmToIncrement = Integer.valueOf(getItem(myPosition).getNbLikesEdm());
					nbLikeEdmToIncrement++;
					layoutDatas.nbLikeByEdm.setText(String.valueOf(nbLikeEdmToIncrement) + " vote(s)");
					layoutDatas.nbLikeByEdm.refreshDrawableState();
				    Toast.makeText(getContext(), "click " + myPosition + " nbLikeEdmToIncrement :  " + nbLikeEdmToIncrement + " heurepost " + getItem(myPosition).getHeurePost().toString() + "  numEdm " + getItem(myPosition).getNumEdm() , Toast.LENGTH_SHORT).show();
					myPosition = position;
				}
				
			});
			

		return row;
	}
	
	
	

	class StorageEdmRelativeLayoutDatas {

		TextView postEdm, datePost, heurePost, auteur, nbLikeByEdm;
		Button btValiderEdm;

	}

}
