package fr.edm.net;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.ResponseCache;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import com.google.android.filecache.FileResponseCache;


public class EdmCache extends FileResponseCache {
	//Cache request: stock une reponse http dans un fichier
	//Cache response: lit dans un fichier les entetes http et le body

    private static final String TAG = EdmCache.class.getSimpleName();

    public static void install(Context context) {
        ResponseCache responseCache = ResponseCache.getDefault();
        if (responseCache instanceof EdmCache) {
            Log.d(TAG, "Cache has already been installed.");
        } else if (responseCache == null) {
        	EdmCache dropCache = new EdmCache(context);
            ResponseCache.setDefault(dropCache);
        } else {
            Class<? extends ResponseCache> type = responseCache.getClass();
            Log.e(TAG, "Another ResponseCache has already been installed: " + type);
        }
    }

    public static File getCacheDir(Context context) {
        File dir = context.getCacheDir();
        dir = new File(dir, "filecache");
        return dir;
    }
    
    public static void clearCache(Context context){
    	File cacheDir = getCacheDir(context);
    	//clear SD cache
        File[] files = cacheDir.listFiles();
        if (files != null)
        {
        	for(File f:files)
        		f.delete();
        }
    }

    private final Context mContext;

    public EdmCache(Context context) {
        mContext = context;
    }
    


    @Override
    protected File getFile(URI uri, String requestMethod, Map<String, List<String>> requestHeaders,
            Object cookie) {
        try {
            File parent = getCacheDir(mContext);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(String.valueOf(uri).getBytes("UTF-8"));
            byte[] output = digest.digest();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < output.length; i++) {
                builder.append(Integer.toHexString(0xFF & output[i]));
            }
            String filename = builder.toString();
            return new File(parent, filename);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /** Sometimes i need to read directly the cache.
     * @param uri
     * @return
     */
    public File getFile(URI uri) {
    	return getFile(uri, null, null, null);
    }
}
