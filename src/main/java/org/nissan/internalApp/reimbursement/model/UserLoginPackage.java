package org.nissan.internalApp.reimbursement.model;

import org.nissan.internalApp.model.UserProfileModel;

public class UserLoginPackage {

	private UserProfileModel upModel;
	private int no;
	public UserLoginPackage(UserProfileModel upModel, int no) {
		super();
		this.upModel = upModel;
		this.no = no;
	}
	public UserLoginPackage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProfileModel getUpModel() {
		return upModel;
	}
	public void setUpModel(UserProfileModel upModel) {
		this.upModel = upModel;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "UserLoginPackage [upModel=" + upModel + ", no=" + no + "]";
	}
	
	
}
