package fr.edm.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.zip.GZIPInputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.AndroidException;
import android.util.Log;

import fr.edm.utils.ToolBox;
import fr.edm.exception.AutoCompleteNotAvailableException;
import fr.edm.net.EdmCache;

public class DbOpenHelper extends SQLiteOpenHelper {
	
	/**
	 * @author pvezinet
	 *
	 */
	
	private static String TAG = DbOpenHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "products.db";

    private static final String DATABASE_ZIPED_NAME = "products.db.gz.MP3";

    public static final String PRODUCTS_TABLE_NAME = "products";

	public static final String PRODUCT_ID = "_id";

    private static final int DATABASE_VERSION = 1;
    
    private static DbOpenHelper instance = null;
    
    public static Context context;
    
    /** path to DB (External or Internal) */
    private String mydbpath;

    /**
     * Base constructor
     * @param context 
     * @throws IOException En cas d'impossibilité de charger la base
     */
	private DbOpenHelper(Context context) throws AutoCompleteNotAvailableException {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		
		// DB creation method
        this.createDataBase();
	}
	
	/**
	 * @param context le context Android
	 * @return l'instance de DBOpenHelper
	 * @throws IOException En cas d'impossibilité de charger la base
	 */
	public static DbOpenHelper getInstance(Context context) throws AutoCompleteNotAvailableException{
		if(instance == null){
			instance = new DbOpenHelper(context);
		}
		return instance;
	}
    
    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * @throws AndroidException En cas d'impossibilité de charger la base
     **/
    public void createDataBase() throws AutoCompleteNotAvailableException{
	     
    	// First we check if a db already exist
    	boolean dbExist = checkDataBase();
    	
    	// If not, we create it
    	if(!dbExist){
        	SQLiteDatabase db = null;

   	     	try{
   	     		// Create a void database
   	     		db = this.createSQLiteDatabase();

   	     		// Copy DB from assets
   	     		copyDbFromZipedAsset(DATABASE_ZIPED_NAME, db.getPath());
    	     } catch (IOException e) {
    	    	saveDBPath(""); //clean th db path if error occurred
 				throw new AutoCompleteNotAvailableException(e);
 			 } catch (SQLiteException e) {
     	    	saveDBPath(""); //clean th db path if error occurred
  				throw new AutoCompleteNotAvailableException(e);
 			 }finally{
				 if(db!=null){
					 db.close();
				 }
    	     }
    	}
    }

