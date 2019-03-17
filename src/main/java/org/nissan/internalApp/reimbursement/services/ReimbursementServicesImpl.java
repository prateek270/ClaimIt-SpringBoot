package org.nissan.internalApp.reimbursement.services;

/**********
 * 
 * Reimbursement Services Implementation - This class will handle all the service requests dedicated towards Reimbursements Application
 * 										   made by the Controller to the Database.
 * 
 * */

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.nissan.internalApp.model.UserProfileModel;
import org.nissan.internalApp.reimbursement.model.ActiveRequestImageData;
import org.nissan.internalApp.reimbursement.model.ActiveRequestsModel;
import org.nissan.internalApp.reimbursement.model.ApproverMapModel;
import org.nissan.internalApp.reimbursement.model.CardViewPopulatorModel;
import org.nissan.internalApp.reimbursement.model.RequestDataMasterModel;
import org.nissan.internalApp.reimbursement.model.RequestDataModel;
import org.nissan.internalApp.reimbursement.model.RequestMasterData;
import org.nissan.internalApp.reimbursement.repository.ActiveRequestImageDataRepository;
import org.nissan.internalApp.reimbursement.repository.ActiveRequestRepository;
import org.nissan.internalApp.reimbursement.repository.ApproverMappingRepository;
import org.nissan.internalApp.repository.FirebaseUserRepository;
import org.nissan.internalApp.repository.UserAuthenticationRepository;
import org.nissan.internalApp.services.AndroidPushNotificationsService;
import org.nissan.internalApp.services.MailServices;
import org.nissan.internalApp.utilities.FCMPushNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

@Service
@Transactional
public class ReimbursementServicesImpl implements ReimbursementServices {

	Gson gson = new Gson();

	@Autowired
	ActiveRequestRepository activeReimbRepo;

	@Autowired
	ApproverMappingRepository aprMapRepo;

	@Autowired
	ActiveRequestImageDataRepository aridRepo;

	@Autowired
	UserAuthenticationRepository uAuthRepository;
	
	@Autowired
	FirebaseUserRepository fuRepo;

	FCMPushNotifications fcmNotif;
	
	@Autowired
	MailServices mServ;
	
	
	//Saving the reimbursement request made by the end user to the database.
	
