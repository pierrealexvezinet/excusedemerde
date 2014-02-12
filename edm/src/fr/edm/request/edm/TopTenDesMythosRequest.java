package fr.edm.request.edm;

import fr.edm.model.ListUserInTopTen;
import fr.edm.parent.request.EdmSpiceRequest;

public class TopTenDesMythosRequest extends EdmSpiceRequest<ListUserInTopTen> {

	String numRequest;

	public TopTenDesMythosRequest(String numRequest) {
		super(ListUserInTopTen.class);
		this.numRequest = numRequest;
	}

	@Override
	public ListUserInTopTen loadDataFromNetwork() throws Exception {
		this.mURL = String
				.format("http://excuse-de-merde.fr/ws/EdmWebServices.php?NUM_REQUEST="
						+ numRequest);
		return super.loadDataFromNetwork();
	}

	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * 
	 * @return
	 */
	public String getCacheKey() {
		return "TopTenDesMythosRequest." + this.numRequest;
	}

}
