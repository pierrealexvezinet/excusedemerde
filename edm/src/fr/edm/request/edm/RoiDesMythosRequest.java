package fr.edm.request.edm;

import fr.edm.model.ListRoiDesMythos;
import fr.edm.parent.request.EdmSpiceRequest;




public class RoiDesMythosRequest extends EdmSpiceRequest<ListRoiDesMythos> {
	
	String numRequest;

	public RoiDesMythosRequest(String numRequest) {
		super(ListRoiDesMythos.class);
		this.numRequest = numRequest;
	}


	@Override
	public ListRoiDesMythos loadDataFromNetwork() throws Exception {
					
		this.mURL = String.format("http://excuse-de-merde.fr/ws/EdmWebServices.php?NUM_REQUEST="+numRequest);
		return super.loadDataFromNetwork();
	}
	
	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * @return
	 */
	public String getCacheKey() {
		return "roiDesMythosRequest." + this.numRequest;
	}
	


}
