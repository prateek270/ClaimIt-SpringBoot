/**
 * Belongs to Global Module
 * */

package org.nissan.internalApp.services;

import java.sql.SQLException;

import org.nissan.internalApp.model.FirebaseUserModel;
import org.nissan.internalApp.model.UserAuthentication;
import org.nissan.internalApp.model.UserProfileModel;
import org.nissan.internalApp.reimbursement.model.UserLoginPackage;
import org.nissan.internalApp.reimbursement.repository.ActiveRequestRepository;
import org.nissan.internalApp.repository.FirebaseUserRepository;
import org.nissan.internalApp.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InternalApplicationServicesImpl implements InternalApplicationServices {

	// Repository Variable Declarations

	@Autowired
	UserAuthenticationRepository uAuthRepository;

	@Autowired
	ActiveRequestRepository arrRepo;

	@Autowired
	FirebaseUserRepository fuRepo;

	@Override
	public UserLoginPackage authenticatUser(UserAuthentication uAuthModel) {

		System.out.println(uAuthModel.getZid());
		System.out.println(uAuthModel.getPassword());
		UserLoginPackage alPackage = null;

		try {
			// Auth
			UserProfileModel upModel = uAuthRepository.findByZidAndPassword(uAuthModel.getZid(),
					uAuthModel.getPassword());
			if (upModel != null) {
				int no = arrRepo.findByCurrentapproverAndStatus(uAuthModel.getZid(), "Initiated").size();
				alPackage = new UserLoginPackage(upModel, no);
				
				if (uAuthModel.getFcm_token() != null) {

					FirebaseUserModel fuModel = new FirebaseUserModel(uAuthModel.getZid(), uAuthModel.getFcm_token());
					fuRepo.save(fuModel);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(alPackage);
		return alPackage;
	}

}
