/**
 * RESTController : Handling Every Get & Post call API.
 * Modules Contained :-
 * 				1.) Global
 *				2.) Reimbursement
 *				3.)
 *
 * */

package org.nissan.internalApp.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.nissan.internalApp.imgModel;
import org.nissan.internalApp.model.UserAuthentication;
import org.nissan.internalApp.reimbursement.model.ActiveRequestsModel;
import org.nissan.internalApp.reimbursement.model.CardViewPopulatorModel;
import org.nissan.internalApp.reimbursement.model.RequestDataModel;
import org.nissan.internalApp.reimbursement.model.RequestMasterData;
import org.nissan.internalApp.reimbursement.model.UserLoginPackage;
import org.nissan.internalApp.reimbursement.services.ReimbursementServicesImpl;
import org.nissan.internalApp.services.AndroidPushNotificationsService;
import org.nissan.internalApp.services.InternalApplicationServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NissanInternalpplicationController {

	// Service Implementation Variables

	@Autowired
	ReimbursementServicesImpl reimbursementServicesImpl;

	@Autowired
	InternalApplicationServicesImpl internalApplicationServicesImpl;

	@Autowired
	AndroidPushNotificationsService apnService;


	/***************
	 * MODULE 1 - API calls belonging to Global Module
	 * 
	 * 1.) Authentication 
	 * 2.)
	 *************/

	
	@CrossOrigin
	@PostMapping(value = "/dummy")
	public boolean saveToDB(@RequestBody imgModel imMod) {
		
		try {
			
			System.out.println(imMod.getImg());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
		
	}
	
	
	// Authentication

	@CrossOrigin
	@PostMapping(value = "/internalapp")
	public UserLoginPackage authenticateUser(@RequestBody UserAuthentication uauthModel) {

		UserLoginPackage upModel = null;

		try {

			upModel = internalApplicationServicesImpl.authenticatUser(uauthModel);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return upModel;
	}

	/***************
	 * MODULE 2 - API calls belonging to Reimbursement Module
	 * 
	 * 1.) Getting the dynamic data. 
	 * 2.) Saving the reimbursement requests made by
	 * ALL ZID. 
	 * 3.) Retrieving the reimbursement request made by User ZID 
	 * 4.)Retrieving the reimbursement request for the Approver ZID
	 *************/

	// Getting the dynamic data.

	@CrossOrigin
	@PostMapping(value = "/internalapp/reimbursements/{zid}")
	public boolean retrieveDynamicData(@RequestBody RequestDataModel rdModel, @PathVariable String zid) {

		return false;
	}

	// Saving the reimbursement requests made by ALL ZID.

	@CrossOrigin
	@PostMapping(value = "/internalapp/reimbursements/push/{zid}")
	public HashMap<String, Object> pushActiveRequests(@RequestBody RequestDataModel rdModel, @PathVariable String zid) {

		HashMap<String, Object> statusResponse = new HashMap<>();
		try {
			reimbursementServicesImpl.saveReimbursementRequest(rdModel, zid, apnService);
			statusResponse.put("STATUS", true);
			return statusResponse;
		} catch (SQLException e) {
			e.printStackTrace();
			statusResponse.put("STATUS", false);
			return statusResponse;
		}
	}
	
	

	// Retrieving the reimbursement request made by User ZID

	@CrossOrigin
	@GetMapping(value = "/internalapp/reimbursements/pull/{zid}")
	public List<CardViewPopulatorModel> pullActiveRequests(@PathVariable String zid) {

		List<CardViewPopulatorModel> cardList = null;

		try {
			cardList = reimbursementServicesImpl.retrieveActiveRequests(zid);
			
			if(cardList.isEmpty()){
				cardList = null;
			}
			return cardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

	// Retrieving the entire request data for a single request made by the user.
	
	@CrossOrigin
	@PostMapping(value = "/internalapp/reimbursements/pull/data/{zid}/{req_uid}")
	public RequestMasterData pullSingleActiveRequests(@PathVariable String zid, @PathVariable String req_uid) {

		RequestMasterData arModelList = null;

		try {
			arModelList = reimbursementServicesImpl.retrieveSingleActiveRequests(zid, req_uid);
			return arModelList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/********
	 * Approver Module
	 *******/
	
	
	// Retrieving the reimbursement request for the Approver ZID

	@CrossOrigin
	@GetMapping(value = "/internalapp/reimbursements/pull/tobeapproved/{zid}")
	public List<CardViewPopulatorModel> pullActiveApproverRequests(@PathVariable String zid) {

		List<CardViewPopulatorModel> cardList = null;

		try {
			cardList = reimbursementServicesImpl.retrieveActiveApproverRequests(zid);
			return cardList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//Approve a request
	@CrossOrigin
	@PostMapping(value = "/internalapp/reimbursements/approve/{requid}/{status}")
	public HashMap<String, Boolean> updateReimburseRequest(@PathVariable String requid, @PathVariable String status) {

		HashMap<String, Boolean> hMap = new HashMap<>();
		// Update the request in the DB
		ActiveRequestsModel arModel = reimbursementServicesImpl.fetchRequestToBeApproved(requid);
		if (arModel != null) {
			try {

			reimbursementServicesImpl.updateRequestAtApproval(requid, arModel, status, apnService);
				hMap.put("STATUS", true);
				return hMap;
			} catch (Exception e) {
				e.printStackTrace();
				hMap.put("STATUS", false);
				return hMap;
			}
		} else {

			hMap.put("STATUS", false);
			return hMap;
		}
	}
	
	/**
	 * Finance Module
	 ***/
	
	@CrossOrigin
	@GetMapping(value = "/internalapp/reimbursements/finance")
	public List<ActiveRequestsModel> pullReimbursementRequests() {

		List<ActiveRequestsModel> arModelList = null;
		try {
			arModelList = reimbursementServicesImpl.retrieveFinanceRequests();
			return arModelList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@CrossOrigin
	@PostMapping(value = "/internalapp/reimbursements/finance/{requid}/{status}")
	public HashMap<String, Boolean> approveRequest(@PathVariable String requid, @PathVariable String status) {

		
		HashMap<String, Boolean> hMap = new HashMap<>();
		// Update the request in the DB
		ActiveRequestsModel arModel = reimbursementServicesImpl.fetchRequestToBeApproved(requid);
		if (arModel != null) {
			try {
				reimbursementServicesImpl.reimburseRequestAfterApproval(requid, arModel, status);
				hMap.put("STATUS", true);
				return hMap;
			} catch (Exception e) {
				e.printStackTrace();
				hMap.put("STATUS", false);
				return hMap;
			}
		} else {

			hMap.put("STATUS", false);
			return hMap;
		}
	}	
}
