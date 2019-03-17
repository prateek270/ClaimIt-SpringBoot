package org.nissan.internalApp.reimbursement.model;

import java.io.Serializable;

public class RequestDataModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String img;
	private String amount;
	private String remark;

	public RequestDataModel() {
		super();
	}

	public RequestDataModel(String type, String img, String amount, String remark) {
		super();
		this.type = type;
		this.img = img;
		this.amount = amount;
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
		return "RequestDataModel [type=" + type + ", img=" + img + ", amount=" + amount + ", remark="
				+ remark + "]";
	}
}
