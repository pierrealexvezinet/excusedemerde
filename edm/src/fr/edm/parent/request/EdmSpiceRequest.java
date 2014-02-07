package fr.edm.parent.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import fr.edm.retry.EdmRetryPolicy;

/**
 * A Spice Request that is able to throw a CasinoWSError.<br/>
 * If a WS return a EdmWSError, we don't retry. Else we retry 3 times.
 * @author pavezinet
 * @param <RESULT>
 */


public class EdmSpiceRequest<RESULT> extends SpringAndroidSpiceRequest<RESULT> {
	protected String mURL;
	static int count = 0;

	public EdmSpiceRequest(Class<RESULT> clazz) {
		super(clazz);
		this.setRetryPolicy(new EdmRetryPolicy());
	}

	@Override
	public RESULT loadDataFromNetwork() throws Exception {
		// TODO Auto-generated method stub
		ResponseEntity<RESULT> responseEntity = null;
		responseEntity = getRestTemplate().getForEntity(mURL, getResultType());
		Log.d("ccc", "response : " + responseEntity.getBody().toString());

		if (responseEntity != null){
			HttpHeaders headers = responseEntity.getHeaders();
			String headerString = headers.getFirst("X-MC2Error");
			if( headerString != null ){
				int code = Integer.valueOf(headerString);
				//throw new CasinoWsError(code, "");
			}else{
				return responseEntity.getBody();
			}
		}
		return null;
	
	}
	


	
		
	


}
