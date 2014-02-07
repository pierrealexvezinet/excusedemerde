package fr.edm.request.edm;

import fr.edm.model.ListEdms;
import fr.edm.parent.request.EdmSpiceRequest;


public class AllEdmsRequest extends EdmSpiceRequest<ListEdms> {
	
	String numRequest;


	public AllEdmsRequest(String numRequest) {
		super(ListEdms.class);
		this.numRequest = numRequest;
	}


	@Override
	public ListEdms loadDataFromNetwork() throws Exception {
		this.mURL = String.format("http://excuse-de-merde.fr/ws/EdmWebServices.php?NUM_REQUEST="+numRequest);
		return super.loadDataFromNetwork();
	}
	
	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * @return
	 */
	public String getCacheKey() {
		return "AllEdmsRequest." + this.numRequest;
	}
	


}
