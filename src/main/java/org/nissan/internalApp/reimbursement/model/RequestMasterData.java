package org.nissan.internalApp.reimbursement.model;

public class RequestMasterData {
	
	private RequestDataMasterModel rdmModel;
	private String imgData;
	public RequestMasterData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestMasterData(RequestDataMasterModel rdmModel, String imgData) {
		super();
		this.rdmModel = rdmModel;
		this.imgData = imgData;
	}
	public RequestDataMasterModel getRdmModel() {
		return rdmModel;
	}
	public void setRdmModel(RequestDataMasterModel rdmModel) {
		this.rdmModel = rdmModel;
	}
	public String getImgData() {
		return imgData;
	}
	public void setImgData(String imgData) {
		this.imgData = imgData;
	}
	@Override
	public String toString() {
		return "RequestMasterData [rdmModel=" + rdmModel + ", imgData=" + imgData + "]";
	}
	
}
