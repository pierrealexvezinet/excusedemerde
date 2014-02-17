package fr.edm.request.edm;

import fr.edm.model.ListEdms;
import fr.edm.model.ListUsers;
import fr.edm.model.User;
import fr.edm.parent.request.EdmSpiceRequest;

public class LoginUserRequest extends EdmSpiceRequest<ListUsers> {
	
	String numRequest;
	String loginUser;
	String mdpUser;


	public LoginUserRequest(String numRequest, String loginUser, String mdpUser) {
		super(ListUsers.class);
		this.numRequest = numRequest;
		this.loginUser = loginUser;
		this.mdpUser = mdpUser;
	}


	@Override
	public ListUsers loadDataFromNetwork() throws Exception {
								  
		this.mURL = String.format("http://excuse-de-merde.fr/ws/EdmWebServices.php?NUM_REQUEST="+numRequest+"&&pseudo="+loginUser+"&&mdp="+mdpUser);
		return super.loadDataFromNetwork();
	}
	
	/**
	 * This method generates a unique cache key for this request. In this case
	 * our cache key depends just on the keyword.
	 * @return
	 */
	public String getCacheKey() {
		return "LoginUserRequest." + this.numRequest;
	}
	


}
