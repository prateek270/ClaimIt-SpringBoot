package org.nissan.internalApp.model;

/**********************************
 * Firebase User Model - 
 * 		Will store the data related to the user Firebase Communications.
 * 		Variables-
 * 			1.) zid 	  - Primary Key in the database table
 * 			2.) fcm_token -	FCM Token of the device if the end user
 ***********************************/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "firebaseusermodel")
public class FirebaseUserModel {

	@Id
	@Column(name = "zid")
	private String zid;

	@Column(name = "fcm_token")
	private String fcm_token;

	public FirebaseUserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FirebaseUserModel(String zid, String fcm_token) {
		super();
		this.zid = zid;
		this.fcm_token = fcm_token;
	}

	public String getZid() {
		return zid;
	}

	public void setZid(String zid) {
		this.zid = zid;
	}

	public String getFcm_token() {
		return fcm_token;
	}

	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}

	@Override
	public String toString() {
		return "FirebaseUserModel [zid=" + zid + ", fcm_token=" + fcm_token + "]";
	}
}
