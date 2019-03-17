/*************
 * 
 * User Profile Model - Global Model, Constant throughout the App.
 * 						Will be inherited by the module specific UserProfileModel.
 * 			 
 *************/

package org.nissan.internalApp.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "userprofiles")

public class UserProfileModel {

	// Data Model Fields

	@Id
	@Column(name = "zid")
	private String zid;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "post")
	private String post;

	@Column(name = "profilepicurl")
	private String profilepicArr;

	public UserProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileModel(String zid, String password, String name, String email, String post, String profilepicArr) {
		super();
		this.zid = zid;
		this.password = password;
		this.name = name;
		this.email = email;
		this.post = post;
		this.profilepicArr = profilepicArr;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProfilepicArr() {
		return profilepicArr;
	}

	public void setProfilepicArr(String profilepicArr) {
		this.profilepicArr = profilepicArr;
	}

	@Override
	public String toString() {
		return "UserProfileModel [zid=" + zid + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", post=" + post + ", profilepicArr=" + profilepicArr + "]";
	}
}
