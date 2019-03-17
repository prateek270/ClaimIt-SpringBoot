package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approvermapping")
public class ApproverMapModel {

	@Id
	@Column(name = "from")
	private String from;

	@Column(name = "to")
	private String to;

	@Column(name = "type")
	private String type;

	@Column(name = "relation")
	private String relation;

	public ApproverMapModel() {
		super();
	}

	public ApproverMapModel(String from, String to, String type, String relation) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.relation = relation;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return "ApproverMapModel [from=" + from + ", to=" + to + ", type=" + type + ", relation=" + relation + "]";
	}
}
