package fr.edm.model;

public class UserConnexion {

	private String loginUserConnexion;
	private String passwordConnexionUser;

	public UserConnexion() {
		super();
	}

	public UserConnexion(String loginUserConnexion, String passwordConnexionUser) {
		super();
		this.loginUserConnexion = loginUserConnexion;
		this.passwordConnexionUser = passwordConnexionUser;
	}

	public String getLoginUserConnexion() {
		return loginUserConnexion;
	}

	public void setLoginUserConnexion(String loginUserConnexion) {
		this.loginUserConnexion = loginUserConnexion;
	}

	public String getPasswordConnexionUser() {
		return passwordConnexionUser;
	}

	public void setPasswordConnexionUser(String passwordConnexionUser) {
		this.passwordConnexionUser = passwordConnexionUser;
	}

}
