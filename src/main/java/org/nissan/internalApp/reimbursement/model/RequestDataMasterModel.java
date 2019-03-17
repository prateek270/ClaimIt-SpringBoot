package org.nissan.internalApp.reimbursement.model;

import java.io.Serializable;

public class RequestDataMasterModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String imgId;
	private String amount;
	private String remark;

	public RequestDataMasterModel() {
		super();
	}

	public RequestDataMasterModel(String type, String imgId, String amount, String remark) {
		super();
		this.type = type;
		this.imgId = imgId;
		this.amount = amount;
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getimgId() {
		return imgId;
	}

	public void setimgId(String imgId) {
		this.imgId = imgId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RequestDataModel [type=" + type + ", imgId=" + imgId + ", amount=" + amount + ", remark="
				+ remark + "]";
	}
}
