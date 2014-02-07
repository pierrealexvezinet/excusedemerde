package fr.edm.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filterable;
import android.widget.TextView;
import fr.edm.db.DbOpenHelper;
import fr.edm.db.SQLQueries;
import fr.edm.exception.AutoCompleteNotAvailableException;

public class AutocompleteCursorAdapter extends CursorAdapter implements Filterable {
	
	private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    public AutocompleteCursorAdapter(Context context, Cursor c) {
        super(context, c, true);
        mContext = context;
    }

   

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((TextView) view).setText(cursor.getString(0));
    }

    @Override
    public String convertToString(Cursor cursor) {
        return cursor.getString(0);
    }

    @Override
    public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
    	if (getFilterQueryProvider() != null) { return getFilterQueryProvider().runQuery(constraint); }
    	
    	Cursor cursor = null;
		try { 
			cursor = SQLQueries.getInstance().getByName(DbOpenHelper.getInstance(mContext), constraint);
		} catch (AutoCompleteNotAvailableException e) {
			Log.e(TAG, "Erreur lors de la recherche de mots clefs",e);
		}
		return cursor;
    }



	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}



}