	@Override
	public boolean saveReimbursementRequest(RequestDataModel rdModel, String zid, AndroidPushNotificationsService apnServices) throws SQLException {

		String requestType = rdModel.getType();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateCreated = (dtf.format(now)).toString();

		Date date = new Date();
		long time = date.getTime();
		String req_uid = zid + rdModel.getType() + time;

		String imgId = req_uid;
		String imgData = rdModel.getImg();
		RequestDataMasterModel rmdModel = new RequestDataMasterModel(rdModel.getType(), imgId, rdModel.getAmount(),
				rdModel.getRemark());
		ActiveRequestImageData aridModel = new ActiveRequestImageData(imgId, imgData);
		// Setting the data to the arModel

		HashMap<String, String> pipelineOperators = getCurrentOpr(zid, "00001");
		Iterator<Map.Entry<String, String>> it = pipelineOperators.entrySet().iterator();
		while (it.hasNext()) {

			
			Map.Entry<String, String> e = it.next();
			ActiveRequestsModel arModel = new ActiveRequestsModel(req_uid, zid, rmdModel, requestType, "Initiated",
					e.getKey(), e.getValue(), dateCreated, dateCreated);
			activeReimbRepo.save(arModel);
			aridRepo.save(aridModel);

			// Send Push Notification
		
			UserProfileModel initiatorModel = uAuthRepository.findByZid(arModel.getInitiator());
			String caZid = arModel.getCurrentapprover();
			String fcm_token = fuRepo.findByZid(caZid).getFcm_token();
			fcmNotif = new FCMPushNotifications();
			ResponseEntity<String> rEntity= fcmNotif.send("reimbursement","Reimbursement Request Made By :" + initiatorModel.getName(), "Request for "+rdModel.getType()+", Amounting : "+rdModel.getAmount(), fcm_token, apnServices);
			System.out.println(rEntity);
			UserProfileModel approverModel = uAuthRepository.findByZid(caZid);	
		
			// Email
			
			try {
				mServ.sendMail(approverModel.getEmail(),"Hello, This is the request for reimbursement made by "+initiatorModel.getName()+" for the type : "+arModel.getType()+", Amounting : "+rdModel.getAmount()+".\n"+"Remark : "+rdModel.getRemark(), "Reimbursement Request By : "+arModel.getInitiator());
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
			
		}
		return true;
	}

	
	
	// Getting the Cuurent Approver Pipeline data
	
	public HashMap<String, String> getCurrentOpr(String frm, String type) {

		ApproverMapModel amapModel = aprMapRepo.findByFromAndType(frm, type);
		String nextOperator = amapModel.getTo();
		String relation = amapModel.getRelation();
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put(nextOperator, relation);
		return hMap;
	}

	
	// Retrieving the active requests made by the end user	
	
	public List<CardViewPopulatorModel> retrieveActiveRequests(String zid) {

		List<ActiveRequestsModel> arModel = null;
		List<CardViewPopulatorModel> crModel = new ArrayList<CardViewPopulatorModel>();
		try {
			arModel = activeReimbRepo.findByInitiator(zid);

			for (int i = 0; i < arModel.size(); i++) {

				String approverZID = arModel.get(i).getCurrentapprover();
				UserProfileModel upModel = uAuthRepository.findByZid(approverZID);
				crModel.add(new CardViewPopulatorModel(arModel.get(i), upModel));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return crModel;
	}
	
	
	// Retrieving the entire data of a reimbursement request
	// on-click of a particular populated view of the requests

	public RequestMasterData retrieveSingleActiveRequests(String zid, String req_uid) {

		RequestMasterData rmd = null;
		try {

			ActiveRequestsModel arModel = activeReimbRepo.findByRequid(req_uid);
			RequestDataMasterModel rdModel = arModel.getReuqestdata();
			ActiveRequestImageData arid = aridRepo.findByImgId(rdModel.getimgId());
			rmd = new RequestMasterData(rdModel, arid.getImgData());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rmd;
	}

	
	
	// Retrieving the active request for the Approver
	
	public List<CardViewPopulatorModel> retrieveActiveApproverRequests(String zid) {

		List<ActiveRequestsModel> arModel = null;
		List<CardViewPopulatorModel> crModel = new ArrayList<CardViewPopulatorModel>();
		try {
			arModel = activeReimbRepo.findByCurrentapprover(zid);

			for (int i = 0; i < arModel.size(); i++) {

				String initiatorID = arModel.get(i).getInitiator();
				UserProfileModel upModel = uAuthRepository.findByZid(initiatorID);
				crModel.add(new CardViewPopulatorModel(arModel.get(i), upModel));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return crModel;
	}

	
	
	// Retrieving the entire data of a reimbursement request to be approved
	// on-click of a particular populated view of the requests
	
	public ActiveRequestsModel fetchRequestToBeApproved(String requid) {

		ActiveRequestsModel arModel = null;

		try {
			arModel = activeReimbRepo.findByRequid(requid);
			return arModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	// Updating the reimbursement request status, as per the approvers response.

	public HashMap<String, Boolean> updateRequestAtApproval(String requid, ActiveRequestsModel arModel, String status, AndroidPushNotificationsService apnServices) {

		HashMap<String, Boolean> hMap = new HashMap<>();
		try {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String datemodified = (dtf.format(now)).toString();
			arModel.setStatus(status);
			arModel.setDatemodified(datemodified);
			
			// Send Push Notification
			// Requirement
			// currentApproverZID & FCM_TOKEN

			UserProfileModel uModel = uAuthRepository.findByZid(arModel.getCurrentapprover());
			
			// Retrieve the FCM TOKEN of initiator 
			String initZID = arModel.getInitiator();
			UserProfileModel initModel = uAuthRepository.findByZid(initZID);	
			String fcm_token_apr = fuRepo.findByZid(initZID).getFcm_token();
			
			// Retrieve the FCM Token of the finance
			
			ApproverMapModel mapFinance = aprMapRepo.findByFromAndType(uModel.getZid(), "00001");			UserProfileModel financeModel = uAuthRepository.findByZid(mapFinance.getTo());
			String fcm_token_fin = fuRepo.findByZid(mapFinance.getTo()).getFcm_token();
			
			sendPushNotification(initModel.getName(), status, financeModel.getName(), arModel.getType(), "reimbursement", "Request "+ status, fcm_token_apr,fcm_token_fin, apnServices);
			
			// Email
			// 
			try {
				mServ.sendMail(initModel.getEmail(), "Hello, "+uModel.getName()+"just "+status+" your request against request ID :"+arModel.getrequid().substring(0, 6)+"...\n Thank You", arModel.getInitiator());
				mServ.sendMail(financeModel.getEmail(), "Hello, "+financeModel.getName()+" just"+status+" "+initModel.getName()+" request for "+arModel.getType()+" amounting "+arModel.getReuqestdata().getAmount()+" against request ID :"+arModel.getrequid().substring(0, 6)+"...\n Thank You", "Request approved  by " + uModel.getZid());
			} catch (MessagingException e1) {
				e1.printStackTrace();
			}
			
			hMap.put("STATUS", true);
			
			return hMap;
		} catch (Exception e) {
			hMap.put("STATUS", false);
			return hMap;
		}
	}

	/****
	 * Finance Modules
	 */
	
	public List<ActiveRequestsModel> retrieveFinanceRequests() {

		List<ActiveRequestsModel> arModel = null;

		try {
			arModel = activeReimbRepo.findByStatus("Approved");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arModel;
	}

	public HashMap<String, Boolean> reimburseRequestAfterApproval(String requid, ActiveRequestsModel arModel,
			String status) {

		HashMap<String, Boolean> hMap = new HashMap<>();
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String datemodified = (dtf.format(now)).toString();
			arModel.setStatus(status);
			arModel.setDatemodified(datemodified);
			hMap.put("STATUS", true);
			return hMap;
		} catch (Exception e) {
			hMap.put("STATUS", false);
			return hMap;
		}
	}
	
	
	
	public List<ResponseEntity<String>> sendPushNotification(String initName, String status, String name, String type, String branch, String title, String fcm_token_apr, String fcm_token_fin, AndroidPushNotificationsService apnServices){

		String initiatorsMessage ="";
		String finanaceMessage ="";
		if(status.equals("Approved")) {
			initiatorsMessage = name+" ,accepted request for type :"+type;
			finanaceMessage = name+" has accepted a request from"+initName;
		}else{
			initiatorsMessage = name+" ,declined request for type :"+type;
			finanaceMessage = name+" has declined a request from"+initName;
		}
		
		fcmNotif = new FCMPushNotifications();
		
		ResponseEntity<String> rEntity1= fcmNotif.send("reimbursement","Request "+ status, initiatorsMessage, fcm_token_apr, apnServices);
		ResponseEntity<String> rEntity2= fcmNotif.send("reimbursement","Request "+ status, finanaceMessage, fcm_token_fin, apnServices);
		
		List<ResponseEntity<String>> responseList = new ArrayList<>();
		
		responseList.add(rEntity1);
		responseList.add(rEntity2);
		return responseList;
	}

	

}
