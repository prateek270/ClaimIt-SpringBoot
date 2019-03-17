package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.nissan.internalApp.model.UserProfileModel;

@Entity
@Table(name = "userprofilereimb")
public class UserProfileReimbursementModel extends UserProfileModel{

	
	@Column(name = "requesthistory")
	private String requesthistory;
	
	@Column(name = "requesthierarchy")
	private String requesthierarchy;

	public UserProfileReimbursementModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileReimbursementModel(String requesthistory, String requesthierarchy) {
		super();
		this.requesthistory = requesthistory;
		this.requesthierarchy = requesthierarchy;
	}

	public String getRequesthistory() {
		return requesthistory;
	}

	public void setRequesthistory(String requesthistory) {
		this.requesthistory = requesthistory;
	}

	public String getRequesthierarchy() {
		return requesthierarchy;
	}

	public void setRequesthierarchy(String requesthierarchy) {
		this.requesthierarchy = requesthierarchy;
	}

	@Override
	
	public String toString() {
		return "UserProfileReimbursementModel [requesthistory=" + requesthistory + ", requesthierarchy="
				+ requesthierarchy + "]";
	}

	
}
