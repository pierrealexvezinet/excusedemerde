package fr.edm.db;

import java.util.regex.Pattern;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import fr.edm.adapter.AutocompleteCursorAdapter;
import fr.edm.exception.AutoCompleteNotAvailableException;

public class SQLQueries {
	
	/**
	 * @author pvezinet
	 *
	 */
	
	
	/******************************************
	 * CONSTRUCTEURS
	 **************************************/
	
	/**
	 * Instance du SQListe Queries (singleton)
	 */
	private static SQLQueries instance;

	private SQLQueries(){
	}
	
	public static SQLQueries getInstance(){
		if(instance==null){
			instance = new SQLQueries();
		}
		return instance;
	}
	
	/******************************************
	 * MÃ©thodes d'action sur la base
	 **************************************/
	
	/**
	 * RÃ©cupÃ¨re l'ensemble des produit
	 * @param provider le ContentResolver de l'application
	 * @return le curseur des produits de l'application
	 * @throws AutoCompleteNotAvailableException 
	 * @see AutocompleteCursorAdapter
	 */
	public Cursor getFirsts(DbOpenHelper dbHelper) throws AutoCompleteNotAvailableException {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try{
			db = dbHelper.getReadableDatabase();
			
			cursor = db.query(DbOpenHelper.PRODUCTS_TABLE_NAME, 
					 new String[]{DbOpenHelper.PRODUCT_ID}, 
					 null, 
					 new String[0],
					 null, 
					 null,
					 null,
					 "10");
			
			cursor.getCount();
			
		} catch (SQLiteException e) {
			// On ne ferme base + curseur qu'en cas d'erreur, sinon le curseur ne peut Ãªtre lu par l'adapteur
			if(cursor!=null){
				cursor.close();
			}
			throw new AutoCompleteNotAvailableException(e);
		 }finally{
				if (db != null){
					db.close();
				}
			}
		
		return cursor;
	}

	/**
	 * RÃ©cupÃ¨re un produit par le nom
	 * @param provider le ContentResolver de l'application
	 * @param constraint le dÃ©but du nom Ã  chercher
	 * @return le curseur des mots clefs commensant par la contrainte.
	 * @throws AutoCompleteNotAvailableException 
	 */
	public Cursor getByName(DbOpenHelper dbHelper, CharSequence constraint) throws AutoCompleteNotAvailableException {
		//remove diacritic/accent
		String nfdNormalizedString = String.valueOf(constraint);
		nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã¨Ã©ÃªÃ«]","e");
		nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã»Ã¹]","u");
		nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã¯Ã®]","i");
		nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã Ã¢]","a");
		nfdNormalizedString = nfdNormalizedString.replaceAll("Ã”","o");

		nfdNormalizedString = nfdNormalizedString.replaceAll("[ÃˆÃ‰ÃŠÃ‹]","E");
	    nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã›Ã™]","U");
	    nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã�ÃŽ]","I");
	    nfdNormalizedString = nfdNormalizedString.replaceAll("[Ã€Ã‚]","A");
	    nfdNormalizedString = nfdNormalizedString.replaceAll("Ã”","O");
	    
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    String constraintWithoutAccent = pattern.matcher(nfdNormalizedString).replaceAll("");

		SQLiteDatabase db = null;
		Cursor cursor = null;
		try{
			db = dbHelper.getReadableDatabase();
			String clef = "%";
			if(constraintWithoutAccent!=null){
				clef = constraintWithoutAccent.toString().trim();
			}
			
			cursor = db.query(DbOpenHelper.PRODUCTS_TABLE_NAME, 
					 new String[]{DbOpenHelper.PRODUCT_ID}, 
					 DbOpenHelper.PRODUCT_ID+" like '"+clef+"%'", 
					 new String[0],
					 null, 
					 null,
					 null,
					 "10");
			
			cursor.getCount();
			
		} catch (SQLiteException e) {
			// On ne ferme base + curseur qu'en cas d'erreur, sinon le curseur ne peut Ãªtre lu par l'adapteur
			if (cursor != null){
				cursor.close();
			}
			throw new AutoCompleteNotAvailableException(e);
		}finally{
			if (db != null){
				db.close();
			}
		}
		
		return cursor;
	}
	
	/**
	 * Compte le nombre de mot clefs
	 * @param provider le ContentResolver de l'application
	 * @return le nombre de mots clefs
	 * @throws AutoCompleteNotAvailableException 
	 */
	public int count(DbOpenHelper dbHelper) throws AutoCompleteNotAvailableException {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		int count = 0;
		try{
			db = dbHelper.getReadableDatabase();
			
			cursor = db.query(DbOpenHelper.PRODUCTS_TABLE_NAME, 
					new String[]{"count(*) as totalcount"}, 
					 null, 
					 new String[0],
					 null, 
					 null,
					 null);
			
			cursor.moveToFirst();
			boolean hasNext = cursor.getCount()>0;
			if (hasNext) {
				count = cursor.getInt(0);
			}
		} catch (SQLiteException e) {
				throw new AutoCompleteNotAvailableException(e);
		 }finally{
			if(cursor!=null){
				cursor.close();
			}
			if(db!=null){
				db.close();
			}
		}
		
		
		return count;
	}

}
