package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typemapping")
public class TypeMapModel {

	@Id
	@Column(name = "typetag")
	private String typetag;
	
	@Column(name = "typename")
	private String typename;

	public TypeMapModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeMapModel(String typetag, String typename) {
		super();
		this.typetag = typetag;
		this.typename = typename;
	}

	public String getTypetag() {
		return typetag;
	}

	public void setTypetag(String typetag) {
		this.typetag = typetag;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "TypeMapModel [typetag=" + typetag + ", typename=" + typename + "]";
	}
	
}
