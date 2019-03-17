/********************
 * Active Requests Data Model Class - Holds the Schema for the active request that are
 * currently in the pipeline, needed approval.
 * 
 *   #### DATA FIELDS EXPLANATION ####
 *   
 *   	requid 			---> ZID + RequestTag(Look up the Documentation) + Timestamp
 *   	initiator 			---> ZID of the person who has requested an approval
 *   	type 				---> Type of the reimbursement asked for
 *   	status 				---> Status of the Approval (Look up the Approver Data Model)
 *   	currentoperator 	---> ZID of the current operator
 *   	currentoperatortype ---> Type of the current operator (Lookup the documentation)
 *   	attatchedfilelink 	---> Attatched file link of the Reciept/Bill
 *   	datecreated 		---> Date of the initiators request
 *   	datemodified 		---> Date of the last modified result
 *   
 ********************/

package org.nissan.internalApp.reimbursement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "activerequests")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ActiveRequestsModel {

	@Id
	@Column(name = "requid")
	private String requid;

	@Column(name = "initiator")
	private String initiator;

	@Type(type = "jsonb")
	@Column(name = "requestdata", nullable = false, columnDefinition = "jsonb")
	private RequestDataMasterModel reuqestdata;

	@Column(name = "type")
	private String type;

	@Column(name = "status")
	private String status;

	@Column(name = "currentapprover")
	private String currentapprover;

	@Column(name = "currentapprovertype")
	private String currentapprovertype;

	@Column(name = "datecreated")
	private String datecreated;

	@Column(name = "datemodified")
	private String datemodified;

	// Constructors

	public ActiveRequestsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActiveRequestsModel(String requid, String initiator, RequestDataMasterModel reuqestdata, String type,
			String status, String currentapprover, String currentapprovertype, String datecreated,
			String datemodified) {
		super();
		this.requid = requid;
		this.initiator = initiator;
		this.reuqestdata = reuqestdata;
		this.type = type;
		this.status = status;
		this.currentapprover = currentapprover;
		this.currentapprovertype = currentapprovertype;
		this.datecreated = datecreated;
		this.datemodified = datemodified;

	}

	public String getrequid() {
		return requid;
	}

	public void setrequid(String requid) {
		this.requid = requid;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public RequestDataMasterModel getReuqestdata() {
		return reuqestdata;
	}

	public void setReuqestdata(RequestDataMasterModel reuqestdata) {

		this.reuqestdata = reuqestdata;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentapprover() {
		return currentapprover;
	}

	public void setCurrentapprover(String currentapprover) {
		this.currentapprover = currentapprover;
	}

	public String getCurrentapprovertype() {
		return currentapprovertype;
	}

	public void setCurrentapprovertype(String currentapprovertype) {
		this.currentapprovertype = currentapprovertype;
	}

	public String getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}

	public String getDatemodified() {
		return datemodified;
	}

	public void setDatemodified(String datemodified) {
		this.datemodified = datemodified;
	}

	@Override
	public String toString() {
		return "ActiveRequestsModel [requid=" + requid + ", initiator=" + initiator + ", reuqestdata=" + reuqestdata
				+ ", type=" + type + ", status=" + status + ", currentapprover=" + currentapprover
				+ ", currentapprovertype=" + currentapprovertype + ", datecreated=" + datecreated + ", datemodified="
				+ datemodified + "]";
	}
}
