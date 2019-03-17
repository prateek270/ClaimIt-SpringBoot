package org.nissan.internalApp.reimbursement.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.nissan.internalApp.reimbursement.model.ActiveRequestsModel;
import org.nissan.internalApp.reimbursement.model.CardViewPopulatorModel;
import org.nissan.internalApp.reimbursement.model.RequestDataModel;
import org.nissan.internalApp.reimbursement.model.RequestMasterData;
import org.nissan.internalApp.services.AndroidPushNotificationsService;
public interface ReimbursementServices {

	public boolean saveReimbursementRequest(RequestDataModel rdModel, String zid, AndroidPushNotificationsService apnServices) throws SQLException;

	public List<CardViewPopulatorModel> retrieveActiveRequests(String zid) throws SQLException;

	public List<CardViewPopulatorModel> retrieveActiveApproverRequests(String zid) throws SQLException;

	public RequestMasterData retrieveSingleActiveRequests(String zid, String req_uid)  throws SQLException;
	
	public ActiveRequestsModel fetchRequestToBeApproved(String requid)  throws SQLException;
	
	public HashMap<String, Boolean> updateRequestAtApproval(String requid, ActiveRequestsModel arModel, String status, AndroidPushNotificationsService apnServices)  throws SQLException;
	
	public List<ActiveRequestsModel> retrieveFinanceRequests()  throws SQLException;
	
	public HashMap<String, Boolean> reimburseRequestAfterApproval(String requid, ActiveRequestsModel arModel, String status)  throws SQLException;
}
