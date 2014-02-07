package fr.edm.request.edm;

import fr.edm.model.ListEdmsUser;
import fr.edm.parent.request.EdmSpiceRequest;

public class UserEdmsRequest extends EdmSpiceRequest<ListEdmsUser> {
	
	String numRequest;
	String pseudoUser;


	public UserEdmsRequest(String numRequest, String pseudoUser) {
		super(ListEdmsUser.class);
		this.numRequest = numRequest;
		this.pseudoUser = pseudoUser;
	}


	@Override
	public ListEdmsUser loadDataFromNetwork() throws Exception {
					
		this.mURL = String.format("http://excuse-de-merde.fr/ws/EdmWebServices.php?NUM_REQUEST="+numRequest+"&&pseudo="+pseudoUser);
		return super.loadDataFromNetwork();
	}
	
	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * @return
	 */
	public String getCacheKey() {
		return "userEdmsRequest." + this.numRequest;
	}
	


}
