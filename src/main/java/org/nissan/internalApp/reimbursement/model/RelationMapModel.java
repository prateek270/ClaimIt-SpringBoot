package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "relationmapping")
public class RelationMapModel {

	@Id
	@Column(name = "tagcode")
	private String tagcode;
	
	@Column(name = "tagname")
	private String tagname;

	public RelationMapModel(String tagcode, String tagname) {
		super();
		this.tagcode = tagcode;
		this.tagname = tagname;
	}

	public RelationMapModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTagcode() {
		return tagcode;
	}

	public void setTagcode(String tagcode) {
		this.tagcode = tagcode;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	@Override
	public String toString() {
		return "RelationMapModel [tagcode=" + tagcode + ", tagname=" + tagname + "]";
	}
}
