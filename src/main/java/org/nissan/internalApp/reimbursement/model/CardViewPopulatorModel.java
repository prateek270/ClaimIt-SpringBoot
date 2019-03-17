package org.nissan.internalApp.reimbursement.model;

import org.nissan.internalApp.model.UserProfileModel;

public class CardViewPopulatorModel {

	private ActiveRequestsModel arModel;
	private UserProfileModel upModel;
	
	public CardViewPopulatorModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CardViewPopulatorModel(ActiveRequestsModel arModel, UserProfileModel upModel) {
		super();
		this.arModel = arModel;
		this.upModel = upModel;
	}

	public ActiveRequestsModel getarModel() {
		return arModel;
	}
	public void setarModel(ActiveRequestsModel arModel) {
		this.arModel = arModel;
	}
	public UserProfileModel getUpModel() {
		return upModel;
	}
	public void setUpModel(UserProfileModel upModel) {
		this.upModel = upModel;
	}

	@Override
	public String toString() {
		return "CardViewPopulatorModel [arModel=" + arModel + ", upModel=" + upModel + "]";
	}
	
}
