package org.nissan.internalApp.model;
/*********
 * User Authentication Model - This model will be used to Map the POST request from the user 
 * 							   as a JSON Contract.
 * 								
 * */
public class UserAuthentication {

	// Variable Declarations
	
	private String zid;
	private String password;
	private String fcm_token;
	
	// Constructors
	
	public UserAuthentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserAuthentication(String zid, String password, String fcm_token) {
		super();
		this.zid = zid;
		this.password = password;
		this.fcm_token = fcm_token;
	}
	
	//Getters & Setters
	public String getZid() {
		return zid;
	}
	public void setZid(String zid) {
		this.zid = zid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFcm_token() {
		return fcm_token;
	}
	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}
	@Override
	public String toString() {
		return "UserAuthentication [zid=" + zid + ", password=" + password + ", fcm_token=" + fcm_token + "]";
	}
}