    /**
     * Copy the DB from the ziped Asset
     * @param databaseZipedName
     * @param location
     * @throws IOException
     */
	private void copyDbFromZipedAsset(String databaseZipedName, String location) throws IOException {
		InputStream is = null;
		GZIPInputStream zin = null;
		FileOutputStream fout = null;
		
		try{
			is = context.getAssets().open(databaseZipedName);
			zin = new GZIPInputStream(is);
			fout = new FileOutputStream(location);

			// Search for DB Base in ziped File
        	byte[] buffer = new byte[1024];
        	int length;
        	while ((length = zin.read(buffer))>0){
        		fout.write(buffer, 0, length);
        	}
			
		}finally{
   	    	 if(fout != null){
   	    		fout.flush();
   	    		fout.close();
   	    	 }
   	    	 if(zin != null){
   	    		zin.close(); 
   	    	 }
		}
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Not used at the moment
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Not used at the moment
	}
    
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
		Cursor cursor = null;
		boolean resultat = false;
    	try{
    		checkDB = this.getReadableDatabase();
			
    		if(checkDB != null){
    			cursor = checkDB.query("sqlite_master", 
    					new String[]{"name"}, 
    					"type = 'table' AND name LIKE '%products%'", 
    					 new String[0],
    					 null, 
    					 null,
    					 null);
    			
    			cursor.moveToFirst();
    			boolean hasNext = cursor.getCount()>0;
    			if (hasNext) {
    				resultat = true;
    			}
        		
    		}
       	}catch(SQLiteException e){
       		Log.w(TAG,"Impossible de charger la base actuellement");
       		//database does't exist yet.
       	}catch(NullPointerException e){
       		Log.w(TAG,"Impossible de charger la base actuellement");
       		//database does't exist yet.
       	}finally{
			if(cursor!=null){
				cursor.close();
			}
			if(checkDB!=null){
				checkDB.close();
			}
       	}
       	return resultat;
    }

    /**
     * Create a void DB on fileSystem
     * @return
     */
	public SQLiteDatabase createSQLiteDatabase() {
		
		// Check if a db path has already been set
		if(mydbpath == null){
			mydbpath = readDBPath(); 
		}
		
		// If not, we try to find the db path (SD or not)
		if(!ToolBox.isNotNullOrEmpty(mydbpath)){
	    	String state = Environment.getExternalStorageState();  
	    	if(Environment.MEDIA_MOUNTED.equals(state)){
				String extrenalPath = ToolBox.getExternalDir(context);
				saveDBPath(extrenalPath);
	    	}else{
				SQLiteDatabase database = super.getWritableDatabase();
				saveDBPath(database.getPath().substring(0,database.getPath().indexOf(DATABASE_NAME) - 1));
				database.close();
	    	}
		}
		
		// Common : 
		File dbdirectory = new File(mydbpath);
		if(!dbdirectory.exists()){
			dbdirectory.mkdirs();
		}
		String dbfile = dbdirectory.getPath() + File.separator + DATABASE_NAME;
	    
	    SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
		Log.d(TAG, "open DB default");
		return database;
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		if(mydbpath == null){
			throw new SQLiteException("Path to the DB File is null");
		}
		String dbfile = mydbpath + File.separator + DATABASE_NAME;
		SQLiteDatabase database = SQLiteDatabase.openDatabase(dbfile, null,SQLiteDatabase.NO_LOCALIZED_COLLATORS 
																			+ SQLiteDatabase.OPEN_READONLY);
		Log.d(TAG, "open DB read only");
		return database;
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		if(mydbpath == null){
			throw new SQLiteException("Path to the DB File is null");
		}
		String dbfile = mydbpath + File.separator + DATABASE_NAME;
		SQLiteDatabase database = SQLiteDatabase.openDatabase(dbfile, null,SQLiteDatabase.NO_LOCALIZED_COLLATORS 
																			+ SQLiteDatabase.OPEN_READWRITE);
		Log.d(TAG, "open DB readwrite");
		return database;
	}
	
	/**
	 * Read the path from the cache directory
	 * @return the db path
	 */
	public String readDBPath(){
		File cache = EdmCache.getCacheDir(context);
		File dbpathFile = new File(cache, "dbpath.json");
		StringBuilder dbpathStringBuilder = new StringBuilder();
		if (dbpathFile != null && dbpathFile.exists() && dbpathFile.isFile()) {
			BufferedReader reader = null;
		    try {
		    	reader = new BufferedReader(new FileReader(dbpathFile));
		        String line = null; 
		        while (( line = reader.readLine()) != null){
		        	dbpathStringBuilder.append(line);
		        }
		    } catch (FileNotFoundException e) {
		    	Log.e(TAG, "cache causes an error at startup",e);
		    	dbpathStringBuilder.setLength(0);
			} catch (IOException e) {
		    	Log.e(TAG, "cache causes an error at startup",e);
		    	dbpathStringBuilder.setLength(0);
			}
		    finally {
		    	if (reader != null){
		    		try {
						reader.close();
					} catch (IOException e) {}
		    	}
		    }
		}
		return dbpathStringBuilder.toString();
	}
	
	/**
	 * Save the path to FileSystem
	 * @param dbPath the path to save
	 */
	public void saveDBPath(String dbPath){
		if(ToolBox.isNotNullOrEmpty(dbPath)){
			this.mydbpath = dbPath;
			
			// Save on disk for next launch
			File cache = EdmCache.getCacheDir(context);
			File dbpathFile = new File(cache, "dbpath.json");
			if (dbpathFile != null && dbpathFile.exists() && dbpathFile.isFile()){
				dbpathFile.delete();
			}
			
			if (dbpathFile.isDirectory()){
				Log.e(TAG, "can't save dbpath file because a directory with the same name already exists");
			}else{
				//use buffering
			    Writer output = null;
			    try {
					dbpathFile.createNewFile();
			    	output = new BufferedWriter(new FileWriter(dbpathFile));
			    	
			    	//FileWriter always assumes default encoding is OK!
			    	output.write( dbPath );
			    } catch (IOException e) {
			    	Log.e(TAG, "cache causes an error at startup",e);
				}
			    finally {
			    	if(output != null){
					      try {
							output.close();
						} catch (IOException e) {
					    	Log.e(TAG, "cache causes an error at startup",e);
						}
			    	}
			    }
			}
		}
	}

}
