package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "active_request_image_data")
public class ActiveRequestImageData {

	@Id
	@Column(name="img_id")
	private String imgId;
	
	@Column(name="img_data")
	private String imgData;

	public ActiveRequestImageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActiveRequestImageData(String imgId, String imgData) {
		super();
		this.imgId = imgId;
		this.imgData = imgData;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgData() {
		return imgData;
	}

	public void setImgData(String imgData) {
		this.imgData = imgData;
	}

	@Override
	public String toString() {
		return "ActiveRequestImageData [imgId=" + imgId + ", imgData=" + imgData + "]";
	}
	
}
